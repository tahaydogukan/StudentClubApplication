package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.approveds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerFormsApprovedDetailPageBinding
import java.text.SimpleDateFormat
import java.util.Locale

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


        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val formattedStartDateTime = sdf.format(args.request.startDate)
        val formattedEndDateTime = sdf.format(args.request.endDate)

        binding.etTitle.setText(args.request.title)
        binding.etContent.setText(args.request.content)
        binding.etEventGoals.setText(args.request.eventGoals)
        binding.etAgenda.setText(args.request.agenda)
        binding.etStartDate.text = formattedStartDateTime
        binding.etEndDate.text = formattedEndDateTime


        binding.btnCreatePostRequest.setOnClickListener {

            val action = ClubManagerFormsApprovedDetailPageDirections
                .actionClubManagerFormsApprovedDetailPageToClubManagerCreatePostFragment(requestApproved)
            findNavController().navigate(action)
        }





    }

}