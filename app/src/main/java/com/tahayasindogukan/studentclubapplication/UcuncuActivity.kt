package com.tahayasindogukan.studentclubapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahayasindogukan.studentclubapplication.core.adapter.RequestsAdapter
import com.tahayasindogukan.studentclubapplication.core.repository.FirebaseViewModel
import com.tahayasindogukan.studentclubapplication.databinding.ActivityMainBinding
import com.tahayasindogukan.studentclubapplication.databinding.ActivityUcuncuBinding

class UcuncuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUcuncuBinding
    private val firebaseViewModel: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUcuncuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.layoutManager = GridLayoutManager(this,2)

        firebaseViewModel.getAllRequests().observe(this){requests->
            val recyclerView = binding.rv
            val adapter =RequestsAdapter(requests)
            recyclerView.adapter=adapter

        }



    }
}