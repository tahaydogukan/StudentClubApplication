package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.databinding.ActivityClubManagerClubInfoBinding
import com.tahayasindogukan.studentclubapplication.databinding.ActivitySksAdminHomePageBinding

class ClubManagerClubInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClubManagerClubInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubManagerClubInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val clubPhoto =intent.getStringExtra("clubPhoto")
        Glide.with(this).load(clubPhoto).centerCrop().into(binding.clubPhoto)

        binding.clubName.text = intent.getStringExtra("clubName")

        binding.clubDescription.text = intent.getStringExtra("clubDescription")

        binding.clubManagerName.text = intent.getStringExtra("clubManager")
    }



}