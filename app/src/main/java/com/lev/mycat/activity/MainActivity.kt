package com.lev.mycat.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lev.mycat.CatApplication
import com.lev.mycat.R
import com.lev.mycat.activity.dagger.MainActivityComponent
import com.lev.mycat.activity.dagger.MainActivityModule
import com.lev.mycat.navigation.NavigationController
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationController: NavigationController

    var activityComponent: MainActivityComponent? = null

    private val resultListeners: MutableList<ResultListener> =
        mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent = CatApplication.initMainActivityComponent(
            MainActivityModule(this)
        )
        activityComponent!!.inject(this)
        navigationController.setUpController(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        activityComponent = null
        CatApplication.clearMainActivityComponent()
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) = resultListeners.forEach { it.onResult(requestCode, resultCode, data) }

    fun addListener(listener: ResultListener) = resultListeners.add(listener)

    fun removeListener(listener: ResultListener) =
        resultListeners.remove(listener)

    interface ResultListener {
        fun onResult(requestCode: Int, resultCode: Int, data: Intent?)
    }
}
