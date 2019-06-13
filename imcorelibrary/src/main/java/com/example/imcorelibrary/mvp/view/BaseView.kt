package com.example.imcorelibrary.mvp.view

interface BaseView {
    fun showLoadingView()
    fun hideLoadingView()
    fun onError()
}