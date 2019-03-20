package com.lev.mycat.cat.add

import android.view.View
import com.lev.mycat.R
import com.lev.mycat.activity.MainActivity
import com.lev.mycat.cat.BaseFragment

class AddCatFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.add_cat_fragment

    override fun initDagger(view: View) {
        (activity as MainActivity).activityComponent!!.inject(this)
        //init viewController here
    }

    override fun clearDagger() {}
}