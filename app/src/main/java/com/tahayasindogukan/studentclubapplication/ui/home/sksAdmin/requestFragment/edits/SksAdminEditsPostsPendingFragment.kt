package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.edits

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminEditsPostsPendingBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class SksAdminEditsPostsPendingFragment : Fragment() {
    private lateinit var binding : FragmentSksAdminEditsPostsPendingBinding
    private val args: SksAdminEditsPostsPendingFragmentArgs by navArgs()
    private lateinit var navController: NavController
    private lateinit var selectedStartDate: Date
    private lateinit var selectedEndDate: Date
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


        Glide.with(requireContext()).load(args.request.newAttachment)
            .into(binding.sksAdminEditPostPhoto)

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val formattedStartDateTime = sdf.format(args.request.startDate)
        val formattedEndDateTime = sdf.format(args.request.endDate)


        binding.sksAdminEditPostTitle.text = args.request.newTitle
        binding.sksAdminEditPostContent.text = args.request.newContent
        binding.sksAdminEditPostManager.text = args.request.newManager
        binding.sksAdminEditPostLocation.text = args.request.newLocation
        binding.sksAdminEditPostWebPlatform.text = args.request.newWebPlatform
        binding.sksAdminEditPostContacts.text = args.request.newContacts
        binding.sksAdminEditPostStartDate.text = formattedStartDateTime
        binding.sksAdminEditPostEndDate.text = formattedEndDateTime

        binding.sksAdminEditPostBtnApprove.setOnClickListener{


            val postUpdates = hashMapOf<String, Any>()
            postUpdates["status"] = "2"
            postUpdates["title"] = args.request.newTitle
            postUpdates["content"] = args.request.newContent
            postUpdates["manager"] = args.request.newManager
            postUpdates["attachment"] = args.request.newAttachment
            postUpdates["startDate"] = args.request.newStartDate
            postUpdates["endDate"] = args.request.newEndDate
            postUpdates["location"] = args.request.newLocation
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

        binding.sksAdminEditPostBtnReject.setOnClickListener {

            val postUpdates = hashMapOf<String, Any>()
            postUpdates["status"] = "2"
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