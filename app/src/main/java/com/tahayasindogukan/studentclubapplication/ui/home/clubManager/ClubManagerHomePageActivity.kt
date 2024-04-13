package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.ActivityClubManagerHomePageBinding
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel

class ClubManagerHomePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClubManagerHomePageBinding
    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityClubManagerHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.club_manager_bottom_nav_fragment_container_view) as NavHostFragment
        NavigationUI.setupWithNavController(
            binding.clubManagerBottomBar,
            navHostFragment.navController
        )

        viewModel.checkManagerOfWhichClub()





    }
}