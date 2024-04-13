package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.databinding.ActivityClubManagerCalendarInfoBinding

class ClubManagerCalendarInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClubManagerCalendarInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubManagerCalendarInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val year = intent.getStringExtra("activityYear")
        val month = intent.getStringExtra("activityMonth")
        val day = intent.getStringExtra("activityDay")


        val activityPhoto = intent.getStringExtra("activityAttachment")
        if (activityPhoto != null) {
            Glide.with(this).load(activityPhoto).centerCrop().into(binding.iwActivityImage)
        }
        binding.twDate.text = "${year}/${month}/${day}"
        binding.twContent.text = intent.getStringExtra("activityContent")
        binding.twContacts.text = intent.getStringExtra("acitivityTags")
        binding.twLocationWebPlatform.text = intent.getStringExtra("activityLocation")
        binding.twActivityTitle.text = intent.getStringExtra("activityTitle")
        binding.twActivityManager.text = intent.getStringExtra("activityManager")

    }
}