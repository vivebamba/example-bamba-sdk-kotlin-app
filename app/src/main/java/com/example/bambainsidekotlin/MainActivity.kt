package com.example.bambainsidekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vivebamba.bambalibrary.Bamba


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val homeFragment = HomeFragment()
        val plansFragment = PlansFragment()
        val bambaFragment = BambaFragment()
        val profileFragment = ProfileFragment()

        setCurrentFragment(homeFragment)

        setSupportActionBar(findViewById(R.id.toolbar))
        // showing the back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bamba = Bamba
        bamba.apiKey = "1835fc79-64c5-4495-b1d3-e87227844236"
        bamba.broadcastApiKey = "6217c228299d56eb1294"
        bamba.user(
            "Adrian",
            "Galicia Dev Test",
            "5554762014",
            "68884b0e-b424-48eb-8775-fe197c6b9e7c"
        )

//        binding.fab.setOnClickListener {
//            bamba.openchat(this)
//        }



        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.plans -> setCurrentFragment(plansFragment)
                R.id.bamba -> setCurrentFragment(bambaFragment)
                R.id.profile -> setCurrentFragment(profileFragment)
            }
            true
        }
//        val bambaService = BambaService()
//        val listView = findViewById<ListView>(R.id.product_list_view)
//        val productList = bambaService.getProducts()
//        val adapter = ProductAdapter(this, productList)
//        listView.adapter = adapter
    }

    private fun setCurrentFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
    }
}