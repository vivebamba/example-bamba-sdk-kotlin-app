package com.example.bambainsidekotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PLAN_SLUG = "planSlug"
private const val ARG_PLAN_PRICE = "planPrice"
private const val ARG_PLAN_NAME ="planName"

/**
 * A simple [Fragment] subclass.
 * Use the [PlansDescriptionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlansDescriptionFragment : Fragment() {

    private var planSlug: String? = null
    private var planPrice: String? = null
    private var planName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            planSlug = it.getString(ARG_PLAN_SLUG)
            planPrice = it.getString(ARG_PLAN_PRICE)
            planName = it.getString(ARG_PLAN_NAME)
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
        toolbar?.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fragmentManager = getFragmentManager()
                val f = fragmentManager?.findFragmentById(R.id.flFragment)
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.apply {
                    if (f != null) {
                        replace(f.id,plansFragment)
                    }
                    commit()
                }
            }
        })
        val priceView: TextView = view.findViewById(R.id.price)
        priceView.text = this.planPrice
        val myWebView: WebView = view.findViewById(R.id.webView)
        myWebView.loadUrl("file:///android_asset/" + this.planSlug + ".html")
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param planSlug Parameter 1.
         * @param planPrice Parameter 2.
         * @return A new instance of fragment PlansDescriptionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(planSlug: String, planPrice: String, planName: String) =
            PlansDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PLAN_SLUG, planSlug)
                    putString(ARG_PLAN_PRICE, planPrice)
                    putString(ARG_PLAN_NAME, planName)
                }
            }
    }
}