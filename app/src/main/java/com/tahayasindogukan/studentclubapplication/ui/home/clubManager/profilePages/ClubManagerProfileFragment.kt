package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.profilePages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.core.entitiy.ClubManager
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerProfileBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.edits.SksAdminEditsPendingFragmentDirections
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel


class ClubManagerProfileFragment : Fragment() {

    private lateinit var binding: FragmentClubManagerProfileBinding
    private val firebaseViewModel: FirebaseViewModel by viewModels()
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerProfileBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        var club: Club? = null




        firebaseViewModel.checkManagerOfWhichClub()

        firebaseViewModel.club.observe(viewLifecycleOwner) {

            club = it

            FirebaseFirestore.getInstance().collection("clubManager").document(it.clubManagerId)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.exists()) {
                        var clubManager = querySnapshot.toObject(ClubManager::class.java)

                        binding.twClubManagerName.text = clubManager!!.name

                        Log.e("clubManager", " İşlem Başaraılı")
                    } else {
                        Log.e("clubManager", "İşlem başarısız")
                    }
                }
        }

        binding.btnNotification.setOnClickListener {
            val action = club?.let { it1 ->
                ClubManagerProfileFragmentDirections
                    .actionClubManagerProfileFragmentToClubManagerProfileNotificationFragment(it1)
            }
            action?.let { it1 -> findNavController().navigate(it1) }
        }

        binding.btnSettings.setOnClickListener {
            navController.navigate(R.id.clubManagerProfileSettings)
        }

        firebaseViewModel.club.observe(viewLifecycleOwner) { club ->
            binding.twClubName.text = club.clubName
            binding.twClubDescription.text = club.clubDescription
            Glide.with(requireContext()).load(club.clubPhoto).into(binding.clubPhoto)

        }

        binding.btnMyClubActivities.setOnClickListener{
            val action = ClubManagerProfileFragmentDirections
                .actionClubManagerProfileFragmentToClubManagerProfileMyActivities(club!!)
            findNavController().navigate(action)
        }

        binding.clubManagerProfileBtnEditProfile.setOnClickListener {
            navController.navigate(R.id.clubManagerProfileEditProfile)
        }


    }

}