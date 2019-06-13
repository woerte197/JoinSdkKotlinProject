package com.example.locationlibrary.location

import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.mapapi.map.MyLocationData
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.map.MapStatusUpdate
import com.baidu.mapapi.model.LatLng




class MyLocationListener : BDAbstractLocationListener() {
    private var mLocDataListener: LocDataListener? = null
    override fun onReceiveLocation(location: BDLocation?) {
        if (location == null) {
            return
        }
        val locData = MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(location.getDirection()).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build()
        val ll = LatLng(location.getLatitude(), location.getLongitude())
        val msu = MapStatusUpdateFactory.newLatLng(ll)
        mLocDataListener!!.locData(locData,msu)
    }

    fun setLocDataListener(mLocDataListener: LocDataListener) {
        this.mLocDataListener = mLocDataListener
    }

    interface LocDataListener {
        fun locData(locData: MyLocationData,msu:MapStatusUpdate)

    }
}