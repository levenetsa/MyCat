package com.lev.mycat.cat.add

import android.view.View
import javax.inject.Inject

class AddCatViewController
@Inject
constructor(private val fragmentView: AddCatView,
            private val model: AddCatModel) {

    private val modelListener = object : AddCatModel.OnDataChanged {
        override fun onCatSaved() {
            //TODO startSaveCompleteAnim
        }

        override fun onError(message: String) {
            fragmentView.showError(message)
        }

    }

    private val viewListener = object : AddCatView.OnViewInteraction {
        override fun onNameChanged(newName: String) {
            model.name = newName
        }

        override fun onSaveClicked() {
            model.saveCat()
        }

        override fun onTakePhotoClicked() {
            //photoModel.takePhoto()
        }

        override fun onChoosePhotoClicked() {
            //photoModel.choosePhoto()

        }
    }

    fun init(view: View){
        fragmentView.init(view)
        model.init()
        model.listener = modelListener
        fragmentView.listener = viewListener
    }

    fun destroy() {
        model.listener = null
        fragmentView.listener = null
    }
}