package com.shreyas.nytimes.di.modules

import androidx.lifecycle.ViewModelProvider
import com.shreyas.nytimes.di.DaggerViewModelFactory
import com.shreyas.nytimes.repository.GitHubSearchRepository
import com.shreyas.nytimes.repository.GitHubSearchRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @Singleton
    abstract fun provideGithubSearchRepository(repositoryImpl: GitHubSearchRepositoryImpl): GitHubSearchRepository
}