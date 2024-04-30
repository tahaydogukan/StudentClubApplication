package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.profilePages

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.UUID

class ClubManagerProfileEditRequestFragment : Fragment() {
    private lateinit var binding: FragmentClubManagerProfileEditRequestBinding
    private val args: SksAdminFormsApprovedDetailFragmentArgs by navArgs()
    private val requestViewModel: RequestViewModel by viewModels()
    private lateinit var navController: NavController
    private var uri: Uri? = null
    var requestData: Request? = null
    private lateinit var selectedStartDate: Date
    private lateinit var selectedEndDate: Date


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


        binding.clubAdminProfileMyActivitiesEditStartDate.setOnClickListener {
            showDateTimePickerDialogStartDate()
        }
        binding.clubAdminProfileMyActivitiesEditEndDate.setOnClickListener {
            showDateTimePickerDialogEndDate()
        }

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("Europe/Istanbul")

        val formattedStartDateTime = sdf.format(args.request.startDate)
        val formattedEndDateTime = sdf.format(args.request.endDate)



        requestData = args.request

        Glide.with(requireContext()).load(args.request.attachment)
            .into(binding.clubManagerProfileMyActivitiesEditPhoto)
        binding.clubAdminProfileMyActivitiesEditTitle.setText(args.request.title)
        binding.clubAdminProfileMyActivitiesEditDescription.setText(args.request.content)
        binding.clubAdminProfileMyActivitiesEditManager.setText(args.request.manager)
        binding.clubAdminProfileMyActivitiesEditLocation.setText(args.request.location)
        binding.clubAdminProfileMyActivitiesEditWebPlatform.setText(args.request.webPlatform)
        binding.clubAdminProfileMyActivitiesEditStartDate.setText(formattedStartDateTime)
        binding.clubAdminProfileMyActivitiesEditEndDate.setText(formattedEndDateTime)
        binding.clubAdminProfileMyActivitiesEditContacts.setText(args.request.contacts)


        binding.button2.setOnClickListener {

            val dateTextStart = binding.clubAdminProfileMyActivitiesEditStartDate.text.toString()
            val dateTextEnd = binding.clubAdminProfileMyActivitiesEditEndDate.text.toString()

            val startDate = convertToDate(dateTextStart)
            val endDate = convertToDate(dateTextEnd)


            requestViewModel.editPost(
                args.request.documentId,
                binding.clubAdminProfileMyActivitiesEditTitle.text.toString(),
                binding.clubAdminProfileMyActivitiesEditManager.text.toString(),
                binding.clubAdminProfileMyActivitiesEditDescription.text.toString(),
                "",
                startDate,
                endDate,
                binding.clubAdminProfileMyActivitiesEditLocation.text.toString(),
                binding.clubAdminProfileMyActivitiesEditWebPlatform.text.toString(),
                binding.clubAdminProfileMyActivitiesEditContacts.text.toString(),
                requireContext()
            )
            if (uri != null){
                uploadPhoto(uri!!)
            }



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

    private fun convertToDate(dateString: String): Date? {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("Europe/Istanbul")

        return try {
            dateFormat.parse(dateString)
        } catch (e: Exception) {
            null
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
                        sdf.timeZone = TimeZone.getTimeZone("Europe/Istanbul")

                        val formattedDateTime = sdf.format(selectedStartDate)
                        binding.clubAdminProfileMyActivitiesEditStartDate.setText(formattedDateTime)
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
                        sdf.timeZone = TimeZone.getTimeZone("Europe/Istanbul")

                        val formattedDateTime = sdf.format(selectedEndDate)
                        binding.clubAdminProfileMyActivitiesEditEndDate.setText(formattedDateTime)
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


}