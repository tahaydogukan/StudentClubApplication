package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.posts.pending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminPostsPendingBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminPostsPendingDetailBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.SksAdminRequestAdapter
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.pending.SksAdminFormsPendingFragmentDirections


class SksAdminPostsPendingFragment : Fragment(),
    SksAdminRequestAdapter.SksAdminRequestAdapterClickListener {
    private lateinit var binding: FragmentSksAdminPostsPendingBinding
    private val viewModel: RequestViewModel by viewModels()
    private lateinit var adapter: SksAdminRequestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentSksAdminPostsPendingBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sksAdminPostsPendingPageRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)

        viewModel.getSksPostPending()

        viewModel.skspostsPendingList.observe(viewLifecycleOwner) { formsApproved ->
            val recyclerView = binding.sksAdminPostsPendingPageRecyclerView
            adapter = SksAdminRequestAdapter(formsApproved, requireContext(), this)
            recyclerView.adapter = adapter
        }

    }

    override fun onClick(
        request: Request
    ) {
        val action =
            SksAdminPostsPendingFragmentDirections
                .actionSksAdminPostsPendingFragmentToSksAdminPostsPendingDetailFragment(
                request
            )
        findNavController().navigate(action)
    }
}