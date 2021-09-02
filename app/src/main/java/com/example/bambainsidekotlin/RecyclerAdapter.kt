package com.example.bambainsidekotlin

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val planNames = arrayOf("Plan básico",
        "Plan medio", "Plan alto")

    private val planPrices = arrayOf("$50 MXN", "$90MXN",
        "$150 MXN")


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.planName.text = planNames[i]
        viewHolder.planPrice.text = planPrices[i]

    }

    override fun getItemCount(): Int {
        return planNames.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var planName: TextView
        var planPrice: TextView

        init {
            planName = itemView.findViewById(R.id.planName)
            planPrice = itemView.findViewById(R.id.planPrice)

//            itemView.setOnClickListener {
//                var position: Int = getAdapterPosition()
//                val context = itemView.context
//                val intent = Intent(context, DetailPertanyaan::class.java).apply {
//                    putExtra("NUMBER", position)
//                    putExtra("CODE", itemKode.text)
//                    putExtra("CATEGORY", itemKategori.text)
//                    putExtra("CONTENT", itemIsi.text)
//                }
//                context.startActivity(intent)
//            }
        }
    }
}

