package com.lev.mycat.cat.add

import com.lev.mycat.db.Cat
import com.lev.mycat.db.CatStore
import com.lev.mycat.navigation.NavigationController
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
    var photo: String? = null
        private set

    fun init() {}

    fun saveCat() {
        if (catValid()) {
            catStore.save(Cat(name)) {
                listener?.onCatSaved()
                navigator.navigateUp()
            }
            return
        }
        listener?.onError("Cant't save cat")
    }

    private fun catValid() = name.isNotEmpty()

    interface OnDataChanged {
        fun onCatSaved()

        fun onError(message: String)
    }
}