package com.lev.mycat.activity.dagger

import com.lev.mycat.activity.MainActivity
import dagger.Subcomponent


@Subcomponent(
    modules = [
        MainActivityModule::class]
)
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)
}