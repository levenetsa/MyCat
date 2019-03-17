package com.lev.mycat.activity.dagger

import androidx.appcompat.app.AppCompatActivity
import dagger.Subcomponent


@Subcomponent(
    modules = [
        MainActivityModule::class]
)
interface MainActivityComponent {

    fun inject(mainActivity: AppCompatActivity)
}