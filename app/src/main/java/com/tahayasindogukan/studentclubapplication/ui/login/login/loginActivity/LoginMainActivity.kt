package com.tahayasindogukan.studentclubapplication.ui.login.login.loginActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.ActivityLoginMainBinding

class LoginMainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}