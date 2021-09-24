package com.example.bambainsidekotlin.adapters

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.bambainsidekotlin.PlansDescriptionFragment
import com.example.bambainsidekotlin.R
import com.vivebamba.client.models.Product

class PlansAdapter (private val plans: ArrayList<Product>): RecyclerView.Adapter<PlansAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var planNameView: TextView = itemView.findViewById(R.id.planName)
        var planPriceView: TextView = itemView.findViewById(R.id.planPrice)

        var planDescriptionFragment =  PlansDescriptionFragment();
        init {

            itemView.setOnClickListener {
                var position: Int = absoluteAdapterPosition
                val index = position - 1
                val selectedPlan: Product =plans[index]
                val slug = selectedPlan.sku
                val planName = selectedPlan.name
                val planPrice = selectedPlan.price
                val planSku = selectedPlan.sku
                val bundle = Bundle()
                bundle.putString("planSlug", slug)
                if (planPrice != null) {
                    bundle.putDouble("planPrice", planPrice)
                }
                bundle.putString("planName", planName)
                bundle.putString("planSku", planSku)
                planDescriptionFragment.arguments = bundle

                val context = itemView.context
                val fragmentManager: FragmentManager =
                    (context as FragmentActivity).supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.flFragment, planDescriptionFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }
    }

    override fun getItemCount(): Int {
       return plans.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val cardView = inflater.inflate(R.layout.card, parent, false)

        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plan: Product = plans[position]
        val planNameTextView = holder.planNameView
        val planPriceTextView = holder.planPriceView
        val resources: Resources = planPriceTextView.context.resources
        val priceFormat: String =
           resources.getString(R.string.plan_price_format, plan.price?.toInt().toString())
        planNameTextView.text = plan.name
        planPriceTextView.text = priceFormat
    }
}