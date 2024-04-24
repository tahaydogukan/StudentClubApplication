package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerClubBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.clubsFragment.adapter.SksAdminClubSearchAdapter
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel
import java.util.Locale

class ClubManagerClubFragment : Fragment(),
    SksAdminClubSearchAdapter.SksAdminClubSearchClickListener {
    private lateinit var binding: FragmentClubManagerClubBinding
    private lateinit var navController: NavController
    private lateinit var searchView: SearchView
    private lateinit var adapter: SksAdminClubSearchAdapter
    private lateinit var rv: RecyclerView

    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerClubBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Search view için adapterı initialize ediyoruz

        navController = Navigation.findNavController(view)
        rv = binding.clubManagerClubsFragmentRecyclerview
        rv.layoutManager = GridLayoutManager(requireContext(), 2)

        //search view initialize ettik
        searchView = binding.clubManagerClubFragmentSearchView



        // Verilerin çekilip live datayı tetiklemesi için viewModel deki fonksiyonu çalıştırıyoruz
        viewModel.getClubs()

        //view modelden gelen club listesini adaptera veriyoruz
        viewModel.clubs.observe(viewLifecycleOwner) { clubs ->
            adapter = SksAdminClubSearchAdapter(clubs, this, requireContext())
            //Recycler view tasarımını tanımladık burda
            rv.adapter = adapter

        }



        binding.apply {
            btnCategoryAcademic.setOnClickListener {
                clubManagerClubFragmentSearchView.setQuery(btnCategoryAcademic.text,true)
            }
            btnCategoryHealths.setOnClickListener {
                clubManagerClubFragmentSearchView.setQuery(btnCategoryHealths.text,true)
            }
            btnCategorySports.setOnClickListener {
                clubManagerClubFragmentSearchView.setQuery(btnCategorySports.text,true)
            }

        }
        //Search view query işlemleri
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText=="Academic"){
                    filterList(newText, 2)
                }else if (newText=="Sports"){
                    filterList(newText, 2)
                }else if (newText=="Health"){
                    filterList(newText, 2)
                }else{
                    filterList(newText, 1)
                }

                return true
            }
        })

    }

    // Search viewdeki querye göre filtreleme yapan fonksiyon
    private fun filterList(query: String?,queryParamter:Int) {
        if (query != null) {
            val filteredList = ArrayList<Club>()
            val clubList = viewModel.clubs.value

            if(queryParamter==2){
                for (i in clubList!!) {
                    if (i.clubCategory.lowercase().contains(query)) {
                        filteredList.add(i)
                        Log.e("veri 2",i.toString())

                    }
                }
            }
            else if (queryParamter==1) {
                for (i in clubList!!) {
                    if (i.clubName.lowercase(Locale.ROOT).contains(query)) {
                        filteredList.add(i)
                        Log.e("veri 1",i.toString())
                    }
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }


    override fun onClick(club: Club) {
        val action = ClubManagerClubFragmentDirections
            .actionClubManagerClubFragmentToClubManagerClubActivitiesFragment(club)
        findNavController().navigate(action)
    }

}