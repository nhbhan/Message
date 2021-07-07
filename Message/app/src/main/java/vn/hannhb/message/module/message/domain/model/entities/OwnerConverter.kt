package vn.hannhb.message.module.message.domain.model.entities

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class OwnerConverter {

    private val adapter by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val listMyData = Types.newParameterizedType(Owner::class.java, Owner::class.java)
        return@lazy moshi.adapter<Owner>(listMyData)
    }

    @TypeConverter
    fun toJson(coordinates: Owner?): String? {
        return adapter.toJson(coordinates)
    }

    @TypeConverter
    fun formJson(json: String?): Owner? {
        return adapter.fromJson(json)
    }
}
