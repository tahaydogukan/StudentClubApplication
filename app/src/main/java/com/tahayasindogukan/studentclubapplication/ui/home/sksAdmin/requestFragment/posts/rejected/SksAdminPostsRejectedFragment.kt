package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.posts.rejected

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
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminPostsRejectedBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.SksAdminRequestAdapter
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.posts.approved.SksAdminPostsApprovedFragmentDirections


class SksAdminPostsRejectedFragment : Fragment(),
    SksAdminRequestAdapter.SksAdminRequestAdapterClickListener {
    private lateinit var binding: FragmentSksAdminPostsRejectedBinding
    private val viewModel: RequestViewModel by viewModels()
    private lateinit var adapter: SksAdminRequestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentSksAdminPostsRejectedBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sksAdminPostsRejectedPageRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)

        viewModel.getSksPostRejected()

        viewModel.skspostsRejectedList.observe(viewLifecycleOwner) { formsApproved ->
            val recyclerView = binding.sksAdminPostsRejectedPageRecyclerView
            adapter = SksAdminRequestAdapter(formsApproved, requireContext(), this)
            recyclerView.adapter = adapter
        }

    }

    override fun onClick(
        request: Request
    ) {
        val action =
            SksAdminPostsRejectedFragmentDirections
                .actionSksAdminPostsRejectedFragmentToSksAdminPostsRejectedDetailFragment(
                    request
                )
        findNavController().navigate(action)
    }
}