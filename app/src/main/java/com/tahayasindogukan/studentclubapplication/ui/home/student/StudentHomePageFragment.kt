package com.tahayasindogukan.studentclubapplication.ui.home.student

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
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
import com.tahayasindogukan.studentclubapplication.databinding.FragmentStudentHomePageBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.ClubManagerCalendarAdapter
import java.util.Locale

class StudentHomePageFragment : Fragment(), ClubManagerCalendarAdapter.MyClickListener {
    private lateinit var binding: FragmentStudentHomePageBinding
    private lateinit var navController: NavController
    private val requestViewModel: RequestViewModel by viewModels()
    private lateinit var adapter: ClubManagerCalendarAdapter
    private lateinit var rv: RecyclerView
    private lateinit var searchView: SearchView
    private var requestList = ArrayList<Request>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentHomePageBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        rv = binding.studentCalendarFragmentRecyclerView

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(requireContext())

        searchView = binding.searchBar

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText, requestList)
                return true
            }

        })

        requestViewModel.getWeeklyActivities()

        requestViewModel.postsApprovedList.observe(viewLifecycleOwner) { request ->
            requestList = request as ArrayList<Request>

            adapter = ClubManagerCalendarAdapter(request, this, requireContext())
            rv.adapter = adapter

        }


    }

    private fun filterList(query: String?, clubList: List<Request>) {
        if (query != null) {
            val filteredList = ArrayList<Request>()
            for (i in clubList) {
                if (i.clubName.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                //
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }


    override fun onClick(request: Request) {
        // Sks admin calendar info activitysi yok onu yap
        val action = StudentHomePageFragmentDirections
            .actionStudentHomePageFragmentToStudentClubInfoDetailFragment(request)
        findNavController().navigate(action)
    }

}