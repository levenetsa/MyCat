package com.lev.mycat.dagger.component

import com.lev.mycat.activity.MainActivity
import com.lev.mycat.activity.dagger.MainActivityComponent
import com.lev.mycat.activity.dagger.MainActivityModule
import com.lev.mycat.dagger.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun plus(activityModule: MainActivityModule): MainActivityComponent

    fun inject(mainActivity: MainActivity)
}