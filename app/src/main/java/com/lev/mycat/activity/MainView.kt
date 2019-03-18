package com.lev.mycat.activity

import android.widget.TextView
import com.lev.mycat.R
import javax.inject.Inject

class MainView
@Inject
constructor(private val activity: MainActivity) {

    private val nameView: TextView = activity.findViewById(R.id.message)

    fun setName(name: String) {
        nameView.text = name
    }
}