package vn.hannhb.message.core.restful.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.jvm.Throws

class DateJsonAdapter : JsonAdapter<Date>() {

    @Synchronized
    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): Date? {
        when (reader.peek()) {
            JsonReader.Token.STRING -> {
                val string = reader.nextString()
                val knownPatterns = ArrayList<SimpleDateFormat>()
                knownPatterns.add(SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US))

                for (pattern in knownPatterns) {
                    try {
                        return Date(pattern.parse(string).time)
                    } catch (ignored: ParseException) {
                    }
                }

                return IsoUtils.parse(string)
            }
            else -> {
                return reader.nextNull()
            }
        }
    }

    @Synchronized
    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: Date?) {
        value?.let {
            writer.value(it.toString())
        } ?: run {
            writer.nullValue()
        }
    }
}
