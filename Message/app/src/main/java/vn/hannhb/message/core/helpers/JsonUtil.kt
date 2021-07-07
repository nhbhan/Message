package vn.hannhb.message.core.helpers

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import vn.hannhb.message.core.restful.adapter.DateJsonAdapter
import vn.hannhb.message.module.message.domain.model.entities.JsonData
import java.io.IOException
import java.util.*

object JsonUtil {
    @Throws(IOException::class)
    fun readJSONFile(context: Context): JsonData? {
        val filePath = "messageslist.json"
        val myJson = context.assets.open(filePath).bufferedReader().use { it.readText() }
        return moshiConverter(myJson)
    }

    @Throws(IOException::class)
    fun moshiConverter(jsonData: String): JsonData? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, DateJsonAdapter())
            .build()
        val jsonAdapter = moshi.adapter<JsonData>(JsonData::class.java)
        return jsonAdapter.fromJson(jsonData)
    }
}
