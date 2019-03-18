package com.lev.mycat.activity

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lev.mycat.CatApplication
import com.lev.mycat.R
import com.lev.mycat.activity.dagger.MainActivityModule
import com.lev.mycat.db.Cat
import com.lev.mycat.db.CatStore
import com.lev.mycat.navigation.NavigationController
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationController: NavigationController

    @Inject
    lateinit var catStore: CatStore

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    textMessage.setText(R.string.title_home)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    textMessage.setText(R.string.title_dashboard)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    textMessage.setText(R.string.title_notifications)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpDagger()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(
            onNavigationItemSelectedListener
        )

        navigationController.init()
        catStore.load(object : CatStore.Callback {
            override fun onSuccess(cat: Cat?) {
                Toast.makeText(
                    applicationContext,
                    cat?.name ?: "no cats",
                    Toast.LENGTH_LONG
                ).show()
            }

        })
    }

    private fun setUpDagger() {
        val activityComponent = CatApplication.initMainActivityComponent(
            MainActivityModule(this)
        )
        //TODO: re-locate to activity component
        CatApplication.appComponent.inject(this)
    }
}
