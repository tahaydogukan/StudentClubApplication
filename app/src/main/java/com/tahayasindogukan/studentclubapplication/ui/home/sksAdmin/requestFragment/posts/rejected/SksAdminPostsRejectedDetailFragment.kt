package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.posts.rejected

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminPostsApprovedDetailBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminPostsRejectedDetailBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.posts.pending.SksAdminPostsPendingDetailFragmentArgs


class SksAdminPostsRejectedDetailFragment : Fragment() {
    private lateinit var binding: FragmentSksAdminPostsRejectedDetailBinding

    private val args: SksAdminPostsRejectedDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSksAdminPostsRejectedDetailBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext()).load(args.request.attachment)
            .into(binding.sksAdminPostsRejectedPhoto)

        binding.apply {

            sksAdminPostsRejectedTitle.setText(args.request.title)
            sksAdminPostsRejectedContent.setText(args.request.content)
            sksAdminPostsRejectedEventGoals.setText(args.request.eventGoals)
            sksAdminPostsRejectedAgenda.setText(args.request.agenda)
            sksAdminPostsRejectedStartDate.setText(args.request.startDate)
            sksAdminPostsRejectedEndDate.setText(args.request.endDate)
        }


    }

}