package com.lev.mycat.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lev.mycat.CatApplication
import com.lev.mycat.R
import com.lev.mycat.activity.dagger.MainActivityModule
import com.lev.mycat.db.Cat
import com.lev.mycat.db.CatStore
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var catStore: CatStore

    private lateinit var textMessage: TextView

    private val noCatsText = "Please provide your cat location"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpDagger()

        textMessage = findViewById(R.id.message)
        catStore.load(object : CatStore.Callback {
            override fun onSuccess(cat: Cat?) {
                textMessage.text = cat?.name ?: noCatsText
            }
        })
    }

    private fun setUpDagger() {
        val activityComponent = CatApplication.initMainActivityComponent(
            MainActivityModule(this)
        )
        activityComponent.inject(this)
    }
}
