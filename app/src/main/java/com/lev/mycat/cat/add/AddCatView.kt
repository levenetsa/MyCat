package com.lev.mycat.cat.add

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.lev.mycat.R
import javax.inject.Inject

class AddCatView
@Inject
constructor(
    private val appContext: Context
) {

    var listener: OnViewInteraction? = null

    private lateinit var catPhoto: ImageView
    private lateinit var catNameLayout: TextInputLayout
    private lateinit var catName: EditText
    private lateinit var takePhotoButton: ImageButton
    private lateinit var chooseGalleryPhotoButton: ImageButton
    private lateinit var addCatButton: ImageButton

    fun init(view: View) {
        catPhoto = view.findViewById(R.id.cat_photo)
        catNameLayout = view.findViewById(R.id.cat_name_layout)
        catName = view.findViewById(R.id.cat_name_value)
        takePhotoButton = view.findViewById(R.id.take_camera_photo)
        chooseGalleryPhotoButton = view.findViewById(R.id.choose_gallery_photo)
        addCatButton = view.findViewById(R.id.cat_add_button)

        catName.setTextChangedListener { s -> listener?.onNameChanged(s) }
        addCatButton.setOnClickListener { listener?.onSaveClicked() }
        takePhotoButton.setOnClickListener { listener?.onTakePhotoClicked() }
        chooseGalleryPhotoButton.setOnClickListener { listener?.onChoosePhotoClicked() }
    }

    fun showError(message: String) {
        Toast.makeText(appContext, message, Toast.LENGTH_LONG).show()
    }

    interface OnViewInteraction {
        fun onNameChanged(newName: String)
        fun onSaveClicked()
        fun onTakePhotoClicked()
        fun onChoosePhotoClicked()
    }
}

fun EditText.setTextChangedListener(callback: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {
        }

        override fun onTextChanged(
            s: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) {
            callback(s.toString())
        }
    })
}