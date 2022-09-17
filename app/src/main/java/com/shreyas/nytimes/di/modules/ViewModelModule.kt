package com.shreyas.nytimes.di.modules

import androidx.lifecycle.ViewModel
import com.shreyas.nytimes.di.annotations.ViewModelKey
import com.shreyas.nytimes.viewmodel.GithubSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GithubSearchViewModel::class)
    abstract fun bindGithubSearchViewModel(githubSearchViewModel: GithubSearchViewModel): ViewModel
}