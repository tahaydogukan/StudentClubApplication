package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.posts.pending

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
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminPostsPendingDetailBinding
import java.text.SimpleDateFormat
import java.util.Locale


class SksAdminPostsPendingDetailFragment : Fragment() {
    private lateinit var binding: FragmentSksAdminPostsPendingDetailBinding
    private lateinit var navController: NavController
    private val args: SksAdminPostsPendingDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSksAdminPostsPendingDetailBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        Glide.with(requireContext()).load(args.request.attachment)
            .into(binding.sksAdminPostsPendingPhoto)

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val formattedStartDateTime = sdf.format(args.request.startDate)
        val formattedEndDateTime = sdf.format(args.request.endDate)

        binding.apply {

            sksAdminPostsPendingTitle.setText(args.request.title)
            sksAdminPostsPendingContent.setText(args.request.content)
            sksAdminEditPostManager.text = args.request.manager
            sksAdminPostsPendingStartDate.text = formattedStartDateTime
            sksAdminPostsPendingEndDate.text = formattedEndDateTime
            sksAdminPostsPendingLocation.text = args.request.location
            sksAdminPostsPendingWebPlatform.text = args.request.webPlatform
            sksAdminPostsPendingContacts.text = args.request.contacts

        }

        binding.sksAdminPostsPendingBtnApprove.setOnClickListener {

            val ref = FirebaseFirestore.getInstance().collection("request")
                .document(args.request.documentId)
            //status 1 = pending 2=approved 3 =rejected 4=deleted


            val requestUpdates = hashMapOf<String, Any>()

            requestUpdates["status"] = "2"

            ref.update(requestUpdates)
                .addOnSuccessListener {
                    Toast.makeText(context, "Düzenleme başarılı", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.sksAdminPostsPendingFragment)
                    navController.popBackStack()
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Düzenleme başarısız", Toast.LENGTH_SHORT).show()
                }
            navController.navigate(R.id.sksAdminRequestFragment)
            navController.popBackStack()
        }
        binding.sksAdminPostsPendingBtnReject.setOnClickListener {

            val ref = FirebaseFirestore.getInstance().collection("request")
                .document(args.request.documentId)
            //status 1 = pending 2=approved 3 =rejected 4=deleted

            val requestUpdates = hashMapOf<String, Any>()

            requestUpdates["status"] = "3"

            ref.update(requestUpdates)
                .addOnSuccessListener {
                    Toast.makeText(context, "Düzenleme başarılı", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.sksAdminPostsPendingFragment)
                    navController.popBackStack()
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Düzenleme başarısız", Toast.LENGTH_SHORT).show()
                }
            navController.navigate(R.id.sksAdminRequestFragment)
            navController.popBackStack()
        }

    }

    }
