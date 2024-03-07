package com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksLoginBinding

class SksLoginFragment : Fragment() {
    private lateinit var binding: FragmentSksLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View modelleri burda tanımla
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sks_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSksLoginBinding.inflate(layoutInflater)

    }
}
