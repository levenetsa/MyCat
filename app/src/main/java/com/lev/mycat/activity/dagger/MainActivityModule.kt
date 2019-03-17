package com.lev.mycat.activity.dagger

import androidx.appcompat.app.AppCompatActivity
import com.lev.mycat.activity.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(
    protected val mainActivity: MainActivity
) {

    @Provides
    fun provideActivity(): MainActivity = mainActivity

    @Provides
    fun provideAppCompat(): AppCompatActivity = mainActivity
}
