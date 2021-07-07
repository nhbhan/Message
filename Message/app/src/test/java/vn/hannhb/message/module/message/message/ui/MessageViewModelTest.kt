package vn.hannhb.message.module.message.message.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import vn.hannhb.message.module.message.MainCoroutineRule
import vn.hannhb.message.module.message.domain.model.entities.Message
import vn.hannhb.message.module.message.domain.usercase.*
import vn.hannhb.message.module.message.domain.usercase.SaveMessageUseCase
import vn.hannhb.message.module.message.message.data.datasource.FakeMessageDataSource
import vn.hannhb.message.module.message.message.data.repository.FakeMessageRepository
import vn.hannhb.message.module.message.ui.MessageViewModel

@ExperimentalCoroutinesApi
class MessageViewModelTest {
    // Subject under test
    private lateinit var messageViewModelTest: MessageViewModel

    // Use a fake component to be injected into the view model
    private lateinit var messageRepository: FakeMessageRepository
    private lateinit var messageDataSource: FakeMessageDataSource
    private lateinit var getNormalMessageUseCase: GetNormalMessageUserCase
    private lateinit var getFavouriteMessageUseCase: GetFavouriteMessagesUseCase
    private lateinit var saveMessageUseCase: SaveMessageUseCase

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @Before
    fun setUp() {
        messageDataSource = FakeMessageDataSource()
        messageRepository = FakeMessageRepository(messageDataSource)
        getFavouriteMessageUseCase = GetFavouriteMessagesUseCase(messageRepository)
        getNormalMessageUseCase = GetNormalMessageUserCase(messageRepository)
        saveMessageUseCase = SaveMessageUseCase(messageRepository)
        messageViewModelTest = MessageViewModel(
            getFavouriteMessagesUseCase = getFavouriteMessageUseCase,
            getNormalMessageUserCase = getNormalMessageUseCase,
            saveMessageUseCase = saveMessageUseCase
        )
    }

    @Test
    fun test_getNormalMessages() {
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
        val actualResult = messageViewModelTest.normalMessage?.value.toString()
        val expectedResult = messages.toString()

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun test_getFavouriteMessages() {
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
        val actualResult = messageViewModelTest.favouriteMessages?.value?.toString()
        val expectedResult = messages.toString()
        Assert.assertEquals(expectedResult, actualResult)
    }
}
