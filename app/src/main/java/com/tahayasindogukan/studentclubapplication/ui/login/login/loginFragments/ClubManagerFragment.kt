package com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerBinding

class ClubManagerFragment : Fragment() {
    private lateinit var binding: FragmentClubManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View modelleri burda tanımla
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_club_manager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentClubManagerBinding.inflate(layoutInflater)
    }
}