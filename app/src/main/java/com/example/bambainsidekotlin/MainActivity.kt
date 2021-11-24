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
        bamba.apiKey = "735bda43-17d0-45cb-8aa0-4686182594dc"
        bamba.broadcastApiKey = "6217c228299d56eb1294"
        bamba.user(
            "Nicola Tesla",
            "Mandić",
            "5525454152",
            "e7705831-70e4-403a-85cf-f77ccb279ce5"
        )

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.plans -> setCurrentFragment(plansFragment)
                R.id.bamba -> setCurrentFragment(bambaFragment)
                R.id.profile -> setCurrentFragment(profileFragment)
            }

            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
    }
}