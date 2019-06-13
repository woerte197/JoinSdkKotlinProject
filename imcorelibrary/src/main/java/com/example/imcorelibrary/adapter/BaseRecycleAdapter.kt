package com.example.imcorelibrary.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class BaseRecycleAdapter(val context: Context, private val entities: MutableList<IEntity>, private val typeHandle: ITypeHandle) : RecyclerView.Adapter<BaseRecycleHolder<IEntity>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecycleHolder<IEntity> {
        val itemView = LayoutInflater.from(context).inflate(viewType, parent, false)
        return typeHandle.createItemViewHolder(viewType, itemView)
    }

    override fun onBindViewHolder(holder: BaseRecycleHolder<IEntity>, position: Int) {
        holder.setView(entities[position], this, position)
    }

    override fun getItemViewType(position: Int): Int {
        val viewType = typeHandle.getViewType(entities[position])
        return if (viewType != -1) {
            viewType
        } else {
            throw RuntimeException("ViewType Is Not Be Exist!")
        }
    }

    override fun getItemCount(): Int {
        return entities.size
    }
}
