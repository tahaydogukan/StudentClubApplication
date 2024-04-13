package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.approveds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerFormsApprovedDetailPageBinding

class ClubManagerFormsApprovedDetailPage : Fragment() {
    private lateinit var binding: FragmentClubManagerFormsApprovedDetailPageBinding
    private val args : ClubManagerFormsApprovedDetailPageArgs by navArgs()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentClubManagerFormsApprovedDetailPageBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        var requestApproved = args.request

        binding.etTitle.setText(args.request.title)
        binding.etContent.setText(args.request.content)
        binding.etEventGoals.setText(args.request.eventGoals)
        binding.etAgenda.setText(args.request.agenda)
        binding.etStartDate.setText(args.request.startDate)
        binding.etEndDate.setText(args.request.endDate)


        binding.btnCreatePostRequest.setOnClickListener {

            val action = ClubManagerFormsApprovedDetailPageDirections
                .actionClubManagerFormsApprovedDetailPageToClubManagerCreatePostFragment(requestApproved)
            findNavController().navigate(action)
        }





    }

}