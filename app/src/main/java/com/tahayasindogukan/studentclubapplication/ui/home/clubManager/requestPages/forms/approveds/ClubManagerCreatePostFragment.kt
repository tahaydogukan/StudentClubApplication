package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.approveds

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ClubManagerCreatePostFragment : Fragment() {
    private lateinit var binding: FragmentClubManagerCreatePostBinding
    private lateinit var navController: NavController

    // args ile diğer fragmentdan gelen verilere erişebiliriz
    private val args: ClubManagerCreatePostFragmentArgs by navArgs()
    private lateinit var selectedStartDate: Date
    private lateinit var selectedEndDate: Date
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

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val formattedStartDateTime = sdf.format(args.request.startDate)
        val formattedEndDateTime = sdf.format(args.request.endDate)


        binding.createPostsEtStartDate.setOnClickListener {
            showDateTimePickerDialogStartDate()
        }
        binding.createPostsEtEtEndDate.setOnClickListener {
            showDateTimePickerDialogEndDate()
        }

        binding.apply {
            createPostsEtTitle.setText(args.request.title)
            createPostsEtManager.setText(args.request.manager)
            createPostsEtContent.setText(args.request.content)
            createPostsEtStartDate.text = formattedStartDateTime
            createPostsEtEtEndDate.text = formattedEndDateTime
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


        //start date functions
        binding.startDateCalendarView.visibility = View.GONE

        binding.createPostsEtStartDate.setOnClickListener {
            binding.startDateCalendarView.visibility = View.VISIBLE
        }

        binding.endDateCalendarView.firstDayOfWeek = 1

        //Takvimden seçilen tarihi bir değişkene ve text view e atar
        binding.startDateCalendarView.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->

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

            // Filtreleme işlemini gerçekleştirin
            binding.endDateCalendarView.visibility = View.INVISIBLE

            binding.createPostsEtEtEndDate.text = "${dayOfMonth}/${month + 1}/${year}"
        }



        binding.createPostsBtnChooseFile.setOnClickListener {
            chooseImage.launch("image/*")
        }


        binding.createPostsBtnCreatePost.setOnClickListener {
            val dateTextStart = binding.createPostsEtStartDate.text.toString()
            val dateTextEnd = binding.createPostsEtEtEndDate.text.toString()

            val startDate = convertToDate(dateTextStart)
            val endDate = convertToDate(dateTextEnd)
            if (uri != null) {
                viewModel.editRequest(
                    args.request.documentId,
                    binding.createPostsEtTitle.text.toString(),
                    binding.createPostsEtContent.text.toString(),
                    null,
                    null,
                    startDate!!,
                    endDate!!,
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
                    "1",
                    requireContext()
                )

                uploadPhoto(uri!!)

                navController.navigate(R.id.clubManagerFormsApprovedPage)
                navController.popBackStack()

            } else {
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

    private fun showDateTimePickerDialogStartDate() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val timePickerDialog = TimePickerDialog(
                    requireContext(),
                    TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar.set(Calendar.MINUTE, minute)

                        selectedStartDate = calendar.time

                        // Seçilen tarihi ve saati göster
                        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                        val formattedDateTime = sdf.format(selectedStartDate)
                        binding.createPostsEtStartDate.text = formattedDateTime
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
                )
                timePickerDialog.show()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()
    }

    private fun showDateTimePickerDialogEndDate() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val timePickerDialog = TimePickerDialog(
                    requireContext(),
                    TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar.set(Calendar.MINUTE, minute)

                        selectedEndDate = calendar.time

                        // Seçilen tarihi ve saati göster
                        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                        val formattedDateTime = sdf.format(selectedEndDate)
                        binding.createPostsEtEtEndDate.text = formattedDateTime
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
                )
                timePickerDialog.show()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000

    }

    private fun convertToDate(dateString: String): Date? {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return try {
            dateFormat.parse(dateString)
        } catch (e: Exception) {
            null
        }
    }
}