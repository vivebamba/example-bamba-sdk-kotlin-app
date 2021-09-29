package com.example.bambainsidekotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bambainsidekotlin.R
import com.example.bambainsidekotlin.models.ParcelableProductDetails

class PlanDescriptionDetailsAdapter(private val descriptionSubtitles: List<ParcelableProductDetails>) :
    RecyclerView.Adapter<PlanDescriptionDetailsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val descriptionSubTitleTextView: TextView = itemView.findViewById(R.id.description_subtitle_text_view)
        val detailItemRecyclerView: RecyclerView = itemView.findViewById(R.id.plan_description_details_item_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val linearLayoutView = inflater.inflate(R.layout.description_card_subtitle, parent, false)

        return ViewHolder(linearLayoutView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.descriptionSubTitleTextView.context
        val planDescriptionDetails = descriptionSubtitles[position]
        val planDescriptionsTextView = holder.descriptionSubTitleTextView
        planDescriptionsTextView.text = planDescriptionDetails.head

        val detailItemRecyclerView = holder.detailItemRecyclerView
        val detailItemsAdapter = planDescriptionDetails.body?.let {
            PlanDescriptionDetailItemsAdapter(
                it
            )
        }
        detailItemRecyclerView.adapter = detailItemsAdapter
        detailItemRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun getItemCount(): Int {
        return descriptionSubtitles.size
    }
}