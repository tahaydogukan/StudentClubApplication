package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.rejected

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
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminFormsApprovedBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminFormsRejectedBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.SksAdminRequestAdapter
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.approved.SksAdminFormsApprovedFragmentDirections


class SksAdminFormsRejectedFragment : Fragment(),SksAdminRequestAdapter.SksAdminRequestAdapterClickListener {
    private lateinit var binding : FragmentSksAdminFormsRejectedBinding
    private lateinit var adapter: SksAdminRequestAdapter
    private val viewModel: RequestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSksAdminFormsRejectedBinding.inflate(layoutInflater,container,false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sksAdminFormsRejectedPageRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)

        viewModel.getSksFormsRejected()

        viewModel.sksformsRejectedList.observe(viewLifecycleOwner) { formsApproved ->
            val recyclerView = binding.sksAdminFormsRejectedPageRecyclerView
            adapter = SksAdminRequestAdapter(formsApproved,requireContext(),this)
            recyclerView.adapter = adapter
        }
    }
    override fun onClick(
        request: Request
    ) {
        val action = SksAdminFormsRejectedFragmentDirections
            .actionSksAdminFormsRejectedFragmentToSksAdminFormsRejectedDetailFragment(request)
        findNavController().navigate(action)
    }
}