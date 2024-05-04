package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.core.entitiy.ClubManager
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.core.repository.RequestViewModel
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerHomePageBinding
import com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments.FirebaseViewModel

class ClubManagerHomePageFragment : Fragment() {
    private lateinit var binding: FragmentClubManagerHomePageBinding
    private lateinit var navController: NavController
    private val viewModel: RequestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerHomePageBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.checkManagerOfWhichClub()
        var clubName = viewModel.clubData?.clubName?.lowercase()
        clubName?.let { getApprovedFormCount(it) }
        clubName?.let { getPendingFormCount(it) }
        clubName?.let { getRejectedFormCount(it) }
        clubName?.let { getApprovedPostCount(it) }
        clubName?.let { getPendingPostCount(it) }
        clubName?.let { getRejectedPostCount(it) }

    }

    fun getApprovedFormCount(clubName:String) {
        val clubList = mutableListOf<Request>()

        FirebaseFirestore.getInstance().collection("request")
            .whereEqualTo("clubName",clubName)
            .whereEqualTo("isForm",true)
            .whereEqualTo("status", "2")

            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {

                    for (document in querySnapshot) {
                        // Burada her bir belgeyi işleyebilirsiniz
                        document.toObject(Request::class.java).let { clubList.add(it) }
                    }
                    binding.twApprovedFormNumber.text = clubList.size.toString()
                }else{
                    Log.e("hata",clubList.toString())
                }
            }

    }
    fun getPendingFormCount(clubName:String) {
        val clubManagerList = mutableListOf<Request>()

        FirebaseFirestore.getInstance().collection("request")
            .whereEqualTo("isForm",true)
            .whereEqualTo("status", "1")
            .whereEqualTo("clubName",clubName)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {

                    for (document in querySnapshot) {
                        // Burada her bir belgeyi işleyebilirsiniz
                        document.toObject(Request::class.java).let { clubManagerList.add(it) }
                    }
                    binding.twPendingFormNumber.text = clubManagerList.size.toString()
                }
            }

    }
    fun getRejectedFormCount(clubName:String){
        val requestList = mutableListOf<Request>()

        FirebaseFirestore.getInstance().collection("request")
            .whereEqualTo("isForm",true)
            .whereEqualTo("isPost",false)
            .whereEqualTo("status", "3")
            .whereEqualTo("clubName",clubName)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {

                    for (document in querySnapshot) {
                        // Burada her bir belgeyi işleyebilirsiniz
                        document.toObject(Request::class.java).let { requestList.add(it) }
                    }
                    binding.twRejectedFormNumber.text = requestList.size.toString()
                }
            }


    }
    fun getApprovedPostCount(clubName:String) {
        val clubList = mutableListOf<Request>()

        var ref = FirebaseFirestore.getInstance().collection("request")

            var query = ref
            .whereEqualTo("isPost",true)
            .whereEqualTo("status", "2")
            .whereEqualTo("clubName",clubName.lowercase())

            query.get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {

                    for (document in querySnapshot) {
                        // Burada her bir belgeyi işleyebilirsiniz
                        document.toObject(Request::class.java).let { clubList.add(it) }
                    }
                    Log.e("hata",clubList.toString())

                    binding.twApprovedPostNumber.text = clubList.size.toString()
                }else{
                    Log.e("hata",clubList.toString())
                }
            }

    }
    fun getPendingPostCount(clubName:String) {
        val clubManagerList = mutableListOf<Request>()

        FirebaseFirestore.getInstance().collection("request")
            .whereEqualTo("isForm",false)
            .whereEqualTo("isPost",true)
            .whereEqualTo("status", "1")
            .whereEqualTo("clubName",clubName)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {

                    for (document in querySnapshot) {
                        // Burada her bir belgeyi işleyebilirsiniz
                        document.toObject(Request::class.java).let { clubManagerList.add(it) }
                    }
                    binding.twPendingPostNumber.text = clubManagerList.size.toString()
                }
            }

    }
    fun getRejectedPostCount(clubName:String){
        val requestList = mutableListOf<Request>()

        FirebaseFirestore.getInstance().collection("request")
            .whereEqualTo("isForm",false)
            .whereEqualTo("isPost",true)
            .whereEqualTo("clubName",clubName)
            .whereEqualTo("status", "3")
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {

                    for (document in querySnapshot) {
                        // Burada her bir belgeyi işleyebilirsiniz
                        document.toObject(Request::class.java).let { requestList.add(it) }
                    }
                    binding.twRejectedPostNumber.text = requestList.size.toString()
                }
            }


    }

}