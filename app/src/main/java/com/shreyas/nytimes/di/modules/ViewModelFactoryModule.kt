package com.shreyas.nytimes.di.modules

import androidx.lifecycle.ViewModelProvider
import com.shreyas.nytimes.di.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory


}