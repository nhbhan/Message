package vn.hannhb.message.module.message.message.domain.model.database

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert
import kotlinx.coroutines.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import vn.hannhb.message.module.message.domain.model.database.MessageDao
import vn.hannhb.message.module.message.domain.model.database.MessageDatabase
import vn.hannhb.message.module.message.domain.model.entities.Message
import java.io.IOException

/**
 * Please comment initZomInOrOutImageView() in Application to test some cases bellow
 */

@RunWith(AndroidJUnit4::class)
class TestMessageDataBase {
    private lateinit var messageDao: MessageDao
    private lateinit var messageDatabase: MessageDatabase
    private val messages: MutableList<Message> = ArrayList()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getContext()
        messageDatabase = Room.inMemoryDatabaseBuilder(
            context, MessageDatabase::class.java
        ).build()
        messageDao = messageDatabase.messageDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        messageDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun test_insertAndQueryMessage() {
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
        CoroutineScope(Dispatchers.IO).launch {
            messageDao.insertMessages(messages)
        }
        Assert.assertEquals(messages.toString(), getListMessage().toString())
    }

    private fun getListMessage(): List<Message> {
        var message = emptyList<Message>()
        runBlocking {
            val jobA = async { messageDao.getMessageFromType(true) }
            runBlocking {
                message = jobA.await()
            }
        }
        return message
    }
}
