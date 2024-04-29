package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.posts.pendings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerPostsPendingDetailPageBinding
import java.text.SimpleDateFormat
import java.util.Locale


class ClubManagerPostsPendingDetailPage : Fragment() {
    private lateinit var binding: FragmentClubManagerPostsPendingDetailPageBinding
    private val args : ClubManagerPostsPendingDetailPageArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerPostsPendingDetailPageBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val formattedStartDateTime = sdf.format(args.request.startDate)
        val formattedEndDateTime = sdf.format(args.request.endDate)

        binding.apply {
            clubManagerPostsPendingPageTwTitle.text = args.request.title
            clubManagerPostsPendingDetailPageTwContent.text = args.request.content
            clubManagerPostsPendingDetailPageTwEventGoals.text = args.request.eventGoals
            clubManagerPostsPendingDetailPageTwAgenda.text = args.request.agenda
            clubManagerPostsPendingDetailPageEtStartDate.text = formattedStartDateTime
            clubManagerPostsPendingDetailPageEtEndDate.text = formattedEndDateTime
            clubManagerPostsPendingDetailPageEtManager.text = args.request.manager
            clubManagerPostsPendingDetailPageEtLocation.text = args.request.location
            clubManagerPostsPendingDetailPageEtWebPlatform.text = args.request.webPlatform
            clubManagerPostsPendingDetailPageEtContacts.text = args.request.contacts
            Glide.with(requireContext()).load(args.request.attachment).into(binding.clubManagerPostsPendingDetailPageEtAttachment)
        }



    }

}