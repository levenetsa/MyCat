package com.lev.mycat.cat.add

import android.view.View
import com.lev.mycat.R
import com.lev.mycat.activity.MainActivity
import com.lev.mycat.cat.BaseFragment
import javax.inject.Inject

class AddCatFragment : BaseFragment() {

    @Inject
    lateinit var viewController: AddCatViewController

    override fun getLayoutId() = R.layout.add_cat_fragment

    override fun initDagger(view: View) {
        (activity as MainActivity).activityComponent!!.inject(this)
        viewController.init(view)
    }

    override fun clearDagger() = viewController.destroy()
}