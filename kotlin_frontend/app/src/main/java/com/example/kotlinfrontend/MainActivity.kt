package com.example.kotlinfrontend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

// PUBLIC_INTERFACE
/**
 * MainActivity - Entry point for the health and fitness app.
 * Handles tabbed navigation for Dashboard, Workouts, Nutrition, and Profile.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set initial fragment to Dashboard
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, DashboardFragment())
                .commit()
        }

        val navView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_dashboard -> {
                    openFragment(DashboardFragment())
                    true
                }
                R.id.navigation_workouts -> {
                    openFragment(WorkoutsFragment())
                    true
                }
                R.id.navigation_nutrition -> {
                    openFragment(NutritionFragment())
                    true
                }
                R.id.navigation_profile -> {
                    openFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    // PUBLIC_INTERFACE
    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment)
            .commit()
    }
}
