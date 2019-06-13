package com.example.imcorelibrary.mvp.persenter

import com.example.imcorelibrary.mvp.view.BaseView
import java.lang.ref.WeakReference

open class BasePresenter<T : BaseView> {
    lateinit var mView: WeakReference<T>

}