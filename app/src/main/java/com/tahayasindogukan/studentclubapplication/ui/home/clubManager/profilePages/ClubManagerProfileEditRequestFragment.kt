package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.profilePages

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerProfileEditRequestBinding
import com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.forms.approved.SksAdminFormsApprovedDetailFragmentArgs
import java.util.UUID

class ClubManagerProfileEditRequestFragment : Fragment() {
    private lateinit var binding: FragmentClubManagerProfileEditRequestBinding
    private val args: SksAdminFormsApprovedDetailFragmentArgs by navArgs()
    private val requestViewModel: RequestViewModel by viewModels()
    private lateinit var navController: NavController
    private var uri: Uri? = null
    var requestData: Request? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentClubManagerProfileEditRequestBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val chooseImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
            if (it != null) {
                uri = it
            } else {
                Toast.makeText(requireContext(), "Action is failed", Toast.LENGTH_LONG).show()
            }
        }

        binding.clubManagerProfileMyActivitiesEditPhoto.setOnClickListener {
            chooseImage.launch("image/*")
        }














        requestData = args.request

        Glide.with(requireContext()).load(args.request.attachment)
            .into(binding.clubManagerProfileMyActivitiesEditPhoto)
        binding.clubAdminProfileMyActivitiesEditTitle.setText(args.request.title)
        binding.clubAdminProfileMyActivitiesEditDescription.setText(args.request.content)
        binding.clubAdminProfileMyActivitiesEditManager.setText(args.request.manager)
        binding.clubAdminProfileMyActivitiesEditLocation.setText(args.request.location)
        binding.clubAdminProfileMyActivitiesEditWebPlatform.setText(args.request.webPlatform)
        binding.clubAdminProfileMyActivitiesEditStartDate.setText(args.request.startDate)
        binding.clubAdminProfileMyActivitiesEditEndDate.setText(args.request.endDate)
        binding.clubAdminProfileMyActivitiesEditContacts.setText(args.request.contacts)


        binding.button2.setOnClickListener {
            requestViewModel.editPost(
                args.request.documentId,
                binding.clubAdminProfileMyActivitiesEditTitle.text.toString(),
                binding.clubAdminProfileMyActivitiesEditManager.text.toString(),
                binding.clubAdminProfileMyActivitiesEditDescription.text.toString(),
                "",
                binding.clubAdminProfileMyActivitiesEditStartDate.text.toString(),
                binding.clubAdminProfileMyActivitiesEditEndDate.text.toString(),
                binding.clubAdminProfileMyActivitiesEditLocation.text.toString(),
                binding.clubAdminProfileMyActivitiesEditWebPlatform.text.toString(),
                binding.clubAdminProfileMyActivitiesEditContacts.text.toString(),
                requireContext()
            )
            uploadPhoto(uri!!)

            navController.navigate(R.id.clubManagerProfileFragment)
            navController.popBackStack()


        }


    }

    private fun uploadPhoto(selectedImageFile: Uri) {
        // Firebase Storage referansı
        val rastgeleAd = UUID.randomUUID().toString() // Rastgele bir UUID oluşturun
        val storageRef = FirebaseStorage.getInstance().getReference("requestImage/${rastgeleAd}")

        // Upload Task oluşturma
        val uploadTask = storageRef.putFile(selectedImageFile)

        // Upload Task'in başarıyla tamamlanması
        uploadTask.addOnSuccessListener {
            Toast.makeText(requireContext(), "Succcesfully Uploaded", Toast.LENGTH_SHORT).show()

            //Firestore Database Referansı
            val firestoreRef = FirebaseFirestore
                .getInstance()
                .collection("request")
                .document(requestData!!.documentId)


            storageRef.downloadUrl.addOnSuccessListener {

                // Collectiondaki tek bir dökümanı güncellemek için gerekli map
                val uploadImageUri = mapOf(
                    "newAttachment" to it.toString()
                )

                // Yükleme tamamlandıktan sonra fotoğrafın URL'sini alma
                // Fotoğraf URL'sini Firestore'a kaydetme
                firestoreRef.update(uploadImageUri)
                    .addOnSuccessListener {
                        Log.d("Uri", " Photo succesfully uploaded")
                        navController.navigate(R.id.clubManagerProfileFragment)
                        navController.popBackStack()


                    }
                    .addOnFailureListener { e ->
                        Log.w("Uri", "Request could not send ", e)
                    }

                Toast.makeText(requireContext(), "Request sent succesfully", Toast.LENGTH_SHORT)
                    .show()


            }

        }


    }


}