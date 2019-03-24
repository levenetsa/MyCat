package com.lev.mycat.cat.add

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.lev.mycat.db.Cat
import com.lev.mycat.db.CatStore
import com.lev.mycat.navigation.NavigationController
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AddCatModel
@Inject
constructor(
    private val catStore: CatStore,
    private val navigator: NavigationController
) {

    var listener: OnDataChanged? = null

    var name: String = ""
    var photo: Bitmap? = null

    fun init() {}

    fun trySaveCat() {
        if (catValid()) {
            saveCat()
        }
        listener?.onError("Cant't save cat")
    }

    private fun saveCat() {
        catStore.save(Cat(name, bitmapToStr(photo))) {
            listener?.onCatSaved()
            navigator.navigateUp()
        }
        return
    }

    private fun catValid() = name.isNotEmpty()

    interface OnDataChanged {
        fun onCatSaved()

        fun onError(message: String)
    }
}

fun bitmapToStr(bitmap: Bitmap?): String? {
    bitmap?.let {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    } ?: return null
}

fun strToBitmap(string: String?): Bitmap? {
    string?.let {
        val decodedString = Base64.decode(it, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(
            decodedString,
            0,
            decodedString.size
        )
    } ?: return null
}