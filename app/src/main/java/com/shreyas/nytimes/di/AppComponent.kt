package com.shreyas.nytimes.di

import android.app.Application
import com.shreyas.nytimes.MainApplication
import com.shreyas.nytimes.di.modules.GithubRepoModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        GithubRepoModule::class
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