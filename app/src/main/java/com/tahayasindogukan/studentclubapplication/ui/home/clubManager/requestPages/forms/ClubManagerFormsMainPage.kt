package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerCreateFormPageBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerFormsMainPageBinding


class ClubManagerFormsMainPage : Fragment() {
    private lateinit var binding: FragmentClubManagerFormsMainPageBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerFormsMainPageBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.btnCreateFormRequest.setOnClickListener {
            navController.navigate(R.id.action_clubManagerFormsMainPage_to_clubManagerCreateFormPage)

        }
        binding.btnPending.setOnClickListener {
            navController.navigate(R.id.action_clubManagerFormsMainPage_to_clubManagerFormsPendingPage)

        }
        binding.btnRejected.setOnClickListener {
            navController.navigate(R.id.action_clubManagerFormsMainPage_to_clubManagerFormsRejectedPage)

        }
        binding.btnApproved.setOnClickListener {
            navController.navigate(R.id.action_clubManagerFormsMainPage_to_clubManagerFormsApprovedPage)

        }
    }

}