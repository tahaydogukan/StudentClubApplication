package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerProfileMyActivitiesDetailBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.clubsFragment.SksAdminClubInfoDetailFragmentArgs


class ClubManagerClubActivtiesDetailFragment : Fragment() {
    private lateinit var binding: FragmentClubManagerProfileMyActivitiesDetailBinding
    private val args: SksAdminClubInfoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClubManagerProfileMyActivitiesDetailBinding.inflate(
            layoutInflater,
            container,
            false
        )
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            clubAdminProfileMyActivitiesTitle.text = args.request.title
            clubAdminProfileMyActivitiesManager.text = args.request.manager

            if (args.request.location.length <= 2) {
                binding.clubAdminProfileMyActivitiesLocation.visibility = View.INVISIBLE
            } else {
                clubAdminProfileMyActivitiesLocation.text = args.request.location
            }

            if (args.request.webPlatform.length <= 2) {
                binding.clubAdminProfileMyActivitiesWebPlatform.visibility = View.INVISIBLE
            } else {
                clubAdminProfileMyActivitiesWebPlatform.text = args.request.webPlatform
            }

            clubAdminProfileMyActivitiesContacts.text = args.request.contacts
            clubAdminProfileMyActivitiesStartDate.text = args.request.startDate
            clubAdminProfileMyActivitiesEndDate.text = args.request.endDate
            clubAdminProfileMyActivitiesDescription.text = args.request.content


        }


    }


}