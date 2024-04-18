package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.posts.rejecteds

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerPostsPendingPageBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerPostsRejectedPageBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.posts.pendings.ClubManagerPostsPendingPageDirections
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.posts.pendings.ClubManagerPostsPendingRecyclerViewAdapter

class ClubManagerPostsRejectedPage : Fragment(),
    ClubManagerPostsRejectedRecyclerViewAdapter.PostsRejectedClickListener {
    private lateinit var binding: FragmentClubManagerPostsRejectedPageBinding
    private lateinit var adapter: ClubManagerPostsRejectedRecyclerViewAdapter

    private val viewModel: RequestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentClubManagerPostsRejectedPageBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clubManagerPostsRejectedPageRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)

        viewModel.getPostRejected()

        viewModel.postsRejectedList.observe(viewLifecycleOwner) { formsRejected ->
            val recyclerView = binding.clubManagerPostsRejectedPageRecyclerView
            adapter =
                ClubManagerPostsRejectedRecyclerViewAdapter(formsRejected, requireContext(), this)
            recyclerView.adapter = adapter

        }

    }

    override fun onClick(request: Request) {
        val action = ClubManagerPostsRejectedPageDirections
            .actionClubManagerPostsRejectedPageToClubManagerPostsRejectedDetailPage(request)
        findNavController().navigate(action)
    }
}