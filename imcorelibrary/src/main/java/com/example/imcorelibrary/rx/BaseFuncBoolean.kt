package com.example.baselibrary.rx

import com.example.imcorelibrary.rx.BaseResponse
import io.reactivex.Observable
import io.reactivex.functions.Function

class BaseFuncBoolean<T> : Function<BaseResponse<T>, Observable<Boolean>> {
    override fun apply(t: BaseResponse<T>): Observable<Boolean> {
        if (t.status != 0) {
            return Observable.error(BaseExecption(t.status, t.message))
        }
        return Observable.just(true)
    }


}