package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerClubActivtiesDetailBinding
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone


class ClubManagerClubActivtiesDetailFragment : Fragment() {
    private lateinit var binding: FragmentClubManagerClubActivtiesDetailBinding
    private val args: ClubManagerClubActivtiesDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClubManagerClubActivtiesDetailBinding.inflate(
            layoutInflater,
            container,
            false
        )
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("Europe/Istanbul")

        val formattedStartDateTime = sdf.format(args.request.startDate)
        val formattedEndDateTime = sdf.format(args.request.endDate)

        binding.apply {

            if(args.request.webPlatform.length < 2){
                binding.clubManagerClubActivitiesDetailWebPlatform.text = "Not Available"
            }else{
                clubManagerClubActivitiesDetailWebPlatform.text = args.request.webPlatform

            }
            if(args.request.location.length < 2){
                binding.clubManagerClubActivitiesDetailLocation.text = "Not Available"
            }else{
                clubManagerClubActivitiesDetailLocation.text = args.request.location

            }

            Glide.with(requireContext()).load(args.request.attachment)
                .into(binding.clubManagerClubActivitiesDetailPhoto)
            clubManagerClubActivitiesDetailTitle.text = args.request.title
            clubManagerClubActivitiesDetailManager.text = args.request.manager
            clubManagerClubActivitiesDetailContacts.text = args.request.contacts
            clubManagerClubActivitiesDetailStartDate.text = formattedStartDateTime
            clubManagerClubActivitiesDetailEndDate.text = formattedEndDateTime
            clubManagerClubActivitiesDetailDescription.text = args.request.content


        }


    }


}