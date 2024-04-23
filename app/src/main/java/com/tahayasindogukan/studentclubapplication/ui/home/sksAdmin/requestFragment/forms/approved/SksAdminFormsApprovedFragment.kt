package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.approved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminFormsApprovedBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.approveds.ClubManagerFormsApprovedPageDirections
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.approveds.ClubManagerFormsApprovedRecyclerViewAdapter
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.SksAdminRequestAdapter


class SksAdminFormsApprovedFragment : Fragment(),SksAdminRequestAdapter.SksAdminRequestAdapterClickListener {
    private lateinit var binding : FragmentSksAdminFormsApprovedBinding
    private lateinit var adapter: SksAdminRequestAdapter
    private val viewModel: RequestViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSksAdminFormsApprovedBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.sksAdminFormsApprovedPageRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)

        viewModel.getSksFormsApproved()

        viewModel.sksformsApprovedList.observe(viewLifecycleOwner) { formsApproved ->
            val recyclerView = binding.sksAdminFormsApprovedPageRecyclerView
            adapter = SksAdminRequestAdapter(formsApproved,requireContext(),this)
            recyclerView.adapter = adapter
        }
    }

    override fun onClick(
        request:Request
    ) {
        val action = SksAdminFormsApprovedFragmentDirections
            .actionSksAdminFormsApprovedFragmentToSksAdminFormsApprovedDetailFragment(request)
        findNavController().navigate(action)
    }


}