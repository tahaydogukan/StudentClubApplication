package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.profilePages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerProfileEditProfileBinding
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel


class ClubManagerProfileEditProfile : Fragment() {
    private lateinit var binding : FragmentClubManagerProfileEditProfileBinding
    private lateinit var navController: NavController
    private val firebaseViewModel: FirebaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerProfileEditProfileBinding.inflate(layoutInflater,container,false)

        // Inflate the layout for this fragment
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        firebaseViewModel.checkManagerOfWhichClub()

        firebaseViewModel.club.observe(viewLifecycleOwner) { club ->
            binding.clubManagerProfileEditProfileEtClubName.setText(club.clubName)
            binding.clubManagerProfileEditProfileEtClubDescription.setText(club.clubDescription)
            Glide.with(requireContext()).load(club.clubPhoto).into(binding.clubManagerProfileEditProfileIwClubPhoto)


            binding.clubManagerProfileEditProfileBtnUpdate.setOnClickListener {

                firebaseViewModel.editClub(
                    club.clubId,
                    "2",
                    binding.clubManagerProfileEditProfileEtClubName.text.toString(),
                    binding.clubManagerProfileEditProfileEtClubDescription.text.toString(),
                    null,
                    requireContext()
                    )
                Toast.makeText(requireContext(),"Update request is sent",Toast.LENGTH_SHORT).show()

                navController.navigate(R.id.clubManagerProfileFragment)


            }


        }






    }

}