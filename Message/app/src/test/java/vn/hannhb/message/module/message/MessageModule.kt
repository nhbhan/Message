package vn.hannhb.message.module.message

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.get
import org.koin.test.inject
import vn.hannhb.message.module.message.message.data.datasource.FakeMessageDataSource
import vn.hannhb.message.module.splash.splashModule

class MessageDataSource

open class MessageRepository(val messageDataSource: FakeMessageDataSource)

class GetFavouriteMessageUseCase(val messageRepository: MessageRepository)

class GetNormalMessageUseCase(val messageRepository: MessageRepository)

class SaveMessageUseCase(val messageRepository: MessageRepository)

/**
 * initial MessageDatabase
 */

class MessageModule : KoinTest {

    // Lazy inject property
    private val messageRepository: MessageRepository by inject()
    private val getFavouriteMessageUseCase: GetFavouriteMessageUseCase by inject()
    private val getNormalMessageUseCase: GetNormalMessageUseCase by inject()
    private val saveMessageUseCase: SaveMessageUseCase by inject()

    @Test
    fun checkModulesApp() {
        checkModules {
            modules(messageModule, splashModule)
        }
    }

    @Test
    fun startKoin() {
        stopKoin()
        startKoin {
            modules(
                module {
                    single { MessageDataSource() }
                    single { MessageRepository(get()) }
                    single { GetFavouriteMessageUseCase(get()) }
                    single { GetNormalMessageUseCase(get()) }
                    single { SaveMessageUseCase(get()) }
                }
            )
        }

        // directly request an instance
        val messageDataSource = get<MessageDataSource>()
        assertNotNull(messageDataSource)
        assertEquals(messageDataSource, messageRepository.messageDataSource)

        val messageRepository = get<MessageRepository>()
        assertNotNull(messageRepository)
        assertEquals(messageRepository, getFavouriteMessageUseCase.messageRepository)
        assertEquals(messageRepository, getNormalMessageUseCase.messageRepository)
        assertEquals(messageRepository, saveMessageUseCase.messageRepository)
    }
}
