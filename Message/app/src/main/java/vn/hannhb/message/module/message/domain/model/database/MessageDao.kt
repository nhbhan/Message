package vn.hannhb.message.module.message.domain.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import vn.hannhb.message.module.message.domain.model.entities.Message

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMessages(messages: List<Message>)

    @Query(value = "SELECT * FROM messages")
    fun getMessageLiveData(): LiveData<List<Message>>

    @Query("SELECT * FROM messages WHERE isFavourite = :type")
    fun getMessageFromTypeLiveData(type: Boolean): LiveData<List<Message>>?

    @Query(value = "SELECT * FROM messages")
    suspend fun getMessage(): List<Message>

    @Query("SELECT * FROM messages WHERE isFavourite = :type")
    suspend fun getMessageFromType(type: Boolean): List<Message>

    @Query("UPDATE messages SET isFavourite = :favourite WHERE id = :id")
    suspend fun updateMessage(id: Int, favourite: Boolean)

    @Query("DELETE FROM messages")
    suspend fun deleteMessage()
}
