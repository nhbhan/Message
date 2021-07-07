package vn.hannhb.message.module.splash

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import vn.hannhb.message.module.splash.ui.SplashViewModel

val splashModule = module {
    // view model
    viewModel { SplashViewModel() }
}
