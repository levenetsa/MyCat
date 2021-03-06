package com.lev.mycat.cat.distance

import android.graphics.Bitmap
import android.view.View
import com.lev.mycat.cat.add.strToBitmap
import com.lev.mycat.db.Cat
import javax.inject.Inject

class DistanceViewController
@Inject
constructor(
    private val distanceView: DistanceView,
    private val model: DistanceModel
) {

    private val modelListener = object :
        DistanceModel.OnDataChanged {
        override fun onCatChanged() {
            updateCat()
        }

        override fun onDistanceCounted() {
            updateDistance()
        }
    }

    fun init(view: View){
        distanceView.init(view)
        model.init()
        distanceView.setCatLoading(true)
        distanceView.setDistanceLoading(true)
        model.listener = modelListener
        if (model.isCatReady()) {
            updateCat()
        }
    }

    fun destroy() {
        model.listener = null
    }

    private fun updateCat() {
        distanceView.setName(model.cat!!.name)
        distanceView.setPhoto(getCatPhoto(model.cat!!))
    }

    private fun updateDistance(){
        distanceView.setDistanceLoading(false)
        distanceView.setDistance(model.distance!!)
    }

    private fun getCatPhoto(cat: Cat): Bitmap? {
        return cat.picture?.let { strToBitmap(it) }
    }
}