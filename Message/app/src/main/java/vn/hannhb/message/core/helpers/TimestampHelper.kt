package vn.hannhb.message.core.helpers

import java.text.SimpleDateFormat
import java.util.*

object TimestampHelper {
    fun getDateTime(timestamp: Long?, pattern: String? = "dd/MM/yyyy - hh:mm"): String? {
        val unwrappedTimestamp = timestamp ?: return ""
        val currentDate = Date(unwrappedTimestamp)
        val dateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
        val date = dateFormat.format(currentDate)
        return date
    }
}
