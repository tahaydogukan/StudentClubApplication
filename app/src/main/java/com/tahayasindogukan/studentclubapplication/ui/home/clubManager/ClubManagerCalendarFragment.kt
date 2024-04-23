package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

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
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerCalendarBinding
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel
import java.util.Locale

class ClubManagerCalendarFragment : Fragment(), ClubManagerCalendarAdapter.MyClickListener {
    private lateinit var binding: FragmentClubManagerCalendarBinding
    private lateinit var adapter: ClubManagerCalendarAdapter
    private lateinit var rv: RecyclerView
    private lateinit var navController: NavController
    private lateinit var searchView: SearchView
    private val viewModel: RequestViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerCalendarBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.calendarView.visibility = View.INVISIBLE
        rv = binding.clubManagerCalendarFragmentRecyclerView

        // Verilerin çekilip live datayı tetiklemesi için viewModel deki fonksiyonu çalıştırıyoruz


        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(requireContext())


        viewModel.postsApprovedList.observe(viewLifecycleOwner) { request ->

            val recyclerView = binding.clubManagerCalendarFragmentRecyclerView

            adapter = ClubManagerCalendarAdapter(request, this)
            recyclerView.adapter = adapter

        }
        viewModel.getPostApproved()

        searchView = binding.searchBar


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
                binding.calendarButton.text = "${dayOfMonth}/${month + 1}/${year}"
                searchView.setQuery(secilenTarih, false)


            }
        }


    }

    fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<Request>()

            val activityList = viewModel.postsApprovedList.value

            if (activityList != null) {
                for (i in activityList) {
                    var startDate = i.startDate
                    if (startDate.lowercase(Locale.ROOT).contains(query)) {
                        filteredList.add(i)
                    }
                }
            }

            if (filteredList.isEmpty()) {
                Log.e("ClubManagerCalenderAdapter", "List is empty")
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }


    override fun onClick(request: Request) {

    }
}