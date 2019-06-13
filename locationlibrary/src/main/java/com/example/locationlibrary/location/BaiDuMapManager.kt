package com.example.locationlibrary.location

import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MyLocationData

class BaiDuMapManager private constructor() {
    private var mBaiduMap: BaiduMap? = null

    companion object {
        val instance: BaiDuMapManager by lazy { BaiDuMapManager() }
    }

     fun addBaiduMap(mBaiduMap: BaiduMap): BaiDuMapManager {
        this.mBaiduMap = mBaiduMap
        return this
    }

     fun addMapType(mapType: Int): BaiDuMapManager {
        this.mBaiduMap!!.mapType = mapType
        return this
    }

     fun isMyLocationEnabled(locationEnabled: Boolean): BaiDuMapManager {
        this.mBaiduMap!!.isMyLocationEnabled = locationEnabled
        return this
    }
    fun  setMyLocationData(lcoationData: MyLocationData): BaiDuMapManager{
        this.mBaiduMap!!.setMyLocationData(lcoationData)
        return this
    }

}


