package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.CalendarFragment

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
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminCalendarBinding
import java.text.SimpleDateFormat
import java.util.Locale


class SksAdminCalendarFragment : Fragment(), SksAdminCalendarAdapter.MyClickListener {

    private lateinit var binding: FragmentSksAdminCalendarBinding
    private lateinit var adapter: SksAdminCalendarAdapter
    private lateinit var rv: RecyclerView
    private lateinit var navController: NavController
    private lateinit var searchView: SearchView
    private val requestViewModel: RequestViewModel by viewModels()


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
        rv = binding.sksAdminCalendarFragmentRecyclerView
        //searchView = binding.sksAdminClubsFragmentSearchBar

        requestViewModel.postsApprovedList.observe(viewLifecycleOwner) {request ->
            adapter = SksAdminCalendarAdapter(request, this, requireContext())
            rv.adapter = adapter
        }

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(requireContext())


        requestViewModel.getSksPostsApprove()

        binding.cancelBtn.setOnClickListener {
            requestViewModel.getSksPostsApprove()
        }

        searchView = binding.searchBar

        val calendarView = binding.calendarView


        binding.calendarButton.setOnClickListener {
            binding.calendarView.visibility = View.VISIBLE
            binding.sksAdminCalendarFragmentRecyclerView.visibility = View.INVISIBLE


            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false

                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    //filterList(newText)
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
                binding.sksAdminCalendarFragmentRecyclerView.visibility = View.VISIBLE

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
                    val startDate = i.startDate
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val startDateString = dateFormat.format(startDate)
                    if (startDateString.lowercase(Locale.ROOT).contains(query)) {
                        filteredList.add(i)
                        Log.e("SksAdminRequestList6", i.toString())

                    }
                }
            }
            Log.e("SksAdminRequestListQuery", query)
                adapter.setFilteredList(filteredList)

        }
    }

    override fun onClick(
        request: Request
    ) {
        // Sks admin calendar info activitysi yok onu yap
        val action = SksAdminCalendarFragmentDirections
            .actionSksAdminCalendarFragmentToSksAdminClubInfoDetailFragment(request)
        findNavController().navigate(action)

    }
}