package com.lev.mycat.db

open class Cat(
    @Transient open var name: String,
    var picture: String? = null
)