package com.example.locationlibrary.location

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.example.imcorelibrary.adapter.BaseRecycleAdapter
import com.example.imcorelibrary.adapter.IEntity
import com.example.imcorelibrary.mvp.activity.BaseMvpActivity
import com.example.locationlibrary.R
import kotlinx.android.synthetic.main.activity_location.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import java.lang.ref.WeakReference


class LocationActivity : BaseMvpActivity<LocationPresenter>(), LocationView {
    private var mBaiduMap: BaiduMap? = null
    private var mLinearLayout: LinearLayout? = null
    private var mInfoWindow: InfoWindow? = null
    private var marker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        mPresenter = LocationPresenter()
        mPresenter.mView = WeakReference(this)
        initView()
        initEvent()
        initLocationClient()
    }

    private fun initLocationClient() {
        mPresenter.sendLocationClient()
    }

    private fun initView() {
        mLinearLayout = LayoutInflater.from(this).inflate(R.layout.baidu_infowindow, null) as LinearLayout
        val builder = MapStatus.Builder()
        builder.zoom(18.0f)
        mBaiduMap = mMapView.map
        mBaiduMap!!.isMyLocationEnabled = true
        mBaiduMap!!.mapType = BaiduMap.MAP_TYPE_NORMAL
        mBaiduMap!!.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()))
        mBaiduMap!!.setOnMapClickListener(mPresenter)
    }

    private fun initEvent() {
        mLinearLayout!!.find<TextView>(R.id.title).setOnClickListener {
            toast("点击文字")
        }
        mLinearLayout!!.find<ImageView>(R.id.tel).setOnClickListener {
            mPresenter.tel()
        }
    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun onDestroy() {
        mPresenter.stopLocationClient()
        mBaiduMap!!.isMyLocationEnabled = false
        mMapView.onDestroy()
        mPresenter.onDestroy()
        super.onDestroy()
    }

    fun showLocData(locationData: MyLocationData, msu: MapStatusUpdate) {
        this.runOnUiThread {
            mBaiduMap!!.setMyLocationData(locationData)
            mBaiduMap!!.animateMapStatus(msu)
            val point = LatLng(locationData.latitude, locationData.longitude)
            mInfoWindow = InfoWindow(mLinearLayout, point, 10)
            mBaiduMap!!.showInfoWindow(mInfoWindow)
        }
    }

    fun showMapClick(option: MarkerOptions) {
        if (marker != null) {
            marker!!.remove()
        }
        marker = mBaiduMap!!.addOverlay(option) as Marker?
    }

    fun updateMap(latLng: LatLng) {
        mPresenter.updateMapClick(latLng)
    }

    fun showLocationList(list: MutableList<IEntity>) {
        mLocationRecycleView.layoutManager = LinearLayoutManager(this)
        mLocationRecycleView.adapter = BaseRecycleAdapter(this, list, TypeHandler())
    }

}
