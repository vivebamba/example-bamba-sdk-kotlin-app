package com.example.bambainsidekotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val planSlugs = arrayOf("plan-basico", "plan-medio", "plan-alto")

    private val planNames = arrayOf("Plan básico",
        "Plan medio", "Plan alto")

    private val planPrices = arrayOf("$50 MXN al mes.", "$90MXN al mes.",
        "$150 MXN al mes.")


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

        var planName: TextView = itemView.findViewById(R.id.planName)
        var planPrice: TextView = itemView.findViewById(R.id.planPrice)
        var planDescriptionFragment =  PlansDescriptionFragment();
        init {

            itemView.setOnClickListener {
                var position: Int = absoluteAdapterPosition
                val index = position - 1;
                val slug = planSlugs[index];
                val planName = planNames[index];
                val planPrice = planPrices[index];
                val bundle = Bundle()
                bundle.putString("planSlug", slug)
                bundle.putString("planPrice", planPrice)
                bundle.putString("planName", planName)
                planDescriptionFragment.arguments = bundle

                val context = itemView.context
                val fragmentManager: FragmentManager =
                    (context as FragmentActivity).supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.flFragment, planDescriptionFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }
    }

}
