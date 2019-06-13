package com.example.uilibrary.login.loginmvp

import com.example.imcorelibrary.mvp.persenter.BasePresenter
import com.tencent.imsdk.*
import com.tencent.imsdk.ext.message.TIMConversationExt
import com.tencent.imsdk.ext.message.TIMMessageExt
import java.util.*

class LoginPresenter : BasePresenter<LoginView>() {

    fun login() {
//        RetrofitFactory.instance.create(LoginService::class.java).login(LoginRequest("",""))
//                .convertBoolean().execute(object: BaseSubscribe<Boolean>() {
//                    override fun onNext(t: Boolean) {
//
//                    }
//                })
//        TIMManager.getInstance().login()

    }

    fun imloginForDev(name: String, userSig: String, autoJoin: Boolean) {
        TIMManager.getInstance().login(name, userSig, object : TIMCallBack {
            override fun onSuccess() {
                if (autoJoin) {
                    autoJoin()
                }
                mView.loginSuccess()
            }

            override fun onError(p0: Int, p1: String?) {
                mView.onError()
            }
        })
    }

    private fun autoJoin() {
        val msgs = ArrayList<TIMMessage>()
        val ext = TIMConversationExt(TIMManager.getInstance().getConversation(TIMConversationType.C2C, "腾讯云通信团队"))
        val TIMMsg = TIMMessage()
        val msgExt = TIMMessageExt(TIMMsg)
        val ele = TIMTextElem()
        ele.text = "欢迎使用腾讯云TUIKit，祝您体验愉快。"
        TIMMsg.addElement(ele)
        msgs.add(TIMMsg)
        msgExt.convertToImportedMsg()
        msgExt.setSender("TUIKit消息测试")
        msgExt.setTimestamp(System.currentTimeMillis() / 1000)
        ext.importMsg(msgs)

        TIMGroupManager.getInstance().applyJoinGroup("developers@Android", "", object : TIMCallBack {
            override fun onError(code: Int, desc: String) {
            }

            override fun onSuccess() {
            }
        })
    }

}