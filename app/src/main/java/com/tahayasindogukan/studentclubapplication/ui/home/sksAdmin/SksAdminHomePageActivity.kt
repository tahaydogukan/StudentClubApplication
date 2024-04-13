package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.ActivitySksAdminHomePageBinding

class SksAdminHomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySksAdminHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySksAdminHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.sks_admin_bottom_nav_fragment_container_view) as NavHostFragment
        NavigationUI.setupWithNavController(
            binding.sksAdminBottomBar,
            navHostFragment.navController


        )
    }
}