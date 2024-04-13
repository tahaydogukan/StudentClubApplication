package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.approveds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.ActivityClubManagerCalendarInfoBinding
import com.tahayasindogukan.studentclubapplication.databinding.ActivityClubManagerCreatePostBinding

class ClubManagerCreatePostActivity : AppCompatActivity() {
    private lateinit var binding:ActivityClubManagerCreatePostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubManagerCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)





    }
}