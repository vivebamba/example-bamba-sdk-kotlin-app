package com.example.bambainsidekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val homeFragment = HomeFragment()
        val plansFragment = PlansFragment()
        val bambaFragment = BambaFragment()
        val profileFragment = ProfileFragment()

        setCurrentFragment(homeFragment)


        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->setCurrentFragment(homeFragment)
                R.id.plans->setCurrentFragment(plansFragment)
                R.id.bamba->setCurrentFragment(bambaFragment)
                R.id.profile->setCurrentFragment(profileFragment)
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