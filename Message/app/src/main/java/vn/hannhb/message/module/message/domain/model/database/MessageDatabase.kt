package vn.hannhb.message.module.message.domain.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vn.hannhb.Application
import vn.hannhb.message.core.helpers.JsonUtil
import vn.hannhb.message.module.message.domain.model.entities.AttachmentConverter
import vn.hannhb.message.module.message.domain.model.entities.Message
import vn.hannhb.message.module.message.domain.model.entities.OwnerConverter

@Database(entities = [Message::class], version = 2, exportSchema = false)
@TypeConverters(AttachmentConverter::class, OwnerConverter::class)
abstract class MessageDatabase : RoomDatabase() {

    abstract fun messageDao(): MessageDao

    companion object {
        @Volatile
        private var instance: MessageDatabase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            MessageDatabase::class.java, "message.db"
        )
            .addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        JsonUtil.readJSONFile(Application.mContext)?.messageList?.messages?.let {
                            instance?.messageDao()?.insertMessages(it)
                        }
                    }
                }
            })
            .build()
    }
}
