package vn.hannhb.message.module.message.domain.model.entities

import com.squareup.moshi.Json

data class JsonData(@Json(name = "messagesList") var messageList: MessageList? = null) : Base()

data class MessageList(
    @Json(name = "currentPage") var currentPage: Int? = null,
    @Json(name = "itemsPerPage") var itemsPerPage: Int? = null,
    @Json(name = "totalPages") var totalPages: Int? = null,
    @Json(name = "list") var messages: List<Message>? = null
) : Base()
