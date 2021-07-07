package vn.hannhb.message.module.message.domain.model.entities

import com.squareup.moshi.Json

data class Attachment(
    @Json(name = "height") var height: Int? = null,
    @Json(name = "modifyTime") var modifyTime: Long? = null,
    @Json(name = "url") var url: String? = null,
    @Json(name = "width") var width: Int? = null
) : Base()
