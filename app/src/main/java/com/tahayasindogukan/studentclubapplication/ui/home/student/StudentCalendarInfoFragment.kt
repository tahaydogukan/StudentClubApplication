package com.tahayasindogukan.studentclubapplication.ui.home.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.tahayasindogukan.studentclubapplication.databinding.FragmentStudentCalendarInfoBinding

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

            if (args.request.location.length <= 2) {
                binding.sksAdminClubInfoDetailLocation.visibility = View.INVISIBLE
            } else {
                sksAdminClubInfoDetailLocation.text = args.request.location
            }

            if (args.request.webPlatform.length <= 2) {
                binding.sksAdminClubInfoDetailWebPlatform.visibility = View.INVISIBLE
            } else {
                sksAdminClubInfoDetailWebPlatform.text = args.request.webPlatform
            }

            sksAdminClubInfoDetailWebPlatform.text = args.request.webPlatform
            sksAdminClubInfoDetailWebContacts.text = args.request.contacts
            sksAdminClubInfoDetailStartDate.text = args.request.startDate
            sksAdminClubInfoDetailEndDate.text = args.request.endDate
            sksAdminClubInfoDetailDecsription.text = args.request.content


        }


    }


}