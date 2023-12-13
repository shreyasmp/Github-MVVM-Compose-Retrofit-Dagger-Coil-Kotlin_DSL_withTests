package com.shreyas.nytimes.di

import android.app.Application
import com.shreyas.nytimes.MainApplication
import com.shreyas.nytimes.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ServiceModule::class,
        ViewModelFactoryModule::class,
        ViewModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(mainApplication: MainApplication)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}