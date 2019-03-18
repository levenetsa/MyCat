package com.lev.mycat.activity

import com.lev.mycat.db.Cat
import com.lev.mycat.db.CatStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainModel
@Inject
constructor(private val catStore: CatStore) {

    companion object {
        const val EMPTY_NAME = "Please provide your cat name"
    }

    var catName = ""
    set(value) {
        field = value
        isModelReady = true
    }
    var isModelReady = false
    var isNameEmpty = true

    var listener: OnDataChanged? = null

    init {
        loadCat()
    }

    fun addCat(name: String) = catStore.save(Cat(name)) { loadCat() }

    private fun loadCat() {
        catStore.load { cat: Cat? ->
            isNameEmpty = cat == null
            if (isNameEmpty) {
                catName = EMPTY_NAME
            } else {
                catName = cat!!.name
            }
            listener?.onNameChanged()
        }
    }

    interface OnDataChanged {
        fun onNameChanged()
    }
}