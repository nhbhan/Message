package vn.hannhb.message.module.message.message.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import vn.hannhb.message.module.message.data.datasource.MessageDataSource
import vn.hannhb.message.module.message.domain.model.entities.Message

class FakeMessageDataSource : MessageDataSource {

    override fun getFavouriteMessages(): LiveData<List<Message>>? {
        val messageLiveData = MutableLiveData<List<Message>>()
        val messages: MutableList<Message> = ArrayList()
        messages.add(
            Message(
                attachmentList = emptyList(),
                title = "title",
                content = "content",
                postTime = 14872634,
                id = 1,
                isFavourite = true
            )
        )
        messageLiveData.value = messages
        return messageLiveData
    }

    override fun getNormalMessages(): LiveData<List<Message>>? {
        val messageLiveData = MutableLiveData<List<Message>>()
        val messages: MutableList<Message> = ArrayList()
        messages.add(
            Message(
                attachmentList = emptyList(),
                title = "normal",
                content = "normal content",
                postTime = 14872634,
                id = 2,
                isFavourite = true
            )
        )
        messageLiveData.value = messages
        return messageLiveData
    }

    override suspend fun saveFavouriteMessage(messageId: Int, favorite: Boolean) {}
}
