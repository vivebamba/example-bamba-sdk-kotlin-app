package com.example.bambainsidekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bambaService = BambaService()
        val listView = findViewById<ListView>(R.id.product_list_view)
        val productList = bambaService.getProducts()
        val adapter = ProductAdapter(this, productList)
        listView.adapter = adapter
    }
}