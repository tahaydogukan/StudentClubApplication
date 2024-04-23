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
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerProfileMyActivitiesBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.ClubManagerCalendarAdapter
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.approved.SksAdminFormsApprovedDetailFragmentArgs
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.approved.SksAdminFormsApprovedFragmentDirections
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel


class ClubManagerProfileMyActivities : Fragment(), ClubManagerCalendarAdapter.MyClickListener {
    private lateinit var binding : FragmentClubManagerProfileMyActivitiesBinding
    private val requestViewModel: RequestViewModel by viewModels()
    private val args : ClubManagerProfileMyActivitiesArgs by navArgs()
    private lateinit var rv: RecyclerView

    // burdan gelen clubName le o cluba ait activityleri çekicez sonraki sayfada da editleme olcak
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerProfileMyActivitiesBinding.inflate(layoutInflater,container,false  )
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = binding.clubManagerProfileMyActivitiesRecyclerView

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(requireContext())

        requestViewModel.myClubActivitiesList.observe(viewLifecycleOwner) { activities ->
            val recyclerView = binding.clubManagerProfileMyActivitiesRecyclerView

            val adapter = ClubManagerCalendarAdapter(activities, this)
            recyclerView.adapter = adapter

        }
        requestViewModel.getMyClubActivities()



    }

    override fun onClick(request: Request) {
   }

}