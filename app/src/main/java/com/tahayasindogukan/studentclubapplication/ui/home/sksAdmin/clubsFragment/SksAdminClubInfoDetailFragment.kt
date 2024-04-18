package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.clubsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminClubInfoDetailBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.posts.pendings.ClubManagerPostsPendingDetailPageArgs


class SksAdminClubInfoDetailFragment : Fragment() {
    private lateinit var binding :FragmentSksAdminClubInfoDetailBinding
    private val args : SksAdminClubInfoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSksAdminClubInfoDetailBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            sksAdminClubInfoDetailTitle.text = args.request.title
            sksAdminClubInfoDetailManager.text = args.request.manager
            sksAdminClubInfoDetailLocation.text = args.request.location
            sksAdminClubInfoDetailWebPlatform.text = args.request.webPlatform
            sksAdminClubInfoDetailWebContacts.text = args.request.contacts
            sksAdminClubInfoDetailStartDate.text = args.request.startDate
            sksAdminClubInfoDetailEndDate.text = args.request.endDate
            sksAdminClubInfoDetailDecsription.text = args.request.content



        }


    }


}