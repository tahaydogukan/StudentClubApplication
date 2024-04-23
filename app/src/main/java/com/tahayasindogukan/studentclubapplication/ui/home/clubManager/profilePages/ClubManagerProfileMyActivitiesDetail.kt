package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.profilePages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerProfileMyActivitiesDetailBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.approved.SksAdminFormsApprovedDetailFragmentArgs

class ClubManagerProfileMyActivitiesDetail : Fragment() {
    private lateinit var binding: FragmentClubManagerProfileMyActivitiesDetailBinding
    private val args: SksAdminFormsApprovedDetailFragmentArgs by navArgs()

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

        var request = args.request

        Glide.with(requireContext()).load(args.request.attachment)
            .into(binding.clubManagerProfileMyActivitiesPhoto)
        binding.clubAdminProfileMyActivitiesTitle.text = args.request.title
        binding.clubAdminProfileMyActivitiesDescription.text = args.request.content
        binding.clubAdminProfileMyActivitiesManager.text = args.request.manager
        binding.clubAdminProfileMyActivitiesLocation.text = args.request.location
        binding.clubAdminProfileMyActivitiesWebPlatform.text = args.request.webPlatform
        binding.clubAdminProfileMyActivitiesStartDate.text = args.request.startDate
        binding.clubAdminProfileMyActivitiesEndDate.text = args.request.endDate
        binding.clubAdminProfileMyActivitiesContacts.text = args.request.contacts



    }


}