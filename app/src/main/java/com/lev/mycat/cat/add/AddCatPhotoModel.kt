package com.lev.mycat.cat.add

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import com.lev.mycat.activity.MainActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddCatPhotoModel
@Inject
constructor() {

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    private val resultListener = object : MainActivity.ResultListener {
        override fun onResult(
            requestCode: Int,
            resultCode: Int,
            data: Intent?
        ) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                isWaitingPic =false
                if (resultCode == RESULT_OK) {
                    val imageBitmap = data!!.extras!!.get("data") as Bitmap
                    listener?.onPhotoChanged(imageBitmap)
                } else {
                    listener?.onError(resultCode)
                }
            }
        }
    }

    var listener: OnImageSelected? = null
    var isWaitingPic = false
    private var activity: MainActivity? = null

    fun init(activity: MainActivity) {
        this.activity = activity
        if (isWaitingPic) {
            subscribeOnActivity()
        }
    }

    fun destroy() {
        activity?.removeListener(resultListener)
        activity = null
    }

    fun takePhoto() {
        isWaitingPic = true
        subscribeOnActivity()
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(activity?.packageManager!!)
                ?.also {
                    activity?.startActivityForResult(
                        takePictureIntent,
                        REQUEST_IMAGE_CAPTURE
                    )
                }
        }
    }

    fun choosePhoto() {

    }

    private fun subscribeOnActivity() {
        activity?.addListener(resultListener)
    }

    interface OnImageSelected {
        fun onPhotoChanged(bitmap: Bitmap)
        fun onError(code: Int)
    }
}