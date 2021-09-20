package com.example.bambainsidekotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bambainsidekotlin.R
import com.example.bambainsidekotlin.models.Service

class ServicesAdapter (private val mServices: List<Service>):  RecyclerView.Adapter<ServicesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serviceNameTextView: TextView = itemView.findViewById(R.id.service_name)
        val renewalDateTextView: TextView = itemView.findViewById(R.id.renewal_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_service, parent, false)

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service: Service = mServices[position]
        val serviceNameTextView = holder.serviceNameTextView
        val serviceRenewalDateTextView = holder.renewalDateTextView
        serviceNameTextView.text = service.name
        serviceRenewalDateTextView.text = service.renewalDate
    }

    override fun getItemCount(): Int {
        return mServices.size
    }

}


