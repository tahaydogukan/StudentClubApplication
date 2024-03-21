package com.tahayasindogukan.studentclubapplication.ui.home.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tahayasindogukan.studentclubapplication.databinding.FragmentStudentHomePageBinding

class StudentHomePageFragment : Fragment() {
    private lateinit var binding: FragmentStudentHomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentHomePageBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

}