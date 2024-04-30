package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.pendings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerFormsPendingDetailPageBinding
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class ClubManagerFormsPendingDetailPage : Fragment() {
    private val args : ClubManagerFormsPendingDetailPageArgs by navArgs()
    private lateinit var binding: FragmentClubManagerFormsPendingDetailPageBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerFormsPendingDetailPageBinding.inflate(layoutInflater,container,false)
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
            etTitle.setText(args.request.title)
            etContent.setText(args.request.content)
            etEventGoals.setText(args.request.eventGoals)
            etAgenda.setText(args.request.agenda)
            etStartDate.text = formattedStartDateTime
            etEndDate.text = formattedEndDateTime
        }

    }

}