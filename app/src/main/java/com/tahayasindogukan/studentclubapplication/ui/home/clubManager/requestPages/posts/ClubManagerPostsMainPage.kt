package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerPostsMainPageBinding


class ClubManagerPostsMainPage : Fragment() {
    private lateinit var binding:FragmentClubManagerPostsMainPageBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerPostsMainPageBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.btnPending.setOnClickListener {
            navController.navigate(R.id.action_clubManagerPostsMainPage_to_clubManagerPostsPendingPage)

        }
        binding.btnRejected.setOnClickListener {
            navController.navigate(R.id.action_clubManagerPostsMainPage_to_clubManagerPostsRejectedPage)

        }
        binding.btnApproved.setOnClickListener {
            navController.navigate(R.id.action_clubManagerPostsMainPage_to_clubManagerPostsApprovedPage)

        }

    }

}