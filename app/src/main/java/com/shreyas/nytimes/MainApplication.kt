package com.shreyas.nytimes

import android.app.Application
import com.shreyas.nytimes.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = activityInjector

    override fun onCreate() {
        super.onCreate()

        createAppDaggerComponent()
    }

    private fun createAppDaggerComponent() {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
    }
}