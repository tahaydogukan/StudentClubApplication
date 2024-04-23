package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.posts.approved

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
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminPostsApprovedBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminPostsPendingBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.SksAdminRequestAdapter
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.posts.pending.SksAdminPostsPendingFragmentDirections

class SksAdminPostsApprovedFragment : Fragment(),
    SksAdminRequestAdapter.SksAdminRequestAdapterClickListener {
    private lateinit var binding: FragmentSksAdminPostsApprovedBinding
    private val viewModel: RequestViewModel by viewModels()
    private lateinit var adapter: SksAdminRequestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentSksAdminPostsApprovedBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sksAdminPostsApprovedPageRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)

        viewModel.getSksPostApproved()

        viewModel.skspostsApprovedList.observe(viewLifecycleOwner) { formsApproved ->
            val recyclerView = binding.sksAdminPostsApprovedPageRecyclerView
            adapter = SksAdminRequestAdapter(formsApproved, requireContext(), this)
            recyclerView.adapter = adapter
        }

    }

    override fun onClick(
        request: Request
    ) {
        val action =
            SksAdminPostsApprovedFragmentDirections
                .actionSksAdminPostsApprovedFragmentToSksAdminPostsApprovedDetailFragment(
                    request
                )
        findNavController().navigate(action)
    }
}