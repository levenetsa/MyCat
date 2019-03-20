package com.lev.mycat.cat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initDagger(view: View)

    abstract fun clearDagger()

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutId(), container, false)

        initDagger(view)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        clearDagger()
    }
}