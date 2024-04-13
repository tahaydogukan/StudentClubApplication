package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.clubsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminClubsBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.clubsFragment.adapter.SksAdminClubSearchAdapter
import java.util.Locale

class SksAdminClubsFragment : Fragment() {
    private lateinit var binding: FragmentSksAdminClubsBinding
    private lateinit var rv: RecyclerView
    private lateinit var searchView: SearchView
    var clubList = mutableListOf<Club>()
    private lateinit var adapter: SksAdminClubSearchAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSksAdminClubsBinding.inflate(inflater,container,false)
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
        addDataToList()

        adapter = SksAdminClubSearchAdapter(clubList)
        rv.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })


        binding.sksAdminClubsFragmentBtnCreateClub.setOnClickListener {
            navController.navigate(R.id.action_sksAdminClubsFragment_to_sksAdminClubsCreateFragment)
        }

    }

    private fun addDataToList() {
        clubList.add(Club("a", "b", "mat"))
        clubList.add(Club("c", "b", "fen"))
        clubList.add(Club("b", "b", "sos"))


    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<Club>()
            for (i in clubList) {
                if (i.clubName.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

}