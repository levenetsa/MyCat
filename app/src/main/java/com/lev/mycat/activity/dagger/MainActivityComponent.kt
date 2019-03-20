package com.lev.mycat.activity.dagger

import com.lev.mycat.activity.MainActivity
import com.lev.mycat.cat.add.AddCatFragment
import com.lev.mycat.cat.distance.DistanceFragment
import com.lev.mycat.cat.info.InfoCatFragment
import dagger.Subcomponent


@Subcomponent(
    modules = [
        MainActivityModule::class]
)
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(fragment: DistanceFragment)
    fun inject(fragment: InfoCatFragment)
    fun inject(fragment: AddCatFragment)
}