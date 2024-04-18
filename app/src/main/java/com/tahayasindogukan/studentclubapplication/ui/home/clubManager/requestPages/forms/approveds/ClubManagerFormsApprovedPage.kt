package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.approveds

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerFormsApprovedPageBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.ClubManagerClubInfoActivity
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.pendings.ClubManagerFormsPendingRecyclerViewAdapter

class ClubManagerFormsApprovedPage : Fragment(),ClubManagerFormsApprovedRecyclerViewAdapter.FormsApprovedClickListener {
    private lateinit var binding: FragmentClubManagerFormsApprovedPageBinding
    private lateinit var adapter: ClubManagerFormsApprovedRecyclerViewAdapter
    private val viewModel: RequestViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerFormsApprovedPageBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clubManagerFormsApprovedPageRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)

        viewModel.getFormsApproved()

        viewModel.formsApprovedList.observe(viewLifecycleOwner) { formsApproved ->
            val recyclerView = binding.clubManagerFormsApprovedPageRecyclerView
            adapter = ClubManagerFormsApprovedRecyclerViewAdapter(formsApproved,requireContext(),this)
            recyclerView.adapter = adapter
        }

    }

    override fun onClick(
        request:Request
    ) {
        val action = ClubManagerFormsApprovedPageDirections
            .actionClubManagerFormsApprovedPageToClubManagerFormsApprovedDetailPage(request)
        findNavController().navigate(action)
    }


}