package com.tahayasindogukan.studentclubapplication.core.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request

class FirebaseViewModel : ViewModel() {


    private val auth = FirebaseRepository()
    private val firebaseUser = currentUserViewModel()

    private val _requests = MutableLiveData<List<DocumentSnapshot>>()

    fun signInViewModel(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        auth.signInRepository(email, password) { success, message ->
            onComplete(success, message)
        }
    }

    fun sendRequestViewModel(
        name: String,
        surname: String,
        visibility: Int,
        onComplete: (Boolean, String?) -> Unit
    ) {
        auth.sendRequest(name, surname, visibility) { success, message ->
            if(success) {
                onComplete(success, message)
            }
        }
    }
    fun getAllRequests(): LiveData<List<DocumentSnapshot>> {
        getAllRequstsFromFirebase()
        return _requests
    }
    fun signUpViewModel(
        email: String,
        password: String,
        name: String,
        visibility: Int,
        onComplete: (Boolean, String?) -> Unit
    ) {
        auth.signUpRepository(
            email,
            password,
            name,
            visibility,
        ) { success, message ->
            if (success) {
                onComplete(success, message)

            }
            // Kayıt başarılı, ek işlemleri yapabilirsiniz.
            val user = auth.getCurrentUserRepository()
            // User nesnesini kullanabilir veya UI'yi güncelleyebilirsiniz.

            /*else {
           Kayıt başarısız, kullanıcıya hata mesajı gösterilebilir.
        }    */
        }
    }

    fun getAllRequstsFromFirebase(){
        val firestoreRef = FirebaseFirestore.getInstance()
        val requestRef = firestoreRef.collection("request")

        val query = requestRef.whereEqualTo("visibility", 0).orderBy("visibility",Query.Direction.DESCENDING).limit(10)

        query.addSnapshotListener { querySnapshot, error ->
            if (error != null) {
                // Hata işleme
            } else {
                val documents = mutableListOf<DocumentSnapshot>()
                for (document in querySnapshot?.documents!!) {
                    documents.add(document)
                }
                _requests.value = documents
            }
        }
    }

    fun currentUserViewModel(): FirebaseUser? {
        return auth.getCurrentUserRepository()
    }
}