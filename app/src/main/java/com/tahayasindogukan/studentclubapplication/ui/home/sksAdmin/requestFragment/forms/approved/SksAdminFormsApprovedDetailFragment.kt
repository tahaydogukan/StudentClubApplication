package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.approved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminFormsApprovedDetailBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.approveds.ClubManagerFormsApprovedDetailPageArgs

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

        Glide.with(requireContext()).load(args.request.attachment).into(binding.sksAdminFormsApprovedPhoto)
        binding.sksAdminFormsApprovedTitle.setText(args.request.title)
        binding.sksAdminFormsApprovedContent.setText(args.request.content)
        binding.sksAdminFormsApprovedEventGoals.setText(args.request.eventGoals)
        binding.sksAdminFormsApprovedAgenda.setText(args.request.agenda)
        binding.sksAdminFormsApprovedStartDate.setText(args.request.startDate)
        binding.sksAdminFormsApprovedEndDate.setText(args.request.endDate)


    }
}