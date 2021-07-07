package vn.hannhb.message.module.message.domain.model.entities

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.*

class AttachmentConverter {

    private val adapter by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val listMyData = Types.newParameterizedType(List::class.java, Attachment::class.java)
        return@lazy moshi.adapter<List<Attachment>>(listMyData)
    }

    @TypeConverter
    fun toJson(coordinates: List<Attachment>): String {
        return adapter.toJson(coordinates)
    }

    @TypeConverter
    fun formJson(json: String): List<Attachment>? {
        return adapter.fromJson(json)
    }
}
