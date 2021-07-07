package vn.hannhb.message.module.message

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import vn.hannhb.message.module.message.data.datasource.MessageDataSource
import vn.hannhb.message.module.message.data.datasource.MessageDateSourceImp
import vn.hannhb.message.module.message.data.repository.MessageRepository
import vn.hannhb.message.module.message.domain.usercase.*
import vn.hannhb.message.module.message.ui.DetailMessageViewModel
import vn.hannhb.message.module.message.ui.MessageViewModel

val messageModule = module {
    single<MessageDataSource> { MessageDateSourceImp() }

    // repository
    single { MessageRepository(get()) }

    // user case
    single { GetFavouriteMessagesUseCase(get()) }
    single { GetNormalMessageUserCase(get()) }
    single { SaveMessageUseCase(get()) }

    // view model
    viewModel { MessageViewModel(get(), get(), get()) }
    viewModel { DetailMessageViewModel() }
}
