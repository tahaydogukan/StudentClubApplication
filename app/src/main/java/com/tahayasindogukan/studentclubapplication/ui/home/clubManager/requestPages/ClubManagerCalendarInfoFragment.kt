package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerCalendarInfoBinding
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class ClubManagerCalendarInfoFragment : Fragment() {
    private lateinit var binding: FragmentClubManagerCalendarInfoBinding
    private val args: ClubManagerCalendarInfoFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClubManagerCalendarInfoBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("Europe/Istanbul")

        val formattedStartDateTime = sdf.format(args.request.startDate)
        val formattedEndDateTime = sdf.format(args.request.endDate)

        Glide.with(requireContext()).load(args.request.attachment).into(binding.clubManagerCalendarInfoDetailPhoto)

        if (args.request.location == "") {
            binding.clubManagerCalendarInfoDetailLocation.text = "Not Available"

        } else {
            binding.clubManagerCalendarInfoDetailLocation.text = args.request.location

        }

        if (args.request.webPlatform=="") {
            binding.clubManagerCalendarInfoDetailWebPlatform.text = "Not Available"

        } else {
            binding.clubManagerCalendarInfoDetailWebPlatform.text = args.request.webPlatform
        }
        binding.clubManagerCalendarInfoDetailStartDate.text = formattedStartDateTime
        binding.clubManagerCalendarInfoDetailDescription.text = args.request.content
        binding.clubManagerCalendarInfoDetailWebContacts.text = args.request.contacts

        binding.clubManagerCalendarInfoDetailTitle.text = args.request.title
        binding.sksAdminClubInfoDetailManager.text = args.request.manager
        binding.clubManagerCalendarInfoDetailEndDate.text = formattedEndDateTime
    }


}