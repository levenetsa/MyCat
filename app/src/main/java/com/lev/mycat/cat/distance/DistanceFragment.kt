package com.lev.mycat.cat.distance

import android.view.View
import com.lev.mycat.R
import com.lev.mycat.activity.MainActivity
import com.lev.mycat.cat.BaseFragment
import javax.inject.Inject

class DistanceFragment : BaseFragment() {

    @Inject
    lateinit var viewController: DistanceViewController

    override fun getLayoutId() = R.layout.distance_fragment

    override fun initDagger(view: View) {
        (activity as MainActivity).activityComponent!!.inject(this)
        viewController.init(view)
    }

    override fun clearDagger() = viewController.destroy()
}