package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.CalendarFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tahayasindogukan.studentclubapplication.core.entitiy.Activity
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminCalendarBinding
import java.util.Date
import java.util.Locale


class SksAdminCalendarFragment : Fragment() {

    private lateinit var binding: FragmentSksAdminCalendarBinding
    private lateinit var adapter: SksAdminCalendarAdapter
    private lateinit var rv: RecyclerView
    private lateinit var navController: NavController
    var activityList = mutableListOf<Activity>()
    private var mStartDate: Date? = null
    private lateinit var searchView: SearchView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSksAdminCalendarBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.calendarView.visibility = View.INVISIBLE
        addDataToList()
        rv = binding.rv
        //searchView = binding.sksAdminClubsFragmentSearchBar

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(requireContext())
        searchView = binding.searchBar

        adapter = SksAdminCalendarAdapter(activityList)
        rv.adapter = adapter

        val calendarView = binding.calendarView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        binding.calendarButton.setOnClickListener {
            binding.calendarView.visibility = View.VISIBLE
            binding.rv.visibility = View.INVISIBLE

            calendarView.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
                // Seçilen tarihi işleyin
                var secilenTarih = year

                // Filtreleme işlemini gerçekleştirin
                binding.calendarView.visibility = View.INVISIBLE
                binding.rv.visibility = View.VISIBLE
                binding.calendarButton.text = "${dayOfMonth}/${month}/${year}"
                searchView.setQuery(secilenTarih.toString(), false)
            }
        }


    }

    private fun addDataToList() {
        activityList.add(Activity("a", 2020, 2024, 1, 1))
        activityList.add(Activity("b", 2020, 2025, 2, 1))
        activityList.add(Activity("c", 2020, 2023, 3, 1))


    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<Activity>()
            for (i in activityList) {
                if (i.year.toString().lowercase(Locale.ROOT).contains(query)) {
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