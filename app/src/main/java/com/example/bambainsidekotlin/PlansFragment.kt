package com.example.bambainsidekotlin

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import com.example.bambainsidekotlin.adapters.PlansAdapter
import com.example.bambainsidekotlin.services.BambaService
import java.lang.Exception


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PlansFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        return inflater.inflate(R.layout.fragment_plans, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val bambaService = BambaService()

        try {
            if (!this.hasInternetConnection()) {
                Toast.makeText(view.context, getString(R.string.cant_show_products_due_to_connection), Toast.LENGTH_LONG).show()
            } else {
                val productList = bambaService.getProducts()
                val concatAdapter = ConcatAdapter(HeaderAdapter(), PlansAdapter(productList))
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = concatAdapter
                }
            }
        } catch (e: Exception) {
            Toast.makeText(view.context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = activity?.getSystemService(ConnectivityManager::class.java)
        val currentNetwork = connectivityManager?.activeNetwork
        val caps = connectivityManager?.getNetworkCapabilities(currentNetwork)

        if (caps === null) {
            return false
        }

        return caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlansFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}