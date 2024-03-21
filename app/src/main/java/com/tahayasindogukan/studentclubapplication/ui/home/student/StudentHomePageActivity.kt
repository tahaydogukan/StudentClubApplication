package com.tahayasindogukan.studentclubapplication.ui.home.student

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.ActivityStudentHomePageBinding

class StudentHomePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.student_bottom_nav_fragment_container_view) as NavHostFragment
        NavigationUI.setupWithNavController(binding.studentBottomBar, navHostFragment.navController)

    }
}