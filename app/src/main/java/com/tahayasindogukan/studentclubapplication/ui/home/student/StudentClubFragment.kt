package com.tahayasindogukan.studentclubapplication.ui.home.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.databinding.FragmentStudentClubBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.clubsFragment.adapter.SksAdminClubSearchAdapter
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel
import java.util.Locale

class StudentClubFragment : Fragment(), SksAdminClubSearchAdapter.SksAdminClubSearchClickListener {
    private lateinit var binding: FragmentStudentClubBinding
    private lateinit var rv: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: SksAdminClubSearchAdapter
    private val firebaseViewModel: FirebaseViewModel by viewModels()
    private lateinit var navController: NavController
    private var clubListForFilter = ArrayList<Club>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentClubBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        rv = binding.sksAdminClubsFragmentRv
        searchView = binding.sksAdminClubsFragmentSearchBar

        rv.setHasFixedSize(true)
        rv.layoutManager = GridLayoutManager(requireContext(), 2)



        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText, clubListForFilter)
                return true
            }
        })



        firebaseViewModel.getClubs()

        firebaseViewModel.clubs.observe(viewLifecycleOwner) { clubList ->
            clubListForFilter = clubList as ArrayList<Club>
            adapter = SksAdminClubSearchAdapter(clubList, this, requireContext())
            rv.adapter = adapter

        }


    }


    private fun filterList(query: String?, clubList: List<Club>) {
        if (query != null) {
            val filteredList = ArrayList<Club>()
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

    override fun onClick(club: Club) {
        val action = StudentClubFragmentDirections
            .actionStudentClubFragmentToStudentClubInfoFragment(club)
        findNavController().navigate(action)
    }

}