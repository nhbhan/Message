package vn.hannhb.message.module.message.ui

import vn.hannhb.message.core.helpers.TimestampHelper
import vn.hannhb.message.core.ui.BaseViewModel
import vn.hannhb.message.module.message.domain.model.entities.Attachment
import vn.hannhb.message.module.message.domain.model.entities.Message

class DetailMessageViewModel : BaseViewModel() {
    var message: Message? = null

    fun getAttachments(): MutableList<Attachment> {
        return message?.attachmentList?.toMutableList() ?: ArrayList()
    }

    fun getTotalComments(): Int {
        return message?.totalComments ?: 0
    }

    fun getNumberOfAttachments(): Int {
        return message?.attachmentList?.size ?: 0
    }

    fun getTitle(): String {
        return message?.title ?: ""
    }

    fun getContent(): String {
        return message?.content ?: ""
    }

    fun getAvatarUrl(): String {
        return message?.owner?.avatar ?: ""
    }

    fun getFullName(): String {
        return message?.owner?.fullName ?: "No Name"
    }

    fun getTime(): String {
        val unwrappedPostTime = message?.postTime ?: return ""
        return TimestampHelper.getDateTime(unwrappedPostTime) ?: ""
    }
}
