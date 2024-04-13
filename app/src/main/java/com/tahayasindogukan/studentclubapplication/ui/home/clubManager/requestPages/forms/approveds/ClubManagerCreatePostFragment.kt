package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.approveds

import android.icu.util.Calendar
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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerCreatePostBinding
import java.text.SimpleDateFormat
import java.util.BitSet
import java.util.Locale

class ClubManagerCreatePostFragment : Fragment() {
    private lateinit var binding: FragmentClubManagerCreatePostBinding
    private lateinit var navController: NavController
    // args ile diğer fragmentdan gelen verilere erişebiliriz
    private val args : ClubManagerCreatePostFragmentArgs by navArgs()

    private val viewModel: RequestViewModel by viewModels()
    private var uri: Uri? = null
    private lateinit var realtimeDatabaseRef: DatabaseReference


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
            etTitle.setText(args.request.title)
            etManager.setText(args.request.manager)
            etContent.setText(args.request.content)
            etLocation.setText(args.request.location)
            etWebPlatform.setText(args.request.webPlatform)
            etContacts.setText(args.request.contacts)
        }


        //variable which Choose a photo from gallery
        val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
            if (it != null) {
                uri = it
            } else {
                Toast.makeText(requireContext(), "Image is not selected!", Toast.LENGTH_LONG).show()
            }
        }

        var DateYear: String? = null
        var DateMonth: String? = null
        var DateDay: String? = null


        //start date functions
        binding.startDateCalendarView.visibility = View.GONE

        binding.twStartTime.setOnClickListener {
            binding.startDateCalendarView.visibility = View.VISIBLE
        }

        binding.endDateCalendarView.firstDayOfWeek = 1

        val date = Calendar.getInstance()
        val sdf = SimpleDateFormat("yy/MM/dd")
        val curDate = sdf.format(date.time)
        Log.d("CUR_DATE", curDate)

        //Takvimden seçilen tarihi bir değişkene ve text view e atar
        binding.startDateCalendarView.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->

            // Seçilen tarihi işleyin
            DateYear = year.toString()
            DateMonth = month.toString()
            DateDay = dayOfMonth.toString()

            // Filtreleme işlemini gerçekleştirin
            binding.startDateCalendarView.visibility = View.INVISIBLE

            binding.twStartTime.text = "${dayOfMonth}/${month+1}/${year}"
        }

        //End date functions
        binding.endDateCalendarView.visibility = View.GONE

        binding.twEndTime.setOnClickListener {
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

            binding.twEndTime.text = "${dayOfMonth}/${month+1}/${year}"
        }

        //Galeriden fotoğraf seçme işlemini yapacak değişken
        val chooseImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
            if (it != null) {
                uri = it
            } else {
                Toast.makeText(requireContext(), "Başarısız", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnChooseFile.setOnClickListener {
            pickImage.launch("image/*")
        }










       /* binding.btnSend.setOnClickListener {
            viewModel.sendFormRequst(
                binding.etTitle.text.toString(),
                binding.etManager.text.toString(),
                binding.etContent.text.toString(),
                "attachment",
                binding.etLocationWebPlatform.text.toString(),
                binding.etTagsLabels.text.toString(),
                //DateYear!!,
                //DateMonth!!,
                //DateDay!!,
                requireContext()
            )
            uploadPhoto(uri!!)
        }*/


        // navController.navigate(R.id.clubManagerHomePageFragment)

    }

    private fun uploadPhoto(selectedImageFile: Uri) {
        // Firebase Storage referansı
        val storageRef = FirebaseStorage.getInstance()
            .getReference("ActivitiyImage")
        //.getReference("ActivitiyImage/${FirebaseAuth.getInstance().currentUser?.uid}")
        //üstteki gibi user di olablr sonradan


        // Upload Task oluşturma
        val uploadTask = storageRef.putFile(selectedImageFile)

        // Yükleme işleminin başarıyla tamamlanması
        uploadTask.addOnSuccessListener {

            // Yükleme tamamlandıktan sonra fotoğrafın URL'sini alma
            // Fotoğraf URL'sini Realtime Database'e kaydetme
            // Realtime Database referansı
            val databaseRef = FirebaseDatabase.getInstance().getReference("ActivitiyImage")
            //child(FirebaseAuth.getInstance().currentUser?.uid!!)
            storageRef.downloadUrl.addOnSuccessListener {

                val user = Request(
                    "1",
                    it.toString()
                )
                // Nesneyi Realtime Database'e kaydetme
                databaseRef.setValue(user)
                Toast.makeText(requireContext(), "Fotoğrafınız Güncellendi!", Toast.LENGTH_SHORT)
                    .show()

            }

        }


    }
}