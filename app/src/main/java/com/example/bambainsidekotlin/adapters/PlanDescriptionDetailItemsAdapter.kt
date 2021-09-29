package com.example.bambainsidekotlin.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bambainsidekotlin.R

class PlanDescriptionDetailItemsAdapter(private val detailItems: List<String>):
    RecyclerView.Adapter<PlanDescriptionDetailItemsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val descriptionDetailItemTextView: TextView = itemView.findViewById(R.id.description_detail_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val layoutId: Int = R.layout.description_details_item
        val linearLayoutView = inflater.inflate(layoutId, parent, false)

        return ViewHolder(linearLayoutView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val detailsItem = detailItems[position]
        val descriptionDetailItemTextView = holder.descriptionDetailItemTextView
        val resources: Resources = descriptionDetailItemTextView.context.resources
        val detailsItemFormat: String =
            resources.getString(R.string.description_detail_item_format, detailsItem)
        descriptionDetailItemTextView.text = detailsItemFormat

    }

    override fun getItemCount(): Int {
        return  detailItems.size
    }
}