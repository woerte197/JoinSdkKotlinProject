package com.example.imcorelibrary.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.google.gson.Gson

abstract class BaseRecycleHolder<T : IEntity>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    protected var mContext: Context = itemView.context

    abstract fun setView(entity: T, adapter: BaseRecycleAdapter, position: Int)

    protected fun tosMsg(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun gson(): Gson {
        return Gson()
    }
}
