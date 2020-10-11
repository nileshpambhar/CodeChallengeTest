package com.mobiquitytest.demo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobiquitytest.demo.R
import com.mobiquitytest.demo.data.room.entity.CityEntity
import com.mobiquitytest.demo.databinding.ItemCityBinding

class CityListAdapter internal constructor(
    private val context: Context,
    private var onAdapterClickHandler: OnAdapterClickHandler
) : RecyclerView.Adapter<CityListAdapter.Holder>() {
    private var cities = emptyList<CityEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.item_city, parent, false))
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding?.let {
            val city = cities[holder.adapterPosition]

            if (city.name.equals("Ahmedabad") && city.latitude.equals("23.0225") &&
                city.longitude.equals("72.5714")
            ) {
                it.ivRemove.visibility = View.INVISIBLE
            } else {
                it.ivRemove.visibility = View.VISIBLE
            }
            it.city = city
            it.root.setOnClickListener {
                onAdapterClickHandler.onItemClick(holder.adapterPosition)
            }
            it.ivRemove.setOnClickListener {
                onAdapterClickHandler.onRemoveClick(holder.adapterPosition)
            }
        }
    }

    internal fun setCities(cities: List<CityEntity>) {
        this.cities = cities
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemCityBinding? = DataBindingUtil.bind(itemView)
    }

    interface OnAdapterClickHandler {
        fun onItemClick(position: Int)
        fun onRemoveClick(position: Int)
    }


}