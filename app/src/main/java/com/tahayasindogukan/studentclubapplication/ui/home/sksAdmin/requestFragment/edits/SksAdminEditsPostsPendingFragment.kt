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
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminEditsPendingBinding
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminEditsPostsPendingBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.pending.SksAdminFormsPendingDetailFragmentArgs


class SksAdminEditsPostsPendingFragment : Fragment() {
    private lateinit var binding : FragmentSksAdminEditsPostsPendingBinding
    private val args: SksAdminEditsPostsPendingFragmentArgs by navArgs()
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSksAdminEditsPostsPendingBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)


        binding.sksAdminEditPostBtnApprove.setOnClickListener{

            val postUpdates = hashMapOf<String, Any>()
            postUpdates["status"] = 2
            postUpdates["title"] = args.request.newTitle
            postUpdates["content"] = args.request.newContent
            postUpdates["manager"] = args.request.newManager
            postUpdates["attachment"] = args.request.newAttachment
            postUpdates["startDate"] = args.request.newLocation
            postUpdates["endDate"] = args.request.newStartDate
            postUpdates["location"] = args.request.newEndDate
            postUpdates["webPlatform"] = args.request.newWebPlatform
            postUpdates["contacts"] = args.request.newContacts



            val postRef = FirebaseFirestore.getInstance().collection("request").document(args.request.documentId)
            postRef.update(postUpdates)
                .addOnSuccessListener {
                    Toast.makeText(context, "Approved", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.sksAdminEditsPendingFragment)
                    navController.popBackStack()

                }
                .addOnFailureListener {
                    Toast.makeText(context, "Düzenleme başarısız", Toast.LENGTH_SHORT).show()
                }
        }

        binding.sksAdminFormsPendingBtnReject.setOnClickListener {

            val postUpdates = hashMapOf<String, Any>()
            postUpdates["status"] = 2
            postUpdates["newTitle"] =  ""
            postUpdates["newManager"] =  ""
            postUpdates["newContent"] =  ""
            postUpdates["newAttachment"] =  ""
            postUpdates["newStartDate"] =  ""
            postUpdates["newEndDate"] =  ""
            postUpdates["newLocation"] =  ""
            postUpdates["newWebPlatform"] =  ""
            postUpdates["newContacts"] =  ""


            val postRef = FirebaseFirestore.getInstance().collection("request").document(args.request.documentId)
            postRef.update(postUpdates)
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