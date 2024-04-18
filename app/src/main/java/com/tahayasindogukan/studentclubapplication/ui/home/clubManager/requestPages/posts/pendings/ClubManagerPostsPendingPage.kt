package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.posts.pendings

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
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerPostsPendingPageBinding

class ClubManagerPostsPendingPage : Fragment(),
    ClubManagerPostsPendingRecyclerViewAdapter.PostsPendingClickListener {
    private lateinit var binding: FragmentClubManagerPostsPendingPageBinding
    private lateinit var adapter: ClubManagerPostsPendingRecyclerViewAdapter

    private val viewModel: RequestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentClubManagerPostsPendingPageBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clubManagerPostsPendingPageRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)

        viewModel.getPostPending()

        viewModel.postsPendingList.observe(viewLifecycleOwner) { formsPending ->
            val recyclerView = binding.clubManagerPostsPendingPageRecyclerView
            adapter =
                ClubManagerPostsPendingRecyclerViewAdapter(formsPending, requireContext(), this)
            recyclerView.adapter = adapter

        }

    }

    override fun onClick(request: Request) {
        val action = ClubManagerPostsPendingPageDirections
            .actionClubManagerPostsPendingPageToClubManagerPostsPendingDetailPage(request)
        findNavController().navigate(action)
    }
}