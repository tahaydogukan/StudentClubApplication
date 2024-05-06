package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.profilePages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerProfileNotificationBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.ClubManagerClubActivtiesDetailFragmentArgs
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.profileFragment.SksAdminNotificationRequestAdapter
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.profileFragment.SksAdminProfileNotificationsFragmentDirections


class ClubManagerProfileNotificationFragment : Fragment(),SksAdminNotificationRequestAdapter.SksAdminEditsClubAdapterClickListener {
    private lateinit var binding : FragmentClubManagerProfileNotificationBinding
    private lateinit var rvRequest: RecyclerView
    private val requestViewModel: RequestViewModel by viewModels()
    private lateinit var requestAdapter: SksAdminNotificationRequestAdapter
    private val args: ClubManagerProfileNotificationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerProfileNotificationBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rvRequest = binding.sksAdminRequestNotificationRecyclerView

        requestViewModel.postsApprovedList.observe(viewLifecycleOwner) {request ->
            requestAdapter = SksAdminNotificationRequestAdapter(request, requireContext(), this)
            rvRequest .adapter = requestAdapter
        }
        rvRequest.layoutManager = LinearLayoutManager(requireContext())
        requestViewModel.getRequestNotificationsForClubManager(args.club.clubName)

    }
    override fun onClick(request: Request) {

        if (request.status == "2" && request.isPost == true && request.isForm == false){
            val action = ClubManagerProfileNotificationFragmentDirections
                .actionClubManagerProfileNotificationFragmentToClubManagerFormsApprovedDetailPage(request)
            findNavController().navigate(action)
        }
        else if (request.status == "2" && request.isForm == true && request.isPost == false){
            val action = ClubManagerProfileNotificationFragmentDirections
                .actionClubManagerProfileNotificationFragmentToClubManagerPostsApprovedDetailPage(request)
            findNavController().navigate(action)
        }
        else if (request.status == "3" && request.isForm == true && request.isPost == false){
            val action = ClubManagerProfileNotificationFragmentDirections
                .actionClubManagerProfileNotificationFragmentToClubManagerFormsRejectedDetailPage(request)
            findNavController().navigate(action)
        }
        else if (request.status == "3" && request.isPost == true && request.isForm == false){
            val action = ClubManagerProfileNotificationFragmentDirections
                .actionClubManagerProfileNotificationFragmentToClubManagerPostsRejectedDetailPage(request)
            findNavController().navigate(action)
        } else{
            //
        }   }
}