package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.profileFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminProfileNotificationsBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.CalendarFragment.SksAdminCalendarAdapter
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.CalendarFragment.SksAdminCalendarFragmentDirections


class SksAdminProfileNotificationsFragment : Fragment()
    ,SksAdminNotificationRequestAdapter.SksAdminEditsClubAdapterClickListener
    ,SksAdminProfileNotificationClubAdapter.SksAdminEditsClubAdapterClickListener{
    private lateinit var binding: FragmentSksAdminProfileNotificationsBinding
    private lateinit var navController: NavController
    private lateinit var rvRequest: RecyclerView
    private lateinit var rvClub: RecyclerView
    private lateinit var requestAdapter: SksAdminNotificationRequestAdapter
    private lateinit var clubAdapter: SksAdminProfileNotificationClubAdapter

    private val requestViewModel: RequestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentSksAdminProfileNotificationsBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        rvRequest = binding.sksAdminRequestNotificationRecyclerView

        requestViewModel.postsApprovedList.observe(viewLifecycleOwner) {request ->
            requestAdapter = SksAdminNotificationRequestAdapter(request, requireContext(), this)
            rvRequest .adapter = requestAdapter
        }
        rvRequest.layoutManager = LinearLayoutManager(requireContext())
        requestViewModel.getRequestNotifications()



        rvClub = binding.sksAdminEditsClubNotificationRecyclerView

        requestViewModel.clubEditList.observe(viewLifecycleOwner) {club ->
            clubAdapter = SksAdminProfileNotificationClubAdapter(club, requireContext(), this)
            rvClub .adapter = clubAdapter
        }
        rvClub.layoutManager = LinearLayoutManager(requireContext())
        requestViewModel.getClubNotifications()
    }

    override fun onClick(request: Request) {

        if (request.status == "4"){
            val action = SksAdminProfileNotificationsFragmentDirections
                .actionSksAdminProfileNotificationsFragmentToSksAdminEditsPostsPendingFragment(request)
            findNavController().navigate(action)
        }else{
            val action = SksAdminProfileNotificationsFragmentDirections
                .actionSksAdminProfileNotificationsFragmentToSksAdminRequestFragment()
            findNavController().navigate(action)
        }
    }

    override fun onClick(club: Club) {
        val action = SksAdminProfileNotificationsFragmentDirections
            .actionSksAdminProfileNotificationsFragmentToSksAdminEditsClubPendingFragment(club)
        findNavController().navigate(action)    }

}