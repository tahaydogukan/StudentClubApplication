package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tahayasindogukan.studentclubapplication.databinding.ActivitySksAdminHomePageBinding

class SksAdminHomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySksAdminHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySksAdminHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}