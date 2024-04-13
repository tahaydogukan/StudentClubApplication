package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.rejecteds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerFormsApprovedDetailPageBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerFormsRejectedDetaillPageBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerFormsRejectedPageBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.approveds.ClubManagerFormsApprovedDetailPage
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.pendings.ClubManagerFormsPendingDetailPageArgs


class ClubManagerFormsRejectedDetailPage : Fragment() {
    private lateinit var binding : FragmentClubManagerFormsRejectedDetaillPageBinding
    private val args : ClubManagerFormsRejectedDetailPageArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerFormsRejectedDetaillPageBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            fragmentClubManagerFormsRejeectedDetailPageEtTitle.setText(args.request.title)
            fragmentClubManagerFormsRejeectedDetailPageTwContent.setText(args.request.content)
            fragmentClubManagerFormsRejeectedDetailPageEtEventGoals.setText(args.request.eventGoals)
            fragmentClubManagerFormsRejeectedDetailPageEtAgenda.setText(args.request.agenda)
            fragmentClubManagerFormsRejeectedDetailPageEtStartDate.setText(args.request.startDate)
            fragmentClubManagerFormsRejeectedDetailPageEtEndDate.setText(args.request.endDate)
        }

    }

}