package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.rejected

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminFormsApprovedDetailBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminFormsRejectedDetailBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.approved.SksAdminFormsApprovedDetailFragmentArgs

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

        binding.apply {
            sksAdminFormsRejectedTitle.setText(args.request.title)
            sksAdminFormsRejectedContent.setText(args.request.content)
            sksAdminFormsRejectedEventGoals.setText(args.request.eventGoals)
            sksAdminFormsRejectedAgenda.setText(args.request.agenda)
            sksAdminFormsRejectedStartDate.setText(args.request.startDate)
            sksAdminFormsRejectedEndDate.setText(args.request.endDate)
        }

    }

}