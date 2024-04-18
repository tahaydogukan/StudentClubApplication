package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.posts.approveds

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerPostsApprovedDetailPageBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerPostsPendingDetailPageBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.posts.pendings.ClubManagerPostsPendingDetailPageArgs

class ClubManagerPostsApprovedDetailPage : Fragment() {
    private lateinit var binding: FragmentClubManagerPostsApprovedDetailPageBinding
    private val args : ClubManagerPostsPendingDetailPageArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerPostsApprovedDetailPageBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            clubManagerPostsApprovedPageTwTitle.text = args.request.title
            clubManagerPostsApprovedDetailPageTwContent.text = args.request.content
            clubManagerPostsApprovedDetailPageTwEventGoals.text = args.request.eventGoals
            clubManagerPostsApprovedDetailPageTwAgenda.text = args.request.agenda
            clubManagerPostsApprovedDetailPageEtStartDate.text = args.request.startDate
            clubManagerPostsApprovedDetailPageEtEndDate.text = args.request.endDate
            clubManagerPostsApprovedDetailPageEtManager.text = args.request.manager
            clubManagerPostsApprovedDetailPageEtLocation.text = args.request.location
            clubManagerPostsApprovedDetailPageEtWebPlatform.text = args.request.webPlatform
            clubManagerPostsApprovedDetailPageEtContacts.text = args.request.contacts
            Glide.with(requireContext()).load(args.request.attachment).into(binding.clubManagerPostsApprovedDetailPageEtAttachment)


        }



    }

}