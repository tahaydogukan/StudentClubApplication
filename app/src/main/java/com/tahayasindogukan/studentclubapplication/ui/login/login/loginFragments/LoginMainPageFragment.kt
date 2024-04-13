package com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentLoginMainPageBinding
import com.tahayasindogukan.studentclubapplication.ui.home.student.StudentHomePageActivity

class LoginMainPageFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentLoginMainPageBinding
    var selectedButtonTag: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View modelleri burda tanımla
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginMainPageBinding.inflate(inflater, container, false)
        // Bindingi burada tanımla her zaman yoksa çalışmıyor.
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val student = binding.loginMainFragmentTwStudent
        val sksAdmin = binding.loginMainFragmentTwSksAdmin
        val clubManager = binding.loginMainFragmentTwClubManager
        val userDescriptipn = binding.loginMainFragmentTwUserDescription



        student.setOnClickListener {
            changeBackgroundResource(student)
            resetBackgroundResource(sksAdmin)
            resetBackgroundResource(clubManager)
            userDescriptipn.setText(R.string.login_main_page_student_description)
            checkButtonTag(student)
        }
        sksAdmin.setOnClickListener {
            changeBackgroundResource(sksAdmin)
            resetBackgroundResource(student)
            resetBackgroundResource(clubManager)
            userDescriptipn.setText(R.string.login_main_page_sks_admin_description)
            checkButtonTag(sksAdmin)
        }
        clubManager.setOnClickListener {
            changeBackgroundResource(clubManager)
            resetBackgroundResource(student)
            resetBackgroundResource(sksAdmin)
            userDescriptipn.setText(R.string.login_main_page_club_manager_description)
            checkButtonTag(clubManager)
        }

        binding.loginMainFragmentBtnTransition.setOnClickListener {
            checkButtonClicked()
        }


    }

    fun checkButtonClicked() {
        when (selectedButtonTag?.toInt()) {
            1 -> {
                startActivity(Intent(requireContext(), StudentHomePageActivity::class.java))
            }

            2 -> {
                navController.navigate(R.id.action_loginMainPageFragment_to_clubManagerFragment)
            }

            3 -> {
                navController.navigate(R.id.action_loginMainPageFragment_to_sksLoginFragment)
            }
        }
    }

    fun changeBackgroundResource(button: Button) {
        button.setBackgroundResource(R.drawable.gradient_background) // Seçilen butonun rengini kırmızıya değiştiriyoruz.
    }

    fun resetBackgroundResource(button: TextView) {
        button.setBackgroundResource(R.drawable.normal_background) // Seçilmeyen butonların rengini beyaza sıfırlıyoruz.
    }

    fun checkButtonTag(view: View) {
        selectedButtonTag = view.tag.toString()
    }

}