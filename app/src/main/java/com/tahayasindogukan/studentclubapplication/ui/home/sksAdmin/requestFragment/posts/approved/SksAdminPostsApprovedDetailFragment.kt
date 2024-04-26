package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.posts.approved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminPostsApprovedDetailBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.posts.pending.SksAdminPostsPendingDetailFragmentArgs

class SksAdminPostsApprovedDetailFragment : Fragment() {
    private lateinit var binding: FragmentSksAdminPostsApprovedDetailBinding

    private val args: SksAdminPostsPendingDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSksAdminPostsApprovedDetailBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext()).load(args.request.attachment)
            .into(binding.sksAdminPostsApprovedPhoto)

        binding.apply {

            sksAdminPostsApprovedTitle.setText(args.request.title)
            sksAdminPostsApprovedManager.text = args.request.manager
            sksAdminPostsApprovedContent.setText(args.request.content)
            sksAdminPostsApprovedStartDate.setText(args.request.startDate)
            sksAdminPostsApprovedEndDate.setText(args.request.endDate)
            sksAdminPostsApprovedLocation.text = args.request.location
            sksAdminPostsApprovedWebPlatform.text = args.request.webPlatform
            sksAdminPostsApprovedContacts.text = args.request.contacts
        }


    }

}
