package com.lev.mycat.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
class CatEntity(
    @PrimaryKey override var name: String,
    picture: String?
) : Cat(name, picture) {
    constructor(cat: Cat) : this(
        cat.name,
        cat.picture
    )
}