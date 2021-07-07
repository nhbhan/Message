package vn.hannhb.message.core.helpers

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import vn.hannhb.message.module.message.messageModule
import vn.hannhb.message.module.splash.splashModule

class KoinConfigurationHelper {
    companion object {
        private var instance: KoinApplication? = null

        operator fun invoke(context: Context) = instance ?: synchronized(this) {
            instance ?: startKoin(context).also { instance = it }
        }

        private fun startKoin(context: Context) = org.koin.core.context.startKoin {
            androidLogger()
            androidContext(context)
            modules(
                listOf(
                    splashModule,
                    messageModule
                )
            )
        }
    }
}
