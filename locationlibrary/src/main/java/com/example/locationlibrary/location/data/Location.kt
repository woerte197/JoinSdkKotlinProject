package com.example.locationlibrary.location.data

import com.example.imcorelibrary.adapter.IEntity
import com.example.locationlibrary.Constants

data class Location(val name:String, val distance:Int,val latitude:Double,val longitude:Double ):IEntity {
    override val itemType: Int
        get() = Constants.LOCATION_ITEM_TYPE
}