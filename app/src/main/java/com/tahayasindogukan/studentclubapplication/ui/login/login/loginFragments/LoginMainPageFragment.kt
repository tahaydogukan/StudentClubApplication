package com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentLoginMainPageBinding

class LoginMainPageFragment : Fragment() {


    private lateinit var binding: FragmentLoginMainPageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View modelleri burda tanımla
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_main_page, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginMainPageBinding.inflate(layoutInflater)




    }

}