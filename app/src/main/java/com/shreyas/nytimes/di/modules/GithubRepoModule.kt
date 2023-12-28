package com.shreyas.nytimes.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ServiceModule::class,
        ViewModelFactoryModule::class,
        ViewModule::class,
        ViewModelModule::class
    ]
)
abstract class GithubRepoModule {

    companion object {
        @Provides
        fun provideApplicationContext(application: Application): Context {
            return application.applicationContext
        }
    }
}