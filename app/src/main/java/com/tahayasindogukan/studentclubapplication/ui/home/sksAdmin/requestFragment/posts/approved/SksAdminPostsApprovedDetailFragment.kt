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
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

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

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("Europe/Istanbul")

        val formattedStartDateTime = sdf.format(args.request.startDate)
        val formattedEndDateTime = sdf.format(args.request.endDate)


        binding.apply {

            sksAdminPostsApprovedTitle.setText(args.request.title)
            sksAdminPostsApprovedManager.text = args.request.manager
            sksAdminPostsApprovedContent.setText(args.request.content)
            sksAdminPostsApprovedStartDate.text = formattedStartDateTime
            sksAdminPostsApprovedEndDate.text = formattedEndDateTime
            sksAdminPostsApprovedLocation.text = args.request.location
            sksAdminPostsApprovedWebPlatform.text = args.request.webPlatform
            sksAdminPostsApprovedContacts.text = args.request.contacts
        }


    }

}
