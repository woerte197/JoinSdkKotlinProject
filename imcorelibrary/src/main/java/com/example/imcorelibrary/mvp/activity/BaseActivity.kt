package com.example.imcorelibrary.mvp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.imcorelibrary.R

open  class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        actionBar?.hide()
    }
}
