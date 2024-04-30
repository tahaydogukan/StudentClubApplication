package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.profileFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminProfileBinding
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginActivity.LoginMainActivity
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel


class SksAdminProfileFragment : Fragment() {
    private lateinit var binding: FragmentSksAdminProfileBinding
    private val firebaseViewModel: FirebaseViewModel by viewModels()
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSksAdminProfileBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)


        Glide.with(requireContext()).load(R.drawable.sks_icon).into(binding.imageView14)

        // LOG OUT
        binding.button8.setOnClickListener {
            firebaseViewModel.signOutViewModel {
                val intent = Intent(requireContext(), LoginMainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(intent)

            }


        }
    }

}

