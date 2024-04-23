package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.pending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminFormsPendingDetailBinding

class SksAdminFormsPendingDetailFragment : Fragment() {
    private lateinit var binding: FragmentSksAdminFormsPendingDetailBinding
    private val args: SksAdminFormsPendingDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentSksAdminFormsPendingDetailBinding.inflate(layoutInflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Glide.with(requireContext()).load(args.request.attachment)
            .into(binding.sksAdminFormsPendingPhoto)

        binding.apply {

            sksAdminFormsPendingTitle.setText(args.request.title)
            sksAdminFormsPendingContent.setText(args.request.content)
            sksAdminFormsPendingEventGoals.setText(args.request.eventGoals)
            sksAdminFormsPendingAgenda.setText(args.request.agenda)
            sksAdminFormsPendingStartDate.setText(args.request.startDate)
            sksAdminFormsPendingEndDate.setText(args.request.endDate)
        }

        binding.sksAdminFormsPendingBtnApprove.setOnClickListener {

            val ref = FirebaseFirestore.getInstance().collection("request")
                .document(args.request.documentId)
            //status 1 = pending 2=approved 3 =rejected 4=deleted


            val requestUpdates = hashMapOf<String, Any>()

            requestUpdates["status"] = "2"

            ref.update(requestUpdates)
                .addOnSuccessListener {
                    Toast.makeText(context, "Düzenleme başarılı", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Düzenleme başarısız", Toast.LENGTH_SHORT).show()
                }
        }
        binding.sksAdminFormsPendingBtnReject.setOnClickListener {

            val ref = FirebaseFirestore.getInstance().collection("request")
                .document(args.request.documentId)
            //status 1 = pending 2=approved 3 =rejected 4=deleted

            val requestUpdates = hashMapOf<String, Any>()

            requestUpdates["status"] = "3"

            ref.update(requestUpdates)
                .addOnSuccessListener {
                    Toast.makeText(context, "Düzenleme başarılı", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Düzenleme başarısız", Toast.LENGTH_SHORT).show()
                }
        }

    }

}