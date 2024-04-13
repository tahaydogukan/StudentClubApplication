package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.CalendarFragment

import android.content.Intent
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tahayasindogukan.studentclubapplication.core.entitiy.Activity
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminCalendarBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.ClubManagerCalendarAdapter
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.ClubManagerCalendarInfoActivity
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel
import java.util.Locale


class SksAdminCalendarFragment : Fragment(), SksAdminCalendarAdapter.MyClickListener {

    private lateinit var binding: FragmentSksAdminCalendarBinding
    private lateinit var adapter: SksAdminCalendarAdapter
    private lateinit var rv: RecyclerView
    private lateinit var navController: NavController
    var activityList = mutableListOf<Activity>()
    private lateinit var searchView: SearchView
    private val viewModel: FirebaseViewModel by viewModels()


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
        rv = binding.clubManagerCalendarFragmentRecyclerView
        //searchView = binding.sksAdminClubsFragmentSearchBar

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(requireContext())
        searchView = binding.searchBar

        adapter = SksAdminCalendarAdapter(activityList,this)
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
            binding.clubManagerCalendarFragmentRecyclerView.visibility = View.INVISIBLE

            calendarView.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
                // Seçilen tarihi işleyin
                var secilenTarih = "${dayOfMonth}/${month + 1}/${year}"

                // Filtreleme işlemini gerçekleştirin
                binding.calendarView.visibility = View.INVISIBLE
                binding.clubManagerCalendarFragmentRecyclerView.visibility = View.VISIBLE
                binding.calendarButton.text = "${dayOfMonth}/${month+1}/${year}"
                searchView.setQuery(secilenTarih, false)
            }
        }


    }

    fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<Activity>()

            val activityList = viewModel.activies.value

            if (activityList != null) {
                for (i in activityList) {
                    var year = i.activityYear
                    var month = i.activityMonth
                    var day = i.activityDay
                    var date = "$day/$month/$year"
                    if (date.lowercase(Locale.ROOT).contains(query)) {
                        filteredList.add(i)
                    }
                }
            }

            if (filteredList.isEmpty()) {
                Log.e("SksAdminCalendarFragment", "List is empty")
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    override fun onClick(
        activityTitle: String,
        activityContent: String,
        activityLocation: String,
        activityManager: String,
        activityAttachment: String,
        activityYear: String,
        activityMonth: String,
        activityDay: String,
        activityTags: String
    ) {
        // Sks admin calendar info activitysi yok onu yap
        val intent = Intent(requireContext(), ClubManagerCalendarInfoActivity::class.java)

        intent.putExtra("activityTitle", activityTitle)
        intent.putExtra("activityContent", activityContent)
        intent.putExtra("activityLocation", activityLocation)
        intent.putExtra("activityManager", activityManager)
        intent.putExtra("activityAttachment", activityAttachment)
        intent.putExtra("activityYear", activityYear)
        intent.putExtra("activityMonth", activityMonth)
        intent.putExtra("activityDay", activityDay)
        intent.putExtra("activityTags", activityTags)

        startActivity(intent)    }
}