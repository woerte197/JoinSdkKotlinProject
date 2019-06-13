package com.example.baselibrary.rx

import com.example.imcorelibrary.rx.BaseResponse
import io.reactivex.Observable
import io.reactivex.functions.Function

class BaseFunc<T> : Function<BaseResponse<T>, Observable<T>> {
    override fun apply(t: BaseResponse<T>): Observable<T> {
        if (t.status != 0) {
            return Observable.error(BaseExecption(t.status, t.message))
        }
        return Observable.just(t.data)
    }


}