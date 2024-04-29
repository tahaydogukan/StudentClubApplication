package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.approved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminFormsApprovedDetailBinding
import java.text.SimpleDateFormat
import java.util.Locale

class SksAdminFormsApprovedDetailFragment : Fragment() {
    private lateinit var binding : FragmentSksAdminFormsApprovedDetailBinding
    private val args : SksAdminFormsApprovedDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSksAdminFormsApprovedDetailBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val formattedStartDateTime = sdf.format(args.request.startDate)
        val formattedEndDateTime = sdf.format(args.request.endDate)


        Glide.with(requireContext()).load(args.request.attachment).into(binding.sksAdminFormsApprovedPhoto)
        binding.sksAdminFormsApprovedTitle.setText(args.request.title)
        binding.sksAdminFormsApprovedContent.setText(args.request.content)
        binding.sksAdminFormsApprovedEventGoals.setText(args.request.eventGoals)
        binding.sksAdminFormsApprovedAgenda.setText(args.request.agenda)
        binding.sksAdminFormsApprovedStartDate.text = formattedStartDateTime
        binding.sksAdminFormsApprovedEndDate.text = formattedEndDateTime


    }
}