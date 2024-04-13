package com.tahayasindogukan.studentclubapplication.core.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request

class RequestViewModel : ViewModel() {
    val formsPendingList = MutableLiveData<List<Request>>()
    val formsApprovedList = MutableLiveData<List<Request>>()
    val formsRejectedList = MutableLiveData<List<Request>>()


    fun sendFormRequst(
        //form  değişkenleri
        title: String,
        content: String,
        eventGoals: String,
        agenda: String,
        startDate: String,
        endDate: String,
        manager:String,
        //yeni form değişkenleri
        newTitle: String,
        newManager:String,
        newContent:String,
        newAttachment: String,
        newStartDate: String,
        newEndDate: String,
        newLocation: String,
        newWebPlatform: String,
        newContacts: String,
        //posta özel değişkenler
        attachment: String,
        location: String,
        webPlatform: String,
        contacts: String,
        isForm:Boolean,
        isPost:Boolean,
        status:Int,
        //status 1 = pending 2=approved 3 =rejected
        context: Context
    ) {
        val ref = FirebaseFirestore.getInstance().collection("request")
        val request = mapOf(
            "title" to title,
            "content" to content,
            "eventGoals" to eventGoals,
            "agenda" to agenda,
            "startDate" to startDate,
            "endDate" to endDate,
            "manager" to manager,

            "newTitle" to newTitle,
            "newManager" to newManager,
            "newContent" to newContent,
            "newAttachment" to newAttachment,
            "newStartDate" to newStartDate,
            "newEndDate" to newEndDate,
            "newLocation" to newLocation,
            "newWebPlatform" to newWebPlatform,
            "newContacts" to newContacts,


            "attachment" to attachment,
            "location" to location,
            "webPlatform" to webPlatform,
            "contacts" to contacts,
            "isForm" to isForm,
            "isPost" to isPost,
            "status" to status
        )
        ref.add(request)
            .addOnSuccessListener {
                Toast.makeText(context, "Başarılı", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Başarısız", Toast.LENGTH_SHORT).show()
            }

    }

    fun getFormsPending(){
        val ref = FirebaseFirestore.getInstance().collection("request")
        val query = ref
            .whereEqualTo("isForm", true)
            .whereEqualTo("status", 1)

        query.get().addOnSuccessListener { task ->
            val requestList = mutableListOf<Request>()
            for (document in task.documents) {
                // Burada her bir belgeyi işleyebilirsiniz
                document.toObject(Request::class.java)?.let { requestList.add(it) }
            }
            formsPendingList.postValue(requestList)
        }
    }
    fun getFormsApproved(){
        val ref = FirebaseFirestore.getInstance().collection("request")
        val query = ref
            .whereEqualTo("isForm", true)
            .whereEqualTo("status", 2)

        query.get().addOnSuccessListener { task ->
            val requestList = mutableListOf<Request>()
            for (document in task.documents) {
                // Burada her bir belgeyi işleyebilirsiniz
                document.toObject(Request::class.java)?.let { requestList.add(it) }
            }
            formsApprovedList.postValue(requestList)
        }
    }
    fun getFormsRejected(){
        val ref = FirebaseFirestore.getInstance().collection("request")
        val query = ref
            .whereEqualTo("isForm", true)
            .whereEqualTo("status", 3)

        query.get().addOnSuccessListener { task ->
            val requestList = mutableListOf<Request>()
            for (document in task.documents) {
                // Burada her bir belgeyi işleyebilirsiniz
                document.toObject(Request::class.java)?.let { requestList.add(it) }
            }
            formsRejectedList.postValue(requestList)
        }
    }
    fun sendPostRequst(
        title: String,
        manager: String,
        content: String,
        attachment: String,
        startDate: String,
        endDate: String,
        location: String,
        webPlatform: String,
        contacts: String,
        context: Context
    ) {
        val ref = FirebaseFirestore.getInstance().collection("request")
        val request = mapOf(
            "title" to title,
            "manager" to manager,
            "content" to content,
            "attachment" to attachment,
            "startDate" to startDate,
            "endDate" to endDate,
            "location" to location,
            "webPlatform" to webPlatform,
            "contacts" to contacts,
            "status" to "pending",
            "newTitle" to "",
            "newManager" to "",
            "newDescription" to "",
            "newAttachment" to "",
            "newStartDate" to "",
            "newEndDate" to "",
            "newLocation" to "",
            "newWebPlatform" to "",
            "newContacts" to "",

            )
        ref.add(request)
            .addOnSuccessListener {
                Toast.makeText(context, "Başarılı", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Başarısız", Toast.LENGTH_SHORT).show()
            }

    }


}