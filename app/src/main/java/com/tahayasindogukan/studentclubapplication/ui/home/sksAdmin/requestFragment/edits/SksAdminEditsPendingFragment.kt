package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.edits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminEditsPendingBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.SksAdminClubEditsAdapter
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.SksAdminRequestAdapter
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.pending.SksAdminFormsPendingFragmentDirections


class SksAdminEditsPendingFragment : Fragment(),
    SksAdminRequestAdapter.SksAdminRequestAdapterClickListener,
    SksAdminClubEditsAdapter.SksAdminEditsClubAdapterClickListener{
    private lateinit var binding : FragmentSksAdminEditsPendingBinding
    private val viewModel: RequestViewModel by viewModels()
    private lateinit var clubAdapter: SksAdminClubEditsAdapter
    private lateinit var postAdapter: SksAdminRequestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSksAdminEditsPendingBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sksAdminEditsPostsRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)

        binding.sksAdminEditsProfileRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)


        viewModel.getClubPostEdits()

        viewModel.requestEditList.observe(viewLifecycleOwner) { formsApproved ->
            val recyclerView = binding.sksAdminEditsPostsRecyclerView
            postAdapter = SksAdminRequestAdapter(formsApproved, requireContext(), this)
            recyclerView.adapter = postAdapter
        }

        viewModel.getClubEdits()

        viewModel.clubEditList.observe(viewLifecycleOwner) { formsApproved ->
            val recyclerView = binding.sksAdminEditsPostsRecyclerView
            clubAdapter = SksAdminClubEditsAdapter(formsApproved, requireContext(), this)
            recyclerView.adapter = clubAdapter
        }

    }

    override fun onClick(club: Club) {
        val action = SksAdminEditsPendingFragmentDirections
            .actionSksAdminEditsPendingFragmentToSksAdminEditsClubPendingFragment(club)
        findNavController().navigate(action)
    }

    override fun onClick(request: Request) {
        val action = SksAdminEditsPendingFragmentDirections
            .actionSksAdminEditsPendingFragmentToSksAdminEditsPostsPendingFragment(request)
        findNavController().navigate(action)
    }
}