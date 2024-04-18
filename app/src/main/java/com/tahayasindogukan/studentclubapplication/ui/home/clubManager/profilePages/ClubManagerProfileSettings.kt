package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.profilePages

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerProfileSettingsBinding
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginActivity.LoginMainActivity
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel


class ClubManagerProfileSettings : Fragment() {
    private lateinit var binding:FragmentClubManagerProfileSettingsBinding
    private val firebaseViewModel: FirebaseViewModel by viewModels()
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerProfileSettingsBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        // CHANGE PASSWORD
        binding.clubManagerProfileSettingsBtnChangePassword.setOnClickListener {
            navController.navigate(R.id.clubManagerProfileChangePassword)
            //Backstack deki fragmentları siler
        }

        // LOG OUT
        binding.clubManagerProfileSettingsBtnLogOut.setOnClickListener {
            firebaseViewModel.signOutViewModel { val intent = Intent(requireContext(), LoginMainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(intent) }
        }

    }

}