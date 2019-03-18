package com.lev.mycat.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lev.mycat.CatApplication
import com.lev.mycat.R
import com.lev.mycat.activity.dagger.MainActivityModule
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewController: MainViewController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activityComponent = CatApplication.initMainActivityComponent(
            MainActivityModule(this)
        )
        activityComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewController.destroy()
    }
}
