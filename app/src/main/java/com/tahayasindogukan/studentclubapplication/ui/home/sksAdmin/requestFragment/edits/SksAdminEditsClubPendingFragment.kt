package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.edits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.firebase.firestore.FirebaseFirestore
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminEditsClubPendingBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminEditsPostsPendingBinding

class SksAdminEditsClubPendingFragment : Fragment() {
    private lateinit var binding : FragmentSksAdminEditsClubPendingBinding
    private val args: SksAdminEditsClubPendingFragmentArgs by navArgs()
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSksAdminEditsClubPendingBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)


        binding.sksAdminEditClubBtnApprove.setOnClickListener{

            val clubUpdates = hashMapOf<String, Any>()
            clubUpdates["status"] = 1

            clubUpdates["clubName"] = args.club.newClubName
            clubUpdates["clubDescription"] = args.club.newClubDescription
            clubUpdates["clubPhoto"] = args.club.newClubPhoto




            val postRef = FirebaseFirestore.getInstance().collection("club").document(args.club.clubId)
            postRef.update(clubUpdates)
                .addOnSuccessListener {
                    Toast.makeText(context, "Approved", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.sksAdminEditsPendingFragment)
                    navController.popBackStack()

                }
                .addOnFailureListener {
                    Toast.makeText(context, "Düzenleme başarısız", Toast.LENGTH_SHORT).show()
                }
        }

        binding.sksAdminEditClubBtnReject.setOnClickListener {

            val clubUpdates = hashMapOf<String, Any>()
            clubUpdates["status"] = 1

            clubUpdates["newClubName"] =  ""
            clubUpdates["newClubDescription"] =  ""
            clubUpdates["newClubPhoto"] = ""



            val postRef = FirebaseFirestore.getInstance().collection("club").document(args.club.clubId)
            postRef.update(clubUpdates)
                .addOnSuccessListener {
                    Toast.makeText(context, "Rejected", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.sksAdminEditsPendingFragment)
                    navController.popBackStack()
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Düzenleme başarısız", Toast.LENGTH_SHORT).show()
                }
        }


    }
}