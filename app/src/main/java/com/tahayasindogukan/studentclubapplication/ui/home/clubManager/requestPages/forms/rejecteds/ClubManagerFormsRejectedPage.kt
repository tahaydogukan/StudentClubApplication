package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.rejecteds

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
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerFormsRejectedPageBinding


class ClubManagerFormsRejectedPage : Fragment(),ClubManagerFormsRejectedRecyclerViewAdapter.FormsRejectedClickListener {
    private lateinit var binding: FragmentClubManagerFormsRejectedPageBinding
    private lateinit var adapter: ClubManagerFormsRejectedRecyclerViewAdapter
    private val viewModel: RequestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentClubManagerFormsRejectedPageBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clubManagerFormsRejectedPageRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)

        viewModel.formsRejectedList.observe(viewLifecycleOwner) { formsPending ->
            val recyclerView = binding.clubManagerFormsRejectedPageRecyclerView
            adapter = ClubManagerFormsRejectedRecyclerViewAdapter(formsPending,requireContext(),this)
            recyclerView.adapter = adapter

        }

        viewModel.getFormsRejected()
    }

    override fun onClick(request: Request) {
        val action = ClubManagerFormsRejectedPageDirections
            .actionClubManagerFormsRejectedPageToClubManagerFormsRejectedDetailPage(request)
        findNavController().navigate(action)    }

}