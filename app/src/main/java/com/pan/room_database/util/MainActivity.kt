package com.pan.room_database.util

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.pan.room_database.R
import com.pan.room_database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //change navhost title
        setupActionBarWithNavController(findNavController(R.id.fragment))
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onNavigateUp()

    }

}