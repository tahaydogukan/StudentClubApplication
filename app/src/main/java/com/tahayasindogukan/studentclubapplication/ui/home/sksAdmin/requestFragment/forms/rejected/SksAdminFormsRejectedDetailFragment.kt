package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.rejected

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminFormsRejectedDetailBinding
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class SksAdminFormsRejectedDetailFragment : Fragment() {

    private lateinit var binding : FragmentSksAdminFormsRejectedDetailBinding
    private val args : SksAdminFormsRejectedDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSksAdminFormsRejectedDetailBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Glide.with(requireContext()).load(args.request.attachment).into(binding.sksAdminFormsRejectedPhoto)

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("Europe/Istanbul")

        val formattedStartDateTime = sdf.format(args.request.startDate)
        val formattedEndDateTime = sdf.format(args.request.endDate)

        binding.apply {
            sksAdminFormsRejectedTitle.setText(args.request.title)
            sksAdminFormsRejectedContent.setText(args.request.content)
            sksAdminFormsRejectedEventGoals.setText(args.request.eventGoals)
            sksAdminFormsRejectedAgenda.setText(args.request.agenda)
            sksAdminFormsRejectedStartDate.text = formattedStartDateTime
            sksAdminFormsRejectedEndDate.text = formattedEndDateTime
        }

    }

}