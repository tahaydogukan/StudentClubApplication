package com.tahayasindogukan.studentclubapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.tahayasindogukan.studentclubapplication.core.repository.FirebaseViewModel
import com.tahayasindogukan.studentclubapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)



        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            startActivity(Intent(this, SecondActivity::class.java))
        }


        binding.mainActivityBtnSignIn.setOnClickListener {
            var email = binding.mainActivityEtMail.text.toString()
            var password = binding.mainAcitivityEtPasword.text.toString()

                viewModel.signInViewModel(email, password) { success, message ->
                    if (success) {
                        // Giriş başarılı, ek işlemleri yapabilirsiniz.
                        val user = viewModel.currentUserViewModel()
                        // User nesnesini kullanabilir veya UI'yi güncelleyebilirsiniz.
                        startActivity(Intent(this, SecondActivity::class.java))
                    } else {
                        // Giriş başarısız, kullanıcıya hata mesajı gösterilebilir.
                        Toast.makeText(
                            this,
                            "Giriş başarısız: $message",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

        }

        binding.mainActivitiyBtnSignUp.setOnClickListener {
            val email = binding.mainActivityEtMail.text.toString()
            val password = binding.mainAcitivityEtPasword.text.toString()

                viewModel.signUpViewModel(
                    email,
                    password,
                    "",
                    0,
                ) { success, message ->
                    startActivity(Intent(this, SecondActivity::class.java))
                    }
                }
        }


    }
