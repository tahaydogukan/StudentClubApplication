package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.pendings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerFormsApprovedDetailPageBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerFormsPendingDetailPageBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerFormsPendingPageBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.approveds.ClubManagerFormsApprovedDetailPageArgs

class ClubManagerFormsPendingDetailPage : Fragment() {
    private val args : ClubManagerFormsPendingDetailPageArgs by navArgs()
    private lateinit var binding: FragmentClubManagerFormsPendingDetailPageBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerFormsPendingDetailPageBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            etTitle.setText(args.request.title)
            etContent.setText(args.request.content)
            etEventGoals.setText(args.request.eventGoals)
            etAgenda.setText(args.request.agenda)
            etStartDate.setText(args.request.startDate)
            etEndDate.setText(args.request.endDate)
        }

    }

}