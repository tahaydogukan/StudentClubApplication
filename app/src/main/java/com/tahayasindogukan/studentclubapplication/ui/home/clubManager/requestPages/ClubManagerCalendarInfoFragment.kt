package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerCalendarInfoBinding

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




        if (args.request.attachment != null) {
            Glide.with(this).load(args.request.attachment).centerCrop()
                .into(binding.clubManagerCalendarInfoDetailPhoto)
        }
        binding.clubManagerCalendarInfoDetailStartDate.text = args.request.startDate
        binding.clubManagerCalendarInfoDetailDescription.text = args.request.content
        binding.clubManagerCalendarInfoDetailWebContacts.text = args.request.contacts
        binding.clubManagerCalendarInfoDetailWebPlatform.text = args.request.webPlatform
        binding.clubManagerCalendarInfoDetailTitle.text = args.request.title
        binding.sksAdminClubInfoDetailManager.text = args.request.manager
        binding.clubManagerCalendarInfoDetailEndDate.text = args.request.endDate
    }


}