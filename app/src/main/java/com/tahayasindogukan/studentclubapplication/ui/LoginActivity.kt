package com.tahayasindogukan.studentclubapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tahayasindogukan.studentclubapplication.databinding.ActivityLoginActivitiyBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginActivitiyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginActivitiyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            //Transaction to the Login page for a SKS ADMIN
            loginActivityTwSksOption.setOnClickListener {

            }
            //Transaction to the Login page for a Club Manager
            loginActivityTwClubManagerOption.setOnClickListener {

            }
            //Transaction to the Login page for a Vısıtor
            loginActivityTwVisitorOption.setOnClickListener {

            }
        }
    }
}