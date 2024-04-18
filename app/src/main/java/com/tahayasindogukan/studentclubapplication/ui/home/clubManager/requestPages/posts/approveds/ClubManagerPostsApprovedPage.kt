package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.posts.approveds

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
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerPostsApprovedPageBinding

class ClubManagerPostsApprovedPage : Fragment(),
    ClubManagerPostsApprovedRecyclerViewAdapter.PostsApprovedClickListener {
    private lateinit var binding: FragmentClubManagerPostsApprovedPageBinding
    private lateinit var adapter: ClubManagerPostsApprovedRecyclerViewAdapter
    private val viewModel: RequestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentClubManagerPostsApprovedPageBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clubManagerPostsApprovedPageRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)

        viewModel.getPostApproved()

        viewModel.postsApprovedList.observe(viewLifecycleOwner) { postsApproved ->
            val recyclerView = binding.clubManagerPostsApprovedPageRecyclerView
            adapter =
                ClubManagerPostsApprovedRecyclerViewAdapter(postsApproved, requireContext(), this)
            recyclerView.adapter = adapter

        }

    }

    override fun onClick(request: Request) {
        val action = ClubManagerPostsApprovedPageDirections
            .actionClubManagerPostsApprovedPageToClubManagerPostsApprovedDetailPage(request)
        findNavController().navigate(action)
    }
}