package com.tahayasindogukan.studentclubapplication.ui.login.forgotPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentForgotPasswordBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksLoginBinding
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel


class ForgotPasswordFragment : Fragment() {
    private lateinit var binding : FragmentForgotPasswordBinding
    private val viewModel: FirebaseViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentForgotPasswordBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.btnBack.setOnClickListener {
            navController.navigate(R.id.action_forgotPasswordFragment_to_loginMainPageFragment)
            navController.popBackStack(R.id.forgotPasswordFragment, true)

        }

        binding.btnSend.setOnClickListener {
            val email = binding.etYourEmail.text.toString()
            if (binding.etYourEmail.text.toString().isEmpty()){
                Toast.makeText(requireContext(),"Please fill the email field",Toast.LENGTH_SHORT).show()
            }else{
                viewModel.sendPasswordResetEmail(email,requireContext())
            }
        }
    }
}