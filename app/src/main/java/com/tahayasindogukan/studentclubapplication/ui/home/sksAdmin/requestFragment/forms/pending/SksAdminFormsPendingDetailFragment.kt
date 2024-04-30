package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.pending

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
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminFormsPendingDetailBinding
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class SksAdminFormsPendingDetailFragment : Fragment() {
    private lateinit var binding: FragmentSksAdminFormsPendingDetailBinding
    private val args: SksAdminFormsPendingDetailFragmentArgs by navArgs()
    private lateinit var navController: NavController
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

        navController = Navigation.findNavController(view)

        Glide.with(requireContext()).load(args.request.attachment)
            .into(binding.sksAdminFormsPendingPhoto)

        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("Europe/Istanbul")

        val formattedStartDateTime = dateFormat.format(args.request.startDate)
        val formattedEndDateTime = dateFormat.format(args.request.endDate)


        binding.apply {

            sksAdminFormsPendingTitle.setText(args.request.title)
            sksAdminFormsPendingContent.setText(args.request.content)
            sksAdminFormsPendingEventGoals.setText(args.request.eventGoals)
            sksAdminFormsPendingAgenda.setText(args.request.agenda)
            sksAdminFormsPendingStartDate.text = formattedStartDateTime
            sksAdminFormsPendingEndDate.text = formattedEndDateTime
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
                    navController.navigate(R.id.sksAdminRequestFragment)
                    navController.popBackStack()
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Düzenleme başarısız", Toast.LENGTH_SHORT).show()
                }
            navController.navigate(R.id.sksAdminRequestFragment)
            navController.popBackStack()
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
                    navController.navigate(R.id.sksAdminRequestFragment)
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