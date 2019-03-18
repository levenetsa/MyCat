package com.lev.mycat

import android.app.Application
import com.lev.mycat.activity.dagger.MainActivityComponent
import com.lev.mycat.activity.dagger.MainActivityModule
import com.lev.mycat.dagger.AppComponent
import com.lev.mycat.dagger.AppModule
import com.lev.mycat.dagger.DaggerAppComponent

class CatApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
        @JvmStatic
        private var mainActivityComponent: MainActivityComponent? = null

        fun initMainActivityComponent(activityModule: MainActivityModule):
                MainActivityComponent {
            mainActivityComponent = appComponent.plus(activityModule)
            return mainActivityComponent!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent =
            DaggerAppComponent.builder().appModule(
                AppModule(
                    this
                )
            ).build()
    }
}