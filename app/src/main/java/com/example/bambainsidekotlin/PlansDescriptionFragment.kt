package com.example.bambainsidekotlin

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bambainsidekotlin.adapters.PlanDescriptionAdapter
import com.example.bambainsidekotlin.models.ParcelableProductDescription
import com.example.bambainsidekotlin.services.BambaService


private const val ARG_PLAN_SLUG = "planSlug"
private const val ARG_PLAN_PRICE = "planPrice"
private const val ARG_PLAN_NAME = "planName"
private const val ARG_PLAN_SKU = "planSku"
private const val ARG_PLAN_DESCRIPTION = "planDescription"

class PlansDescriptionFragment : Fragment() {

    private var planSlug: String? = null
    private var planPrice: Double? = null
    private var planName: String? = null
    private var planSku: String? = null
    private var planDescriptionParcelable: ArrayList<ParcelableProductDescription>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            planSlug = it.getString(ARG_PLAN_SLUG)
            planPrice = it.getDouble(ARG_PLAN_PRICE)
            planName = it.getString(ARG_PLAN_NAME)
            planSku = it.getString(ARG_PLAN_SKU)
            planDescriptionParcelable = it.getParcelableArrayList(ARG_PLAN_DESCRIPTION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_plans_description, container, false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as AppCompatActivity?)!!.title = this.planName
        val toolbar = (activity as AppCompatActivity?)?.findViewById<Toolbar>(R.id.toolbar)
        val plansFragment = PlansFragment()

        val descriptionListRecyclerView = view.findViewById(R.id.planDescription) as RecyclerView
        val adapter = planDescriptionParcelable?.let { PlanDescriptionAdapter(it) }
        descriptionListRecyclerView.adapter = adapter
        descriptionListRecyclerView.layoutManager = LinearLayoutManager(activity)

        toolbar?.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fragmentManager = getFragmentManager()
                val f = fragmentManager?.findFragmentById(R.id.flFragment)
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.apply {
                    if (f != null) {
                        replace(f.id, plansFragment)
                    }
                    commit()
                }
            }
        })
        this.setupBuyButtonListener(view)
        val resources: Resources = view.context.resources
        val priceFormat: String =
            resources.getString(
                R.string.plan__description_price_format,
                this.planPrice?.toInt().toString()
            )
        val priceView: TextView = view.findViewById(R.id.price)
        priceView.text = priceFormat

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(
            planSlug: String,
            planPrice: Double,
            planName: String,
            planDescriptionParcelable: ArrayList<ParcelableProductDescription>
        ) =
            PlansDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PLAN_SLUG, planSlug)
                    putDouble(ARG_PLAN_PRICE, planPrice)
                    putString(ARG_PLAN_NAME, planName)
                    putString(ARG_PLAN_SKU, planSlug)
                    putSerializable(ARG_PLAN_DESCRIPTION, planDescriptionParcelable)
                }
            }
    }

    private fun setupBuyButtonListener(view: View) {
        val button = view.findViewById<Button>(R.id.buy_button)
        val successfulPurchaseFragment = SuccessfulPruchaseFragment()
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                try {
                    val bambaService = BambaService()
                    bambaService.placeOrder(planSku.toString())
                    val fragmentManager = getFragmentManager()
                    val f = fragmentManager?.findFragmentById(R.id.flFragment)
                    val fragmentTransaction = fragmentManager?.beginTransaction()
                    fragmentTransaction?.apply {
                        if (f != null) {
                            replace(f.id, successfulPurchaseFragment)
                        }
                        commit()
                    }
                } catch (e: Exception) {
                    Toast.makeText(view.context, e.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}