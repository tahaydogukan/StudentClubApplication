package com.tahayasindogukan.studentclubapplication.core.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request

class RequestViewModel : ViewModel() {
    val formsPendingList = MutableLiveData<List<Request>>()
    val formsApprovedList = MutableLiveData<List<Request>>()
    val formsRejectedList = MutableLiveData<List<Request>>()
    val postsPendingList = MutableLiveData<List<Request>>()
    val postsApprovedList = MutableLiveData<List<Request>>()
    val postsRejectedList = MutableLiveData<List<Request>>()
    var clubRequests = MutableLiveData<List<Request>>()

    var clubData: Club? = null

    init {
        checkManagerOfWhichClub()

    }

    fun checkManagerOfWhichClub() {
        FirebaseAuth.getInstance().currentUser?.uid?.let { uid ->
            FirebaseFirestore.getInstance().collection("club")
                .whereEqualTo("clubManagerId", uid)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.documents.isNotEmpty()) {
                        // Kullanıcı giriş yapmıştır ve verilere erişebilir.
                        var club1 = querySnapshot.documents[0].toObject(Club::class.java)
                        clubData = club1
                        Log.e("clubNameVMM", club1.toString())

                        // ...
                    } else {
                        // Kullanıcı giriş yapamaz ve verilere erişemez.
                    }
                }
        }

    }

    fun editRequest(
        // Document ID to identify the request to edit (obtained from original creation)
        documentId: String,

        // Existing field values (optional, for reference or population)
        title: String? = null,
        content: String? = null,
        eventGoals: String? = null,
        agenda: String? = null,
        startDate: String? = null,
        endDate: String? = null,
        manager: String? = null,

        // New or updated field values
        newTitle: String? = null,
        newManager: String? = null,
        newContent: String? = null,
        newAttachment: String? = null,
        newStartDate: String? = null,
        newEndDate: String? = null,
        newLocation: String? = null,
        newWebPlatform: String? = null,
        newContacts: String? = null,

        // Post-specific fields (optional)
        attachment: String? = null,
        location: String? = null,
        webPlatform: String? = null,
        contacts: String? = null,
        isForm: Boolean? = true,
        isPost: Boolean? = null,
        status: Int? = null,
        context: Context
    ) {
        val ref = FirebaseFirestore.getInstance().collection("request").document(documentId)

        val updates = hashMapOf<String, Any>()

        // Include existing fields if provided (for reference or population)
        if (title != null) updates["title"] = title
        if (content != null) updates["content"] = content
        if (eventGoals != null) updates["eventGoals"] = eventGoals
        if (agenda != null) updates["agenda"] = agenda
        if (startDate != null) updates["startDate"] = startDate
        if (endDate != null) updates["endDate"] = endDate
        if (manager != null) updates["manager"] = manager

        // Include new or updated fields
        if (newTitle != null) updates["newTitle"] = newTitle
        if (newManager != null) updates["newManager"] = newManager
        if (newContent != null) updates["newContent"] = newContent
        if (newAttachment != null) updates["newAttachment"] = newAttachment
        if (newStartDate != null) updates["newStartDate"] = newStartDate
        if (newEndDate != null) updates["newEndDate"] = newEndDate
        if (newLocation != null) updates["newLocation"] = newLocation
        if (newWebPlatform != null) updates["newWebPlatform"] = newWebPlatform
        if (newContacts != null) updates["newContacts"] = newContacts

        // Include post-specific fields if provided
        if (attachment != null) updates["attachment"] = attachment
        if (location != null) updates["location"] = location
        if (webPlatform != null) updates["webPlatform"] = webPlatform
        if (contacts != null) updates["contacts"] = contacts
        if (isForm != null) updates["isForm"] = isForm
        if (isPost != null) updates["isPost"] = isPost
        if (status != null) updates["status"] = status
        ref.update(updates)
            .addOnSuccessListener {
                Toast.makeText(context, "Düzenleme başarılı", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Düzenleme başarısız", Toast.LENGTH_SHORT).show()
            }
    }

    fun sendFormRequst(
        //form  değişkenleri
        title: String,
        content: String,
        eventGoals: String,
        agenda: String,
        startDate: String,
        endDate: String,
        manager: String,
        //yeni form değişkenleri
        newTitle: String,
        newManager: String,
        newContent: String,
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
        isForm: Boolean,
        isPost: Boolean,
        status: Int,
        clubName: String,
        //status 1 = pending 2=approved 3 =rejected 4=deleted
        context: Context
    ) {
        val ref = FirebaseFirestore.getInstance().collection("request")
        val request = mapOf(
            "documentId" to "",
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
            "status" to status,
            "clubName" to clubName
        )
        ref.add(request)
            .addOnSuccessListener {
                Toast.makeText(context, "Başarılı", Toast.LENGTH_SHORT).show()

                val documentId = it.id

                // Aynı collection'a ID'yi ekleyin
                val updatedRequest = mapOf(
                    "documentId" to documentId,
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
                    "status" to status,
                    "clubName" to clubName
                )
                it.set(updatedRequest)
                    .addOnSuccessListener {
                        Log.d("Id", "ID Başarıyla Güncellendi")
                    }
                    .addOnFailureListener { e ->
                        Log.w("Id", "ID Güncelleme Başarısız", e)
                    }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Başarısız", Toast.LENGTH_SHORT).show()
            }

    }

    fun getFormsApproved() {
        FirebaseAuth.getInstance().currentUser?.uid?.let { uid ->
            FirebaseFirestore.getInstance().collection("club")
                .whereEqualTo("clubManagerId", uid)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.documents.isNotEmpty()) {
                        // Kullanıcı giriş yapmıştır ve verilere erişebilir.
                        var club1 = querySnapshot.documents[0].toObject(Club::class.java)
                        clubData = club1
                        Log.e("clubNameVMM", club1.toString())
                        val ref = FirebaseFirestore.getInstance().collection("request")

                        val query = ref
                            .whereEqualTo("isForm", true)
                            .whereEqualTo("status", 2)
                            .whereEqualTo("clubName", this.clubData?.clubName?.lowercase())
                        Log.e("viewModel", this.clubData.toString())


                        query.get().addOnSuccessListener { task ->
                            val requestList = mutableListOf<Request>()
                            for (document in task.documents) {
                                // Burada her bir belgeyi işleyebilirsiniz
                                document.toObject(Request::class.java)?.let { requestList.add(it) }
                            }
                            formsApprovedList.postValue(requestList)
                            Log.e("requestList", requestList.toString())

                        }
                        // ...
                    } else {
                        // Kullanıcı giriş yapamaz ve verilere erişemez.
                    }
                }
        }

    }

    fun getPostApproved() {
        FirebaseAuth.getInstance().currentUser?.uid?.let { uid ->
            FirebaseFirestore.getInstance().collection("club")
                .whereEqualTo("clubManagerId", uid)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.documents.isNotEmpty()) {
                        // Kullanıcı giriş yapmıştır ve verilere erişebilir.
                        var club1 = querySnapshot.documents[0].toObject(Club::class.java)
                        clubData = club1
                        Log.e("clubNameVMM", club1.toString())
                        val ref = FirebaseFirestore.getInstance().collection("request")

                        val query = ref
                            .whereEqualTo("isPost", true)
                            .whereEqualTo("status", 2)
                            .whereEqualTo("clubName", this.clubData?.clubName?.lowercase())
                        Log.e("viewModel", this.clubData.toString())


                        query.get().addOnSuccessListener { task ->
                            val requestList = mutableListOf<Request>()
                            for (document in task.documents) {
                                // Burada her bir belgeyi işleyebilirsiniz
                                document.toObject(Request::class.java)?.let { requestList.add(it) }
                            }
                            postsApprovedList.postValue(requestList)
                            Log.e("requestList", requestList.toString())

                        }
                        // ...
                    } else {
                        // Kullanıcı giriş yapamaz ve verilere erişemez.
                    }
                }
        }

    }

    fun getFormsPending() {
        FirebaseAuth.getInstance().currentUser?.uid?.let { uid ->
            FirebaseFirestore.getInstance().collection("club")
                .whereEqualTo("clubManagerId", uid)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.documents.isNotEmpty()) {
                        // Kullanıcı giriş yapmıştır ve verilere erişebilir.
                        var club1 = querySnapshot.documents[0].toObject(Club::class.java)
                        clubData = club1
                        Log.e("clubNameVMM", club1.toString())
                        val ref = FirebaseFirestore.getInstance().collection("request")

                        val query = ref
                            .whereEqualTo("isForm", true)
                            .whereEqualTo("status", 1)
                            .whereEqualTo("clubName", this.clubData?.clubName?.lowercase())
                        Log.e("viewModel", this.clubData.toString())


                        query.get().addOnSuccessListener { task ->
                            val requestList = mutableListOf<Request>()
                            for (document in task.documents) {
                                // Burada her bir belgeyi işleyebilirsiniz
                                document.toObject(Request::class.java)?.let { requestList.add(it) }
                            }
                            formsPendingList.postValue(requestList)
                            Log.e("requestList", requestList.toString())

                        }
                        // ...
                    } else {
                        // Kullanıcı giriş yapamaz ve verilere erişemez.
                    }
                }
        }

    }

    fun getPostPending() {
        FirebaseAuth.getInstance().currentUser?.uid?.let { uid ->
            FirebaseFirestore.getInstance().collection("club")
                .whereEqualTo("clubManagerId", uid)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.documents.isNotEmpty()) {
                        // Kullanıcı giriş yapmıştır ve verilere erişebilir.
                        var club1 = querySnapshot.documents[0].toObject(Club::class.java)
                        clubData = club1
                        Log.e("clubNameVMM", club1.toString())
                        val ref = FirebaseFirestore.getInstance().collection("request")

                        val query = ref
                            .whereEqualTo("isPost", true)
                            .whereEqualTo("status", 1)
                            .whereEqualTo("clubName", this.clubData?.clubName?.lowercase())
                        Log.e("viewModel", this.clubData.toString())


                        query.get().addOnSuccessListener { task ->
                            val requestList = mutableListOf<Request>()
                            for (document in task.documents) {
                                // Burada her bir belgeyi işleyebilirsiniz
                                document.toObject(Request::class.java)?.let { requestList.add(it) }
                            }
                            postsPendingList.postValue(requestList)
                            Log.e("requestList", requestList.toString())

                        }
                        // ...
                    } else {
                        // Kullanıcı giriş yapamaz ve verilere erişemez.
                    }
                }
        }

    }

    fun getFormsRejected() {
        FirebaseAuth.getInstance().currentUser?.uid?.let { uid ->
            FirebaseFirestore.getInstance().collection("club")
                .whereEqualTo("clubManagerId", uid)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.documents.isNotEmpty()) {
                        // Kullanıcı giriş yapmıştır ve verilere erişebilir.
                        var club1 = querySnapshot.documents[0].toObject(Club::class.java)
                        clubData = club1
                        Log.e("clubNameVMM", club1.toString())
                        val ref = FirebaseFirestore.getInstance().collection("request")

                        val query = ref
                            .whereEqualTo("isForm", true)
                            .whereEqualTo("status", 3)
                            .whereEqualTo("clubName", this.clubData?.clubName?.lowercase())
                        Log.e("viewModel", this.clubData.toString())


                        query.get().addOnSuccessListener { task ->
                            val requestList = mutableListOf<Request>()
                            for (document in task.documents) {
                                // Burada her bir belgeyi işleyebilirsiniz
                                document.toObject(Request::class.java)?.let { requestList.add(it) }
                            }
                            formsRejectedList.postValue(requestList)
                            Log.e("requestList", requestList.toString())

                        }
                        // ...
                    } else {
                        // Kullanıcı giriş yapamaz ve verilere erişemez.
                    }
                }
        }

    }

    fun getPostRejected() {
        FirebaseAuth.getInstance().currentUser?.uid?.let { uid ->
            FirebaseFirestore.getInstance().collection("club")
                .whereEqualTo("clubManagerId", uid)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.documents.isNotEmpty()) {
                        // Kullanıcı giriş yapmıştır ve verilere erişebilir.
                        var club1 = querySnapshot.documents[0].toObject(Club::class.java)
                        clubData = club1
                        Log.e("clubNameVMM", club1.toString())
                        val ref = FirebaseFirestore.getInstance().collection("request")

                        val query = ref
                            .whereEqualTo("isPost", true)
                            .whereEqualTo("status", 3)
                            .whereEqualTo("clubName", this.clubData?.clubName?.lowercase())
                        Log.e("viewModel", this.clubData.toString())


                        query.get().addOnSuccessListener { task ->
                            val requestList = mutableListOf<Request>()
                            for (document in task.documents) {
                                // Burada her bir belgeyi işleyebilirsiniz
                                document.toObject(Request::class.java)?.let { requestList.add(it) }
                            }
                            postsRejectedList.postValue(requestList)
                            Log.e("requestList", requestList.toString())

                        }
                        // ...
                    } else {
                        // Kullanıcı giriş yapamaz ve verilere erişemez.
                    }
                }
        }

    }



    fun getClubRequests(clubName: String) {
        FirebaseFirestore.getInstance().collection("request")
            .whereEqualTo("clubName", clubName.lowercase())
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {

                    val requestList = mutableListOf<Request>()
                    for (document in querySnapshot) {
                        // Burada her bir belgeyi işleyebilirsiniz
                        document.toObject(Request::class.java).let { requestList.add(it) }
                    }
                    clubRequests.postValue(requestList)

                } else {
                    Log.e("getClubRequests", "Veri alınamadı")
                }
            }


    }

    fun getSksPostRejected() {
        val ref = FirebaseFirestore.getInstance().collection("request")

        val query = ref
            .whereEqualTo("isPost", true)
            .whereEqualTo("status", 3)

        query.get().addOnSuccessListener { task ->
            val requestList = mutableListOf<Request>()
            for (document in task.documents) {
                // Burada her bir belgeyi işleyebilirsiniz
                document.toObject(Request::class.java)?.let { requestList.add(it) }
            }
            postsRejectedList.postValue(requestList)
            Log.e("requestList", requestList.toString())

        }


    }
    fun getSksPostApproved() {
        val ref = FirebaseFirestore.getInstance().collection("request")
        //status 1 = pending 2=approved 3 =rejected 4=deleted
        val query = ref
            .whereEqualTo("isPost", true)
            .whereEqualTo("status", 2)

        query.get().addOnSuccessListener { task ->
            val requestList = mutableListOf<Request>()
            for (document in task.documents) {
                // Burada her bir belgeyi işleyebilirsiniz
                document.toObject(Request::class.java)?.let { requestList.add(it) }
            }
            postsRejectedList.postValue(requestList)
            Log.e("requestList", requestList.toString())

        }


    }
    fun getSksPostPending() {
        val ref = FirebaseFirestore.getInstance().collection("request")
        //status 1 = pending 2=approved 3 =rejected 4=deleted
        val query = ref
            .whereEqualTo("isPost", true)
            .whereEqualTo("status", 1)

        query.get().addOnSuccessListener { task ->
            val requestList = mutableListOf<Request>()
            for (document in task.documents) {
                // Burada her bir belgeyi işleyebilirsiniz
                document.toObject(Request::class.java)?.let { requestList.add(it) }
            }
            postsRejectedList.postValue(requestList)
            Log.e("requestList", requestList.toString())

        }


    }
    fun getSksFormsRejected() {
        val ref = FirebaseFirestore.getInstance().collection("request")
        //status 1 = pending 2=approved 3 =rejected 4=deleted
        val query = ref
            .whereEqualTo("isForm", true)
            .whereEqualTo("status", 3)

        query.get().addOnSuccessListener { task ->
            val requestList = mutableListOf<Request>()
            for (document in task.documents) {
                // Burada her bir belgeyi işleyebilirsiniz
                document.toObject(Request::class.java)?.let { requestList.add(it) }
            }
            postsRejectedList.postValue(requestList)
            Log.e("requestList", requestList.toString())

        }


    }
    fun getSksFormsApproved() {
        val ref = FirebaseFirestore.getInstance().collection("request")
        //status 1 = pending 2=approved 3 =rejected 4=deleted
        val query = ref
            .whereEqualTo("isForm", true)
            .whereEqualTo("status", 2)

        query.get().addOnSuccessListener { task ->
            val requestList = mutableListOf<Request>()
            for (document in task.documents) {
                // Burada her bir belgeyi işleyebilirsiniz
                document.toObject(Request::class.java)?.let { requestList.add(it) }
            }
            postsRejectedList.postValue(requestList)
            Log.e("requestList", requestList.toString())

        }


    }
    fun getSksFormsPending() {
        val ref = FirebaseFirestore.getInstance().collection("request")
        //status 1 = pending 2=approved 3 =rejected 4=deleted
        val query = ref
            .whereEqualTo("isForm", true)
            .whereEqualTo("status", 1)

        query.get().addOnSuccessListener { task ->
            val requestList = mutableListOf<Request>()
            for (document in task.documents) {
                // Burada her bir belgeyi işleyebilirsiniz
                document.toObject(Request::class.java)?.let { requestList.add(it) }
            }
            postsRejectedList.postValue(requestList)
            Log.e("requestList", requestList.toString())

        }


    }

}