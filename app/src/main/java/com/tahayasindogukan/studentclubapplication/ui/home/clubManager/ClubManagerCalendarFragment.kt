package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

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
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerCalendarBinding
import java.util.Locale

class ClubManagerCalendarFragment : Fragment(), ClubManagerCalendarAdapter.MyClickListener {
    private lateinit var binding: FragmentClubManagerCalendarBinding
    private lateinit var adapter: ClubManagerCalendarAdapter
    private lateinit var rv: RecyclerView
    private lateinit var navController: NavController
    private lateinit var searchView: SearchView
    private val requestViewModel: RequestViewModel by viewModels()


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

        requestViewModel.getPostApproved()

        requestViewModel.postsApprovedList.observe(viewLifecycleOwner) { request ->

            val recyclerView = binding.clubManagerCalendarFragmentRecyclerView

            adapter = ClubManagerCalendarAdapter(request, this, requireContext())
            recyclerView.adapter = adapter

        }

        binding.cancelBtn.setOnClickListener {
            requestViewModel.getPostApproved()
        }

        searchView = binding.searchBar


        val calendarView = binding.calendarView



        binding.calendarButton.setOnClickListener {
            binding.calendarView.visibility = View.VISIBLE
            binding.clubManagerCalendarFragmentRecyclerView.visibility = View.INVISIBLE

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false

                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    filterList(newText)
                    return true
                }

            })

            calendarView.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
                // Seçilen tarihi işleyin
                if (month < 10) {
                    var secilenTarih = "${dayOfMonth}/0${month + 1}/${year}"
                    searchView.setQuery(secilenTarih, true)

                } else {
                    var secilenTarih = "${dayOfMonth}/${month + 1}/${year}"
                    searchView.setQuery(secilenTarih, true)

                }

                // Filtreleme işlemini gerçekleştirin
                binding.calendarView.visibility = View.INVISIBLE
                binding.clubManagerCalendarFragmentRecyclerView.visibility = View.VISIBLE

                if (month < 10) {
                    binding.calendarButton.text = "${dayOfMonth}/0${month + 1}/${year}"
                } else {
                    binding.calendarButton.text = "${dayOfMonth}/${month + 1}/${year}"

                }

            }
        }


    }

    fun filterList(query: String?) {
        if (query != null) {
            var filteredList = ArrayList<Request>()

            requestViewModel.postsApprovedList.observe(viewLifecycleOwner) {
                var requesList = it
                for (i in requesList) {
                    var startDate = i.startDate
                    if (startDate.lowercase(Locale.ROOT).contains(query)) {
                        filteredList.add(i)
                        Log.e("SksAdminRequestList6", i.toString())

                    }
                }
            }
            Log.e("SksAdminRequestListQuery", query)
            adapter.setFilteredList(filteredList)

        }
    }


    override fun onClick(request: Request) {
        val action = ClubManagerCalendarFragmentDirections
            .actionClubManagerCalendarFragmentToClubManagerCalendarInfoFragment(request)
        findNavController().navigate(action)
    }
}