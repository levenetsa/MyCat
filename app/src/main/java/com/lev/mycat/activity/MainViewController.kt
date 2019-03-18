package com.lev.mycat.activity

import javax.inject.Inject

class MainViewController
@Inject
constructor(private val view: MainView,
            private val model: MainModel) {

    private val modelListener = object : MainModel.OnDataChanged {
        override fun onNameChanged() {
            updateName()
        }
    }

    init{
        init()
        model.listener = modelListener
    }

    fun init(){
        if (model.isModelReady) {
            updateName()
        }
    }

    private fun updateName() {
        view.setName(model.catName)
        if (model.isNameEmpty){
            //TODO ask for name
        }
    }

    fun destroy(){
        model.listener = null
    }
}