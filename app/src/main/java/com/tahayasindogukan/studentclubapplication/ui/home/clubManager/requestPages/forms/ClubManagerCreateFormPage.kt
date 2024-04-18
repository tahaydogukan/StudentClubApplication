package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerCreateFormPageBinding

class ClubManagerCreateFormPage : Fragment() {
    private lateinit var binding: FragmentClubManagerCreateFormPageBinding
    private val viewModel: RequestViewModel by viewModels()
    private var clubName:String?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerCreateFormPageBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.checkManagerOfWhichClub()

        binding.btnSendFormRequest.setOnClickListener {
            clubName = viewModel.clubData?.clubName
            Log.e("createForm",clubName.toString())
            viewModel.sendFormRequst(
                binding.etTitle.text.toString(),
                binding.etContent.text.toString(),
                binding.etEventGoals.text.toString(),
                binding.etAgenda.text.toString(),
                binding.etStartDate.text.toString(),
                binding.etEndDate.text.toString(),
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                true,
                false,
                1,
                clubName?.lowercase().toString(),
                requireContext()
            )
        }
    }

}