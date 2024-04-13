package com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerRegisterBinding


class ClubManagerRegisterFragment : Fragment() {
    private lateinit var binding: FragmentClubManagerRegisterBinding
    private val viewModel: FirebaseViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerRegisterBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)


        binding.signUpBtn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()
            val name = binding.etName.text.toString()

            if (password == confirmPassword) {
                signUp(email, password,name,"","")

            } else {
                binding.etPassword.error = "Invalid password"
                binding.etConfirmPassword.error = "Invalid password"
            }

        }

        binding.loginBtn.setOnClickListener {
            navController.navigate(R.id.action_clubManagerRegisterFragment_to_clubManagerFragment)
        }

    }

    fun signUp(email: String, password: String,name:String,phone:String,clubId:String) {
        viewModel.signUpViewModel(email, password, phone,name,clubId) { success, message ->
            if (success) {
                // Giriş başarılı, ek işlemleri yapabilirsiniz.
                val user = viewModel.currentUserViewModel()
                navController.navigate(R.id.action_clubManagerRegisterFragment_to_clubManagerFragment)
            } else {
                // Giriş başarısız, kullanıcıya hata mesajı gösterilebilir.
                Toast.makeText(
                    requireContext(),
                    "Action is failed: $message",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}