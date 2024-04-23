package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.posts.approved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminPostsApprovedDetailBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminPostsPendingDetailBinding
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
            sksAdminPostsApprovedContent.setText(args.request.content)
            sksAdminPostsApprovedEventGoals.setText(args.request.eventGoals)
            sksAdminPostsApprovedAgenda.setText(args.request.agenda)
            sksAdminPostsApprovedStartDate.setText(args.request.startDate)
            sksAdminPostsApprovedEndDate.setText(args.request.endDate)
        }


    }

}
