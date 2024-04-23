package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminRequestBinding


class SksAdminRequestFragment : Fragment() {

    private lateinit var binding: FragmentSksAdminRequestBinding
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSksAdminRequestBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)


         binding.apply {
             sksAdminRequestBtnFormsPending.setOnClickListener {
                 navController.navigate(R.id.sksAdminFormsPendingFragment)
             }

             sksAdminRequestBtnFormsApproved.setOnClickListener {
                 navController.navigate(R.id.sksAdminFormsApprovedFragment)
             }
             sksAdminRequestBtnFormsRejected.setOnClickListener {
                 navController.navigate(R.id.sksAdminFormsRejectedFragment)
             }
             sksAdminRequestBtnPostsPending.setOnClickListener {
                 navController.navigate(R.id.sksAdminPostsPendingFragment)
             }
             sksAdminRequestBtnPostsApproved.setOnClickListener {
                 navController.navigate(R.id.sksAdminPostsApprovedFragment)
             }
             sksAdminRequestBtnPostsRejected.setOnClickListener {
                 navController.navigate(R.id.sksAdminPostsRejectedFragment)
             }
             sksAdminRequestBtnEditsPending.setOnClickListener {
                 navController.navigate(R.id.sksAdminEditsPendingFragment)
             }
         }


    }

}