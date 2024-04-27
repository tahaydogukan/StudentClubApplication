package com.tahayasindogukan.studentclubapplication.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSecondIntroBinding


class SecondIntroFragment : Fragment() {
    private lateinit var binding: FragmentSecondIntroBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondIntroBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.btnSecondNext.setOnClickListener {
            navController.navigate(R.id.thirdIntroFragment)
        }

        binding.btnSecondSkip.setOnClickListener {
            navController.navigate(R.id.loginMainPageFragment)
        }
    }


}