package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.posts.rejecteds

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerPostsApprovedDetailPageBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerPostsRejectedDetailPageBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.posts.pendings.ClubManagerPostsPendingDetailPageArgs

class ClubManagerPostsRejectedDetailPage : Fragment() {
    private lateinit var binding: FragmentClubManagerPostsRejectedDetailPageBinding
    private val args : ClubManagerPostsPendingDetailPageArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerPostsRejectedDetailPageBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            clubManagerPostsRejectedPageTwTitle.text = args.request.title
            clubManagerPostsRejectedDetailPageTwContent.text = args.request.content
            clubManagerPostsRejectedDetailPageTwEventGoals.text = args.request.eventGoals
            clubManagerPostsRejectedDetailPageTwAgenda.text = args.request.agenda
            clubManagerPostsRejectedDetailPageEtStartDate.text = args.request.startDate
            clubManagerPostsRejectedDetailPageEtEndDate.text = args.request.endDate
            clubManagerPostsRejectedDetailPageEtManager.text = args.request.manager
            clubManagerPostsRejectedDetailPageEtLocation.text = args.request.location
            clubManagerPostsRejectedDetailPageEtWebPlatform.text = args.request.webPlatform
            clubManagerPostsRejectedDetailPageEtContacts.text = args.request.contacts
            Glide.with(requireContext()).load(args.request.attachment).into(binding.clubManagerPostsRejectedDetailPageEtAttachment)


        }



    }

}