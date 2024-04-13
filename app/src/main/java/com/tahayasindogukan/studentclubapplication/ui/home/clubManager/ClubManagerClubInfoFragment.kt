package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerClubInfoBinding
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel

class ClubManagerClubInfoFragment : Fragment() {
    private lateinit var binding:FragmentClubManagerClubInfoBinding
    private val viewModel: FirebaseViewModel by viewModels()
    private lateinit var adapter: ClubManagerClubSearchAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerClubInfoBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}