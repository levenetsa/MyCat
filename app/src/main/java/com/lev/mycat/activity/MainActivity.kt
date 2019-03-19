package com.lev.mycat.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lev.mycat.CatApplication
import com.lev.mycat.R
import com.lev.mycat.activity.dagger.MainActivityModule
import com.lev.mycat.navigation.NavigationController
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationController: NavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activityComponent = CatApplication.initMainActivityComponent(
            MainActivityModule(this)
        )
        activityComponent.inject(this)
        navigationController.setUpController(this)
    }
}
