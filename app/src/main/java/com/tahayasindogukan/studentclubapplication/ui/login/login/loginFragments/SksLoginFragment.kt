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
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksLoginBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.SksAdminHomePageActivity

class SksLoginFragment : Fragment() {
    private lateinit var binding: FragmentSksLoginBinding
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
        // Inflate the layout for this fragment
        binding= FragmentSksLoginBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        //function of back button

        binding.btnBack.setOnClickListener {
            navController.navigate(R.id.action_sksLoginFragment_to_loginMainPageFragment)
            navController.popBackStack(R.id.sksLoginFragment,true)
        }

        val Sksmail = "tahayasin.dogukan@st.uskudar.edu.tr"
        val Skspassword = "1597536Yasin"

        binding.sksFragmentBtnLogin.setOnClickListener {
            var mail = binding.sksFragmentEtEmail.text.toString()
            var password = binding.sksFragmentEtPassword.text.toString()

            if (mail == Sksmail && password == Skspassword){

                viewModel.signInViewModel(mail,password){ success, message ->
                    if (success){
                        // Giriş başarılı, ek işlemleri yapabilirsiniz.
                        val user = viewModel.currentUserViewModel()
                        startActivity(Intent(requireContext(),SksAdminHomePageActivity::class.java))

                    }else{
                        // Giriş başarısız, kullanıcıya hata mesajı gösterilebilir.
                        Toast.makeText(
                            requireContext(),
                            "Giriş başarısız: $message",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }else if (mail != Sksmail && password != Skspassword){
                binding.sksFragmentEtPassword.error = "Invalid Password"
                binding.sksFragmentEtEmail.error = "Invalid Email"
                Toast.makeText(requireContext(),"İkiside hatalı",Toast.LENGTH_SHORT)

            }else if (mail != Sksmail){
                binding.sksFragmentEtEmail.error = "Invalid Email"
                Toast.makeText(requireContext(),"mail hatalı",Toast.LENGTH_SHORT)

            }
            else{
                binding.sksFragmentEtPassword.error = "Invalid Password"
                Toast.makeText(requireContext(),"şifre hatalı",Toast.LENGTH_SHORT)


            }
        }

    }
}
