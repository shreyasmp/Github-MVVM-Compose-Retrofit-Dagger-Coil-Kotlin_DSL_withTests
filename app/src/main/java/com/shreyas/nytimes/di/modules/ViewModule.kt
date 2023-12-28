package com.shreyas.nytimes.di.modules

import com.shreyas.nytimes.di.annotations.ActivityScope
import com.shreyas.nytimes.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}