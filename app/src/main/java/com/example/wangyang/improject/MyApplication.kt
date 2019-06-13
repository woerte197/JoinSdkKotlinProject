package com.example.wangyang.improject

import android.app.Application
import android.os.Environment
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.example.imcorelibrary.BaseConstants
import com.tencent.qcloud.uikit.BaseUIKitConfigs
import com.tencent.qcloud.uikit.TUIKit
import com.tencent.imsdk.TIMManager
import android.os.Environment.getExternalStorageDirectory
import com.tencent.imsdk.TIMLogLevel
import com.tencent.imsdk.TIMSdkConfig
import com.tencent.imsdk.session.SessionWrapper



class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SDKInitializer.initialize(this)
//        自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
//        包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL)
        if (SessionWrapper.isMainProcess(applicationContext)) {
            val config = TIMSdkConfig(BaseConstants.SDKAPPID)
                    .enableLogPrint(true)
                    .setLogLevel(TIMLogLevel.DEBUG)
                    .setLogPath(Environment.getExternalStorageDirectory().getPath() + "/justfortest/")
            //初始化 SDK
            TIMManager.getInstance().init(applicationContext, config)
        }
    }
}