package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerCreateFormPageBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class ClubManagerCreateFormPage : Fragment() {
    private lateinit var binding: FragmentClubManagerCreateFormPageBinding
    private val viewModel: RequestViewModel by viewModels()
    private var clubName:String?=null
    private  var selectedStartDate: Date?=null
    private  var selectedEndDate: Date?=null
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentClubManagerCreateFormPageBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.etStartDate.setOnClickListener {
            showDateTimePickerDialogStartDate()
        }
        binding.etEndDate.setOnClickListener {
            showDateTimePickerDialogEndDate()
        }



        viewModel.checkManagerOfWhichClub()

        binding.btnSendFormRequest.setOnClickListener {

            val editText1 = binding.etTitle
            val editText2 = binding.etContent
            val editText3 = binding.etEventGoals
            val editText4 = binding.etAgenda
            val editText5 = binding.etStartDate
            val editText6= binding.etEndDate

            val editTextList = listOf(editText1, editText2, editText3, editText4, editText5,editText6)
            val emptyEditTextList = mutableListOf<EditText>()

            // Boş olan edit text'leri bul
            for (editText in editTextList) {
                if (editText.text.isBlank()) {
                    emptyEditTextList.add(editText)
                }
            }

            // Eğer boş edit text varsa, hata mesajını göster
            if (emptyEditTextList.isNotEmpty()) {
                val errorMessage = "Please Fill All the Blanks"
                for (emptyEditText in emptyEditTextList) {
                    emptyEditText.error = errorMessage
                }
            } else {
                // Boş edit text yoksa, istediğiniz işlemi gerçekleştir
                val dateTextEnd = binding.etEndDate.text.toString()
                val dateTextStart = binding.etStartDate.text.toString()

                val startDate = convertToDate(dateTextStart)
                val endDate = convertToDate(dateTextEnd)

                clubName = viewModel.clubData?.clubName
                Log.e("createForm",clubName.toString())
                viewModel.sendFormRequst(
                    binding.etTitle.text.toString(),
                    binding.etContent.text.toString(),
                    binding.etEventGoals.text.toString(),
                    binding.etAgenda.text.toString(),
                    selectedStartDate!!,
                    selectedEndDate!!,
                    "",
                    "",
                    "",
                    "",
                    "",
                    startDate!!,
                    endDate!!,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    true,
                    false,
                    "1",
                    clubName?.lowercase().toString(),
                    requireContext()
                )
                navController.navigate(R.id.action_clubManagerCreateFormPage_to_clubManagerFormsMainPage)
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
                        sdf.timeZone = TimeZone.getTimeZone("Europe/Istanbul")

                        val formattedDateTime = sdf.format(selectedStartDate!!)
                        binding.etStartDate.setText(formattedDateTime)
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

                        val formattedDateTime = sdf.format(selectedEndDate!!)
                        binding.etEndDate.setText(formattedDateTime)
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

    private fun convertToDate(dateString: String): Date? {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("Europe/Istanbul")

        return try {
            dateFormat.parse(dateString)
        } catch (e: Exception) {
            null
        }
    }
}