package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.homePageFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.core.entitiy.ClubManager
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminHomePageBinding

class SksAdminHomePageFragment : Fragment() {
    private lateinit var binding: FragmentSksAdminHomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSksAdminHomePageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getPendingCount()
        getApprovedCount()
        getClubManagerCount()
        getClubCount()
    }

    fun getClubCount() {
        val clubList = mutableListOf<Club>()

        FirebaseFirestore.getInstance().collection("club")
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {

                    for (document in querySnapshot) {
                        // Burada her bir belgeyi işleyebilirsiniz
                        document.toObject(Club::class.java).let { clubList.add(it) }
                    }
                    binding.clubsNumber.text = clubList.size.toString()
                }else{
                    Log.e("hata",clubList.toString())
                }
            }

    }
    fun getClubManagerCount() {
        val clubManagerList = mutableListOf<ClubManager>()

        FirebaseFirestore.getInstance().collection("clubManager")
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {

                    for (document in querySnapshot) {
                        // Burada her bir belgeyi işleyebilirsiniz
                        document.toObject(ClubManager::class.java).let { clubManagerList.add(it) }
                    }
                    binding.clubManagerNumber.text = clubManagerList.size.toString()
                }
            }

    }
    fun getPendingCount(){
        val requestList = mutableListOf<Request>()

        FirebaseFirestore.getInstance().collection("request")
            .whereEqualTo("status", "1")
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {

                    for (document in querySnapshot) {
                        // Burada her bir belgeyi işleyebilirsiniz
                        document.toObject(Request::class.java).let { requestList.add(it) }
                    }
                    binding.pendingNumber.text = requestList.size.toString()
                }
            }


    }
    fun getApprovedCount() {
        val requestList = mutableListOf<Request>()

        FirebaseFirestore.getInstance().collection("request")
            .whereEqualTo("status", "2")
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {

                    for (document in querySnapshot) {
                        // Burada her bir belgeyi işleyebilirsiniz
                        document.toObject(Request::class.java).let { requestList.add(it) }
                    }
                    binding.approvedNumber.text = requestList.size.toString()

                }
            }

    }

}

