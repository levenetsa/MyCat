package com.lev.mycat.cat.add

import android.graphics.Bitmap
import android.view.View
import com.lev.mycat.activity.MainActivity
import javax.inject.Inject

class AddCatViewController
@Inject
constructor(private val fragmentView: AddCatView,
            private val photoModel: AddCatPhotoModel,
            private val model: AddCatModel,
            private val activity: MainActivity) {

    private val modelListener = object : AddCatModel.OnDataChanged {
        override fun onCatSaved() {
            //TODO startSaveCompleteAnim
        }

        override fun onError(message: String) {
            fragmentView.showError(message)
        }
    }

    private val photoModelListener = object : AddCatPhotoModel.OnImageSelected {
        override fun onPhotoChanged(bitmap: Bitmap) {
            fragmentView.setPhoto(bitmap)
            model.photo = bitmap
        }

        override fun onError(code: Int) {
            fragmentView.showError("Code from pic intent: $code")
        }
    }

    private val viewListener = object : AddCatView.OnViewInteraction {
        override fun onNameChanged(newName: String) {
            model.name = newName
        }

        override fun onSaveClicked() {
            model.trySaveCat()
        }

        override fun onTakePhotoClicked() {
            photoModel.takePhoto()
        }

        override fun onChoosePhotoClicked() {
            photoModel.choosePhoto()

        }
    }

    fun init(view: View){
        fragmentView.init(view)
        model.init()
        photoModel.init(activity)


        fragmentView.listener = viewListener
        model.listener = modelListener
        photoModel.listener = photoModelListener

        fragmentView.setName(model.name)
        model.photo?.let { fragmentView.setPhoto(it) }
    }

    fun destroy() {
        photoModel.destroy()

        model.listener = null
        fragmentView.listener = null
        photoModel.listener = null
    }
}