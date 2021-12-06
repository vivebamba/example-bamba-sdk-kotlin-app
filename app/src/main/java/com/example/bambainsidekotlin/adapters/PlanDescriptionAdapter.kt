package com.example.bambainsidekotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bambainsidekotlin.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.vivebamba.client.models.ProductDescription


class PlanDescriptionAdapter(private val planDescriptions: ArrayList<ProductDescription>):
    RecyclerView.Adapter<PlanDescriptionAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val descriptionTextView: TextView = itemView.findViewById(R.id.description_title)
        val detailItemRecyclerView: RecyclerView = itemView.findViewById(R.id.plan_description_details_item_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val cardView = inflater.inflate(R.layout.description_card, parent, false)

        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.descriptionTextView.context
        val planDescription = planDescriptions[position]
        val planDescriptionsTextView = holder.descriptionTextView
        planDescriptionsTextView.text = planDescription.section

        val detailItemRecyclerView = holder.detailItemRecyclerView
        val detailItemsAdapter = planDescription.body?.let {
            PlanDescriptionDetailItemsAdapter(
                it
            )
        }
        detailItemRecyclerView.adapter = detailItemsAdapter
        detailItemRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun getItemCount(): Int {
        return planDescriptions.size
    }
}