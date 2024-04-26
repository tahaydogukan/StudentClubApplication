package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.posts.rejected

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminPostsRejectedDetailBinding


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
            sksAdminPostsRejectedStartDate.setText(args.request.startDate)
            sksAdminPostsRejectedEndDate.setText(args.request.endDate)
            sksAdminPostsRejectedLocation.text = args.request.location
            sksAdminPostsRejectedContacts.text = args.request.contacts
            sksAdminPostsRejectedWebPlatform.text = args.request.webPlatform
        }


    }

}