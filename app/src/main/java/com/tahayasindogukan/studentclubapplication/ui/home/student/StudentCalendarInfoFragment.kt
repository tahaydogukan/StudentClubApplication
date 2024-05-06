package com.tahayasindogukan.studentclubapplication.ui.home.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.databinding.FragmentStudentCalendarInfoBinding
import java.text.SimpleDateFormat
import java.util.Locale

class StudentCalendarInfoFragment : Fragment() {
    private lateinit var binding: FragmentStudentCalendarInfoBinding
    private val args: StudentCalendarInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentCalendarInfoBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            sksAdminClubInfoDetailTitle.text = args.request.title
            sksAdminClubInfoDetailManager.text = args.request.manager


            if (args.request.location == "") {
                binding.sksAdminClubInfoDetailLocation.text = "Not Available"

            } else {
                sksAdminClubInfoDetailLocation.text = args.request.location

            }

            if (args.request.webPlatform=="") {
                binding.sksAdminClubInfoDetailWebPlatform.text = "Not Available"

            } else {
                sksAdminClubInfoDetailWebPlatform.text = args.request.webPlatform
            }


            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val formattedStartDateTime = sdf.format(args.request.startDate)
            val formattedEndDateTime = sdf.format(args.request.endDate)

            Glide.with(requireContext()).load(args.request.attachment).into(binding.sksAdminClubInfoDetailPhoto)

            sksAdminClubInfoDetailWebContacts.text = args.request.contacts
            sksAdminClubInfoDetailStartDate.text = formattedStartDateTime
            sksAdminClubInfoDetailEndDate.text = formattedEndDateTime
            sksAdminClubInfoDetailDecsription.text = args.request.content


        }


    }


}