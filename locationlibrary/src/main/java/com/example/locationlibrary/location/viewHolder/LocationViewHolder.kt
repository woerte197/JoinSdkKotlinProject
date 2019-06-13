package com.example.locationlibrary.location.viewHolder

import android.view.View
import android.widget.TextView
import com.baidu.mapapi.model.LatLng
import com.example.imcorelibrary.adapter.BaseRecycleAdapter
import com.example.imcorelibrary.adapter.BaseRecycleHolder
import com.example.imcorelibrary.adapter.IEntity
import com.example.locationlibrary.R.id.mLocationTitle

import com.example.locationlibrary.location.LocationActivity
import com.example.locationlibrary.location.data.Location
import org.jetbrains.anko.find

class LocationViewHolder<T : IEntity>(itemView: View) : BaseRecycleHolder<T>(itemView) {
    override fun setView(entity: T, adapter: BaseRecycleAdapter, position: Int) {
        itemView.find<TextView>(mLocationTitle).text = (entity as Location).name
        itemView.find<TextView>(mLocationTitle).setOnClickListener {
            (mContext as LocationActivity).updateMap(LatLng((entity as Location).latitude, (entity as Location).longitude))
        }
    }
}