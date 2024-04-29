package com.tahayasindogukan.studentclubapplication.ui.home.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.tahayasindogukan.studentclubapplication.databinding.FragmentStudentClubInfoDetailBinding
import java.text.SimpleDateFormat
import java.util.Locale

class StudentClubInfoDetailFragment : Fragment() {
    private lateinit var binding: FragmentStudentClubInfoDetailBinding
    private val args: StudentClubInfoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentClubInfoDetailBinding.inflate(layoutInflater, container, false)
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

            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val formattedStartDateTime = sdf.format(args.request.startDate)
            val formattedEndDateTime = sdf.format(args.request.endDate)


            sksAdminClubInfoDetailWebPlatform.text = args.request.webPlatform
            sksAdminClubInfoDetailWebContacts.text = args.request.contacts
            sksAdminClubInfoDetailStartDate.text = formattedStartDateTime
            sksAdminClubInfoDetailEndDate.text = formattedEndDateTime
            sksAdminClubInfoDetailDecsription.text = args.request.content


        }


    }


}