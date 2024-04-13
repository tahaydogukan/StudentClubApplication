package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.pendings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerFormsPendingPageBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.approveds.ClubManagerFormsApprovedPageDirections

class ClubManagerFormsPendingPage : Fragment(),ClubManagerFormsPendingRecyclerViewAdapter.FormsPendingClickListener {
    private lateinit var binding: FragmentClubManagerFormsPendingPageBinding
    private lateinit var adapter: ClubManagerFormsPendingRecyclerViewAdapter
    private val viewModel: RequestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentClubManagerFormsPendingPageBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clubManagerFormsPendingPageRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)



        viewModel.formsPendingList.observe(viewLifecycleOwner) { formsPending ->
            val recyclerView = binding.clubManagerFormsPendingPageRecyclerView
            adapter = ClubManagerFormsPendingRecyclerViewAdapter(formsPending,requireContext(),this)
            recyclerView.adapter = adapter

        }

        viewModel.getFormsPending()

    }

    override fun onClick(request: Request) {
        val action = ClubManagerFormsPendingPageDirections
            .actionClubManagerFormsPendingPageToClubManagerFormsPendingDetailPage(request)
        findNavController().navigate(action)    }

}