package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.approveds

import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerCreatePostBinding

class ClubManagerCreatePostFragment : Fragment() {
    private lateinit var binding: FragmentClubManagerCreatePostBinding
    private lateinit var navController: NavController
    // args ile diğer fragmentdan gelen verilere erişebiliriz
    private val args : ClubManagerCreatePostFragmentArgs by navArgs()

    private val viewModel: RequestViewModel by viewModels()
    private var uri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClubManagerCreatePostBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.apply {
            createPostsEtTitle.setText(args.request.title)
            createPostsEtManager.setText(args.request.manager)
            createPostsEtContent.setText(args.request.content)
            createPostsEtStartDate.setText(args.request.startDate)
            createPostsEtEtEndDate.setText(args.request.endDate)
            createPostsEtLocation.setText(args.request.location)
            createPostsEtEtWebPlatform.setText(args.request.webPlatform)
            createPostsEtContacts.setText(args.request.contacts)
        }


        //Galeriden fotoğraf seçme işlemini yapacak değişken
        val chooseImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
            if (it != null) {
                uri = it
            } else {
                Toast.makeText(requireContext(), "Action is failed", Toast.LENGTH_LONG).show()
            }
        }

        var DateYear: String? = null
        var DateMonth: String? = null
        var DateDay: String? = null


        //start date functions
        binding.startDateCalendarView.visibility = View.GONE

        binding.createPostsEtStartDate.setOnClickListener {
            binding.startDateCalendarView.visibility = View.VISIBLE
        }

        binding.endDateCalendarView.firstDayOfWeek = 1

        //Takvimden seçilen tarihi bir değişkene ve text view e atar
        binding.startDateCalendarView.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->

            // Seçilen tarihi işleyin
            DateYear = year.toString()
            DateMonth = month.toString()
            DateDay = dayOfMonth.toString()

            // Filtreleme işlemini gerçekleştirin
            binding.startDateCalendarView.visibility = View.INVISIBLE

            binding.createPostsEtStartDate.text = "${dayOfMonth}/${month + 1}/${year}"
        }

        //End date functions
        binding.endDateCalendarView.visibility = View.GONE

        binding.createPostsEtEtEndDate.setOnClickListener {
            binding.endDateCalendarView.visibility = View.VISIBLE
        }
        //Takvimden seçilen tarihi bir değişkene ve text view e atar
        binding.endDateCalendarView.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
            // Seçilen tarihi işleyin
            DateYear = year.toString()
            DateMonth = month.toString()
            DateDay = dayOfMonth.toString()
            // Filtreleme işlemini gerçekleştirin
            binding.endDateCalendarView.visibility = View.INVISIBLE

            binding.createPostsEtEtEndDate.text = "${dayOfMonth}/${month + 1}/${year}"
        }



        binding.createPostsBtnChooseFile.setOnClickListener {
            chooseImage.launch("image/*")
        }


        binding.createPostsBtnCreatePost.setOnClickListener {

            if (uri != null){
                viewModel.editRequest(
                    args.request.documentId,
                    binding.createPostsEtTitle.text.toString(),
                    binding.createPostsEtContent.text.toString(),
                    null,
                    null,
                    binding.createPostsEtStartDate.text.toString(),
                    binding.createPostsEtEtEndDate.text.toString(),
                    binding.createPostsEtManager.text.toString(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    binding.createPostsEtLocation.text.toString(),
                    binding.createPostsEtEtWebPlatform.text.toString(),
                    binding.createPostsEtContacts.text.toString(),
                    false,
                    true,
                    1,
                    requireContext()
                    )

            uploadPhoto(uri!!)

            }else{
                binding.createPostsEtAttachment.text = "Please upload a photo"
                binding.createPostsEtAttachment.setTextColor(Color.RED)
            }
        }




    }

    private fun uploadPhoto(selectedImageFile: Uri) {
        // Firebase Storage referansı
        val storageRef = FirebaseStorage.getInstance().getReference("RequestImage")

        // Upload Task oluşturma
        val uploadTask = storageRef.putFile(selectedImageFile)

        // Upload Task'in başarıyla tamamlanması
        uploadTask.addOnSuccessListener {
            binding.createPostsEtAttachment.text = "Succesfully uploaded!"

            //Firestore Database Referansı
            val firestoreRef = FirebaseFirestore
                .getInstance()
                .collection("request")
                .document(args.request.documentId)


            storageRef.downloadUrl.addOnSuccessListener {

                    // Collectiondaki tek bir dökümanı güncellemek için gerekli map
                    val uploadImageUri = mapOf(
                        "attachment" to it.toString()
                    )

                    // Yükleme tamamlandıktan sonra fotoğrafın URL'sini alma
                    // Fotoğraf URL'sini Firestore'a kaydetme
                    firestoreRef.update(uploadImageUri)
                        .addOnSuccessListener {
                            Log.d("Uri", "Request sent succesfully")
                            navController.navigate(R.id.clubManagerRequestFragment)
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