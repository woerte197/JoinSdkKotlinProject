package com.example.uilibrary.login.loginmvp

import android.content.Intent
import android.os.Bundle
import com.example.imcorelibrary.mvp.activity.BaseMvpActivity
import com.example.locationlibrary.location.LocationActivity
import com.example.uilibrary.R
import com.example.uilibrary.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView {
    private var userSig_1 = "eJxlj1FPgzAUhd-5FQ2vM64tK1MTHyYzkTnKcE6iL01HCzTLWIU6ZMb-vokam3iT*-R9OSfnwwEAuI-z5TnPst1bZZjptHTBFXChe-YHtVaCccO8WvyD8l2rWjKeG1n3EBFCMIS2o4SsjMrVj9HyquhObxmN2LC*5jtiBCFGPvIubEUVPYxuV0GYBBN6s2qo7ui4mE516A-uRrgZS0STMj0EtEvT-H6m97OSt2GRpWQyeBnSZcnpPHxYDLOctOiViMMmMk-beB2LRSxUtH5Orq1Ko7byd9Olj4mH7VV7WTdqV-UChogg7MGvc51P5wh5iV5E"
    private var userSig_2 = "eJxlj1FPgzAUhd-5FYRn41paGPg2CSiLqGRFM1*aAt3aLeu6rhubxv*uokYS7*v3nZxz3xzXdT1yN7tkTbM9KEvtWXPPvXI94F38Qa1lS5mlyLT-ID9paThlC8tND2EQBD4AQ0e2XFm5kD9Gx9RyQPftmvYV33EMgA9DiKKhIpc9LNJ5kpdJjW-4*EhWa5M38paNZl1V6w3r0jIdz58yFO1OFZne81U8ycXkQVxX*OU1zAoSitA3IsbksHscFVktFIdloJ*neVoAhJNBpZUb-vtPHH2uiuIBPXKzl1vVCz6AAfQR*DrPeXc*AGnZW9c_"
    private var userId_1 = "wangyang"
    private var userId_2 = "wang"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginPresenter = LoginPresenter()
        mPresenter = loginPresenter
        mPresenter.mView = this
        initEvent()
    }

    private fun initEvent() {
        loginim_btn.setOnClickListener {
            mPresenter.imloginForDev(userId_2, userSig_2, true)
        }
    }

    override fun loginSuccess() {
        val intent = Intent(this, LocationActivity::class.java)
        startActivity(intent)
        finish()
    }
}
