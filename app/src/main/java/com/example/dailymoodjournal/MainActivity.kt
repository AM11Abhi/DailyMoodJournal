package com.example.dailymoodjournal

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.dailymoodjournal.data.MoodEntry
import com.example.dailymoodjournal.MoodViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // NavController setup
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setupWithNavController(navController)

        val moodViewModel = MoodViewModel(application)

        // Example: Fetch moods
        lifecycleScope.launch {
            val moods = moodViewModel.getAllMoods()
            moods.forEach {
                Log.d("MainActivity", "Mood: ${it.mood}, Note: ${it.note}, Date: ${it.date}")
            }
        }
    }
}
