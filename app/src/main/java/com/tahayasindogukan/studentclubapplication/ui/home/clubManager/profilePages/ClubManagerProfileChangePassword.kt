package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.profilePages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerProfileChangePasswordBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerProfileEditProfileBinding
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel


class ClubManagerProfileChangePassword : Fragment() {
    private lateinit var binding : FragmentClubManagerProfileChangePasswordBinding
    private lateinit var navController: NavController
    private val firebaseViewModel: FirebaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerProfileChangePasswordBinding.inflate(layoutInflater,container,false)

        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.clubManagerProfileChangePasswordBtnUpdate.setOnClickListener {
            val newPassword = binding.clubManagerProfileChangePasswordEtNewPassword.text.toString()
            val confirmNewPassword = binding.clubManagerProfileChangePasswordEtConfirmNewPassword.text.toString()

            if (newPassword==confirmNewPassword){
                firebaseViewModel.updatePassword(newPassword) { isSuccessful ->
                    if (isSuccessful) {
                        // Kullanıcının şifresi başarıyla değiştirildi.
                        Toast.makeText(requireContext(),"Password is changed", Toast.LENGTH_SHORT).show()
                        navController.navigate(R.id.clubManagerProfileFragment)
                        navController.popBackStack()
                    } else {
                        // Şifre değiştirmede hata oluştu.
                        // Hata mesajını işlemeniz gerekir.
                        Toast.makeText(requireContext(),"Password is not changed", Toast.LENGTH_SHORT).show()

                    }
                }
            }else{
                binding.clubManagerProfileChangePasswordEtNewPassword.error = "Check Password"
                binding.clubManagerProfileChangePasswordEtConfirmNewPassword.error = "Check Password"
            }


        }





    }

}