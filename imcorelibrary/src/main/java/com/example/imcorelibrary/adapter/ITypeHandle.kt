package com.example.imcorelibrary.adapter

import android.view.View

interface ITypeHandle {
    fun createItemViewHolder(viewType: Int, view: View): BaseRecycleHolder<IEntity>

    fun getViewType(entity: IEntity): Int
}
