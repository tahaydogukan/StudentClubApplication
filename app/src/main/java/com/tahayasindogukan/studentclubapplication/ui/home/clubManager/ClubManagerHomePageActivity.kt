package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.ActivityClubManagerHomePageBinding

class ClubManagerHomePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClubManagerHomePageBinding

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

        /*  val navController =
             findNavController(R.id.nav_host_fragment_activity_club_manager_home_page)
         // Passing each menu ID as a set of Ids because each
         // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
             setOf(
                 R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
             )
         )*/

    }
}