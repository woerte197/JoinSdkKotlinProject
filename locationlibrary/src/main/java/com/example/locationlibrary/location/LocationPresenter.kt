package com.example.locationlibrary.location

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.baidu.mapapi.search.poi.*
import com.example.imcorelibrary.adapter.IEntity
import com.example.imcorelibrary.mvp.persenter.BasePresenter
import com.example.locationlibrary.R
import com.example.locationlibrary.location.data.Location


class LocationPresenter : BasePresenter<LocationView>(), BaiduMap.OnMapClickListener {

    var mLocationClient: LocationClient? = null
    var myLocationListener = MyLocationListener()
    /*
    * 设置定位参数并开启定位
    * */
    fun sendLocationClient() {
        mLocationClient = LocationClient(mView.get() as Context)

        val option = LocationClientOption()                //通过LocationClientOption设置LocationClient相关参数

        option.isOpenGps = true                            // 打开gps

        option.setIsNeedAddress(true)
        option.setCoorType("bd09ll")                       // 设置坐标类型

        //option.setScanSpan(1000)
        mLocationClient!!.locOption = option               //设置locationClientOption

        myLocationListener.setLocDataListener(object : MyLocationListener.LocDataListener {

            override fun locData(locData: MyLocationData, msu: MapStatusUpdate) {

                (mView.get() as LocationActivity).showLocData(locData, msu)

                nearbyPoiSearch(locData)
            }
        })

        mLocationClient!!.registerLocationListener(myLocationListener)   //注册LocationListener监听器

        mLocationClient!!.start()                           //开启地图定位图层
    }

    /*
    * 停止定位
    * */
    fun stopLocationClient() {
        mLocationClient!!.stop()
        mLocationClient!!.unRegisterLocationListener(myLocationListener)
    }

    /*
    * 打电话
    * */
    fun tel() {
        var intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 181231122))
        (mView.get() as Activity).startActivity(intent)
    }

    /*
    * 搜索附近
    * */
    fun nearbyPoiSearch(locData: MyLocationData) {

        val latLng = LatLng(locData.latitude, locData.longitude)

        this.nearbyPoiSearch(latLng)

    }

    /*
    * 搜索附近
    * */
    private fun nearbyPoiSearch(latLng: LatLng) {
        val poiSearch = PoiSearch.newInstance()                             //创建poi检索实例

        val poiListener = object : OnGetPoiSearchResultListener {                      //创建poi监听者
            override fun onGetPoiDetailResult(p0: PoiDetailResult?) {
            }

            override fun onGetPoiDetailResult(p0: PoiDetailSearchResult?) {
            }

            override fun onGetPoiIndoorResult(p0: PoiIndoorResult?) {
            }

            override fun onGetPoiResult(poiResult: PoiResult?) {
                (mView.get()  as LocationActivity).showLocationList(dealResult(poiResult))
            }
        }

        poiSearch.setOnGetPoiSearchResultListener(poiListener)                     //设置poi监听者该方法要先于检索方法searchNearby(PoiNearbySearchOption)前调用，否则会在某些场景出现拿不到回调结果的情况

        val nearbySearchOption = PoiNearbySearchOption()   //设置请求参数
                .keyword("大厦")                                                   //检索关键字
                .location(latLng)                                                  //检索位置
                .pageNum(0)                                                        //分页编号，默认是0页
                .pageCapacity(20)                                                  //设置每页容量，默认10条
                .radius(100)                                                       //附近检索半径

        poiSearch.searchNearby(nearbySearchOption)                                 //发起请求

        poiSearch.destroy()                                                        //释放检索对象

    }


    private fun dealResult(poiResult: PoiResult?): MutableList<IEntity> {
        val list: MutableList<IEntity> = ArrayList()

        if (poiResult != null && poiResult.allPoi != null && !poiResult.allPoi.isEmpty()) {

            for (PoiInfo in poiResult.allPoi) {

                val location = Location(PoiInfo.name, PoiInfo.distance, PoiInfo.location.latitude, PoiInfo.location.longitude)

                list.add(location)
            }
        }

        return list
    }

    /*
    * 单击地图回调
    * */
    override fun onMapClick(p0: LatLng?) {
        p0 ?: return
        updateMapClick(p0)
    }

    /*
    * 当点击地图时更新
    * */
    fun updateMapClick(p0: LatLng) {

        val bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.click_location)

        val option = MarkerOptions()
                .position(p0)
                .perspective(true)
                .icon(bitmap)

        (mView.get() as LocationActivity).showMapClick(option)

        nearbyPoiSearch(p0)
    }

    override fun onMapPoiClick(p0: MapPoi?): Boolean {
        return false
    }

    fun onDestroy() {
       mView.clear()

    }

}