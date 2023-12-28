package com.shreyas.nytimes.di

import android.app.Application
import com.shreyas.nytimes.MainApplication
import com.shreyas.nytimes.di.modules.GithubRepoModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

class TestComponent {

    @Singleton
    @Component(
        modules = [
            GithubRepoModule::class
        ]
    )
    interface TestAppComponent {

        fun inject(mainApplication: MainApplication)

        @Component.Builder
        interface Builder {

            fun build(): TestAppComponent

            @BindsInstance
            fun application(application: Application): Builder
        }
    }
}