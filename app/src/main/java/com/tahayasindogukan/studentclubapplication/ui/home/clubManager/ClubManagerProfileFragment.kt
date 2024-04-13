package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerProfileBinding
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginActivity.LoginMainActivity
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel


class ClubManagerProfileFragment : Fragment() {

    private lateinit var binding: FragmentClubManagerProfileBinding
    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerProfileBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.checkManagerOfWhichClub()

        viewModel.club.observe(viewLifecycleOwner){
            binding.twClubName.text = it.clubName
            binding.twClubDescription.text = it.clubDescription
            binding.twClubManagerName.text = it.clubManagerId
        }


        binding.btnLogOut.setOnClickListener {
            viewModel.signOutViewModel { val intent = Intent(requireContext(), LoginMainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(intent) }
        }

    }

}