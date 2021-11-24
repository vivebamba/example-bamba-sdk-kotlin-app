package com.example.bambainsidekotlin.adapters

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bambainsidekotlin.R
import com.example.bambainsidekotlin.models.Service
import com.vivebamba.client.models.CustomerService
import java.time.Month

class ServicesAdapter (private val mServices: List<CustomerService>):  RecyclerView.Adapter<ServicesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serviceNameTextView: TextView = itemView.findViewById(R.id.service_name)
        val renewalDateTextView: TextView = itemView.findViewById(R.id.renewal_date)
        val switchCompat: SwitchCompat = itemView.findViewById(R.id.cancel_service_switch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_service, parent, false)

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service: CustomerService = mServices[position]
        val serviceNameTextView = holder.serviceNameTextView
        val serviceRenewalDateTextView = holder.renewalDateTextView
        serviceNameTextView.text = service.name
        val monthName: String? = service.validTo?.month?.let { esMonthName(it) }
        val finalDate = "Renovación ${service.validTo?.dayOfMonth} de  $monthName"
        serviceRenewalDateTextView.text = finalDate

        holder.switchCompat.setOnCheckedChangeListener { compoundButton, b ->
            println(b)
        }


    }

    private fun esMonthName(month: Month): String {
        return when (month) {
            Month.JANUARY -> "Enero"
            Month.FEBRUARY -> "Febrero"
            Month.MARCH -> "Marzo"
            Month.APRIL -> "Abril"
            Month.MAY -> "Mayo"
            Month.JUNE -> "Junio"
            Month.JULY -> "Julio"
            Month.AUGUST -> "Agosto"
            Month.SEPTEMBER -> "Septiembre"
            Month.OCTOBER -> "Octubre"
            Month.NOVEMBER -> "Noviembre"
            Month.DECEMBER -> "Diciembre"
        }
    }

    override fun getItemCount(): Int {
        return mServices.size
    }

}


