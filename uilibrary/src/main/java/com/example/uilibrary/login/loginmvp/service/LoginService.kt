package com.example.uilibrary.login.loginmvp.service

import com.example.imcorelibrary.rx.BaseResponse
import com.example.uilibrary.login.loginmvp.request.LoginRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/login")
    fun login(@Body loginRequest: LoginRequest): Observable<BaseResponse<String>>

}