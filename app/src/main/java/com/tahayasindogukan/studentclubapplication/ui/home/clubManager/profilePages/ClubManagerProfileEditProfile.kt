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
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerProfileEditProfileBinding
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel


class ClubManagerProfileEditProfile : Fragment() {
    private lateinit var binding : FragmentClubManagerProfileEditProfileBinding
    private lateinit var navController: NavController
    private val firebaseViewModel: FirebaseViewModel by viewModels()
    private var uri: Uri? = null
    var clubData: Club? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerProfileEditProfileBinding.inflate(layoutInflater,container,false)

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

        binding.clubManagerProfileEditProfileIwClubPhoto.setOnClickListener {
            chooseImage.launch("image/*")
        }




        firebaseViewModel.checkManagerOfWhichClub()

        firebaseViewModel.club.observe(viewLifecycleOwner) { club ->
            binding.clubManagerProfileEditProfileEtClubName.setText(club.clubName)
            binding.clubManagerProfileEditProfileEtClubDescription.setText(club.clubDescription)
            Glide.with(requireContext()).load(club.clubPhoto).into(binding.clubManagerProfileEditProfileIwClubPhoto)
            clubData = club

            binding.clubManagerProfileEditProfileBtnUpdate.setOnClickListener {

                firebaseViewModel.editClub(
                    club.clubId,
                    "2",
                    binding.clubManagerProfileEditProfileEtClubName.text.toString(),
                    binding.clubManagerProfileEditProfileEtClubDescription.text.toString(),
                    null,
                    requireContext()
                    )
                uploadPhoto(uri!!)
                Toast.makeText(requireContext(),"Update request is sent",Toast.LENGTH_SHORT).show()

                navController.navigate(R.id.clubManagerProfileFragment)


            }


        }






    }

    private fun uploadPhoto(selectedImageFile: Uri) {
        // Firebase Storage referansı
        val storageRef = FirebaseStorage.getInstance().getReference("ClubImage")

        // Upload Task oluşturma
        val uploadTask = storageRef.putFile(selectedImageFile)

        // Upload Task'in başarıyla tamamlanması
        uploadTask.addOnSuccessListener {
            Toast.makeText(requireContext(), "Succcesfully Uploaded", Toast.LENGTH_SHORT).show()

            //Firestore Database Referansı
            val firestoreRef = FirebaseFirestore
                .getInstance()
                .collection("club")
                .document(clubData!!.clubId)


            storageRef.downloadUrl.addOnSuccessListener {

                // Collectiondaki tek bir dökümanı güncellemek için gerekli map
                val uploadImageUri = mapOf(
                    "newClubPhoto" to it.toString()
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