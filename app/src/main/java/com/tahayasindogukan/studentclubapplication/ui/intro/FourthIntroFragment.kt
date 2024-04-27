package com.tahayasindogukan.studentclubapplication.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentFourthIntroBinding


class FourthIntroFragment : Fragment() {
    private lateinit var binding: FragmentFourthIntroBinding
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFourthIntroBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.btnFourthNext.setOnClickListener {
            navController.navigate(R.id.loginMainPageFragment)
        }
        binding.btnFourthSkip.setOnClickListener {
            navController.navigate(R.id.loginMainPageFragment)
        }
    }


}