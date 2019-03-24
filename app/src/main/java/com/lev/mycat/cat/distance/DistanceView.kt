package com.lev.mycat.cat.distance

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import com.lev.mycat.R
import javax.inject.Inject

class DistanceView
@Inject
constructor(
    private val appContext: Context
) {

    companion object {

        @StringRes
        private const val DEFAULT_ERROR_MESSAGE: Int =
            R.string.default_error_messtage
        private const val DEFAULT_CAT_PIC: Int = R.mipmap.ic_launcher
    }

    private lateinit var photoView: ImageView
    private lateinit var nameView: TextView
    private lateinit var distanceView: TextView
    private lateinit var awareView: TextView
    private lateinit var catProgressBar: ProgressBar
    private lateinit var distanceProgressBar: ProgressBar

    fun init(view: View) {
        photoView = view.findViewById(R.id.cat_photo)
        nameView = view.findViewById(R.id.cat_name)
        distanceView = view.findViewById(R.id.distance_value)
        awareView = view.findViewById(R.id.aware_text)
        catProgressBar = view.findViewById(R.id.cat_progress_bar)
        distanceProgressBar = view.findViewById(R.id.distance_progress_bar)
    }

    fun setName(name: String) {
        nameView.text = name
    }

    fun setDistance(meters: Int) {
        distanceView.text =
            appContext.getString(R.string.distance_meters, meters)
    }

    fun setPhoto(bitmap: Bitmap? = null) {
        bitmap?.let {
            photoView.setImageBitmap(it)
        } ?: photoView.setImageResource(DEFAULT_CAT_PIC)
    }


    fun setCatLoading(isLoading: Boolean) {
        if (isLoading) {
            distanceView.visibility = GONE
            awareView.visibility = GONE
            tryShowProgress(catProgressBar)
        } else {
            tryHideProgress(catProgressBar)
            distanceView.visibility = VISIBLE
            awareView.visibility = VISIBLE
        }
    }

    fun setDistanceLoading(isLoading: Boolean) {
        if (isLoading) {
            distanceView.visibility = GONE
            awareView.visibility = GONE
            tryShowProgress(distanceProgressBar)
        } else {
            tryHideProgress(distanceProgressBar)
            distanceView.visibility = VISIBLE
            awareView.visibility = VISIBLE
        }
    }

    fun replaceWithAddCat() {
        //TODO implement replace with add cat
    }

    private fun tryShowProgress(progressBar: ProgressBar) {
        if (distanceProgressBar.isVisible || catProgressBar.isVisible) {
            return
        } else progressBar.visibility = VISIBLE
    }

    private fun tryHideProgress(progressBar: ProgressBar) {
        if (progressBar.isVisible) {
            progressBar.visibility = GONE
            return
        }
        distanceProgressBar.visibility = GONE
        catProgressBar.visibility = GONE
    }

    fun showError(@StringRes messageId: Int = DEFAULT_ERROR_MESSAGE) =
        Toast.makeText(appContext, messageId, Toast.LENGTH_LONG).show()
}