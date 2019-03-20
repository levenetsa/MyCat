package com.lev.mycat.cat.distance

import android.os.AsyncTask
import android.os.Handler
import com.lev.mycat.R
import com.lev.mycat.db.Cat
import com.lev.mycat.db.CatStore
import com.lev.mycat.navigation.NavigationController
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DistanceModel
@Inject
constructor(
    private val catStore: CatStore,
    private val navigator: NavigationController
) {

    var cat: Cat? = null
    var distance: Int? = null

    var handler = Handler()
    var listener: OnDataChanged? = null

    fun init() {
        loadCat()
    }

    fun isCatReady() = cat != null

    private fun loadCat() {
        catStore.load { cat: Cat? ->
            if (cat == null) {
                navigator.navigate(R.id.add_cat)
            } else {
                this@DistanceModel.cat = cat
                listener?.onCatChanged()
                calculateDistance()
            }
        }
    }

    private fun calculateDistance() = AsyncTask.execute {
        //countDistanceHere
        distance = 1574
        handler.post { listener?.onDistanceCounted() }
    }

    interface OnDataChanged {
        fun onCatChanged()
        fun onDistanceCounted()
    }
}