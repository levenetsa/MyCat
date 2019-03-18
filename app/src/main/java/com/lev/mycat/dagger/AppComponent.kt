package com.lev.mycat.dagger

import com.lev.mycat.activity.MainActivity
import com.lev.mycat.activity.dagger.MainActivityComponent
import com.lev.mycat.activity.dagger.MainActivityModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun plus(activityModule: MainActivityModule): MainActivityComponent

    fun inject(mainActivity: MainActivity)
}