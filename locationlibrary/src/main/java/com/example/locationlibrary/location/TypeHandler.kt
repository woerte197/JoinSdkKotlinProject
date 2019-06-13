package com.example.locationlibrary.location

import android.view.View
import com.example.imcorelibrary.adapter.BaseRecycleHolder
import com.example.imcorelibrary.adapter.IEntity
import com.example.imcorelibrary.adapter.ITypeHandle
import com.example.locationlibrary.Constants
import com.example.locationlibrary.R
import com.example.locationlibrary.location.viewHolder.LocationViewHolder

class TypeHandler : ITypeHandle {
    companion object {
        val ITEM_LOCATION_LAYOUT = R.layout.item_location_layout
    }

    override fun createItemViewHolder(viewType: Int, view: View): BaseRecycleHolder<IEntity> {
        when (viewType) {
            ITEM_LOCATION_LAYOUT -> return LocationViewHolder(view)
        }
        throw NullPointerException("null")
    }

    override fun getViewType(entity: IEntity): Int {
        when (entity.itemType) {
            Constants.LOCATION_ITEM_TYPE -> return ITEM_LOCATION_LAYOUT
        }
        return 0
    }
}