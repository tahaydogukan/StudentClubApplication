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
import com.google.firebase.auth.FirebaseAuth
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.ClubManagerHomePageActivity
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.SksAdminHomePageActivity

class ClubManagerFragment : Fragment() {
    private lateinit var binding: FragmentClubManagerBinding
    private lateinit var navController: NavController
    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View modelleri burda tanımla
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)





        // function of forgot password button
        binding.clubManagerFragmentTwForgotPassword.setOnClickListener {
            navController.navigate(R.id.action_clubManagerFragment_to_forgotPasswordFragment)
        }
        //function of back button
        binding.btnBack.setOnClickListener {
            navController.navigate(R.id.action_clubManagerFragment_to_loginMainPageFragment)
        }

        //Login Function
        binding.clubManagerFragmentBtnLogin.setOnClickListener {
            var mail = binding.clubManagerFragmentEtEmail.text.toString()
            var password = binding.clubManagerFragmentEtPassword.text.toString()

            if (mail=="tahayasin.dogukan@st.uskudar.edu.tr"){
                binding.clubManagerFragmentEtEmail.error="Invalid email"

            }else if (mail.isNotEmpty() && password.isNotEmpty()){

            viewModel.signInViewModel(mail,password){ success, message ->
                if (success){
                    // Giriş başarılı, ek işlemleri yapabilirsiniz.
                    val user = viewModel.currentUserViewModel()
                    startActivity(
                        Intent(requireContext(),
                            ClubManagerHomePageActivity::class.java)
                    )
                    navController.popBackStack(R.id.clubManagerFragment, true)

                }else{
                    // Giriş başarısız, kullanıcıya hata mesajı gösterilebilir.
                    Toast.makeText(
                        requireContext(),
                        "Sign in is failed: $message",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }else if (mail.isEmpty() && password.isEmpty()){
            binding.clubManagerFragmentEtPassword.error = "Invalid Password"
            binding.clubManagerFragmentEtEmail.error = "Invalid Email"

        }else if (mail.isEmpty()){
            binding.clubManagerFragmentEtEmail.error = "Invalid Email"

        }
        else if (password.isEmpty()){
            binding.clubManagerFragmentEtPassword.error = "Invalid Password"
        }
        }

        binding.clubManagerFragmentTwSignUp.setOnClickListener {
            navController.navigate(R.id.action_clubManagerFragment_to_clubManagerRegisterFragment)
        }
    }
}