package com.lev.mycat.cat.info

import android.view.View
import com.lev.mycat.R
import com.lev.mycat.activity.MainActivity
import com.lev.mycat.cat.BaseFragment

class InfoCatFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.info_cat_fragment

    override fun initDagger(view: View) {
        (activity as MainActivity).activityComponent!!.inject(this)
        //init viewController here
    }

    override fun clearDagger() {}
}