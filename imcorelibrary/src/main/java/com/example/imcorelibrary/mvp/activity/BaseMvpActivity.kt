package com.example.imcorelibrary.mvp.activity

import com.example.imcorelibrary.mvp.persenter.BasePresenter
import com.example.imcorelibrary.mvp.view.BaseView

abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    lateinit var mPresenter: T

    override fun hideLoadingView() {
    }

    override fun showLoadingView() {
    }

    override fun onError() {

    }


}