package com.tahayasindogukan.studentclubapplication.core.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request

class FirebaseRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()


    fun signUpRepository(
        email: String,
        password: String,
        name: String,
        visibility: Int,
        onComplete: (Boolean, String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Kayıt başarılı
                    val user = task.result?.user
                    val uid = user?.uid
                    // Firestore'a veri ekleme
                    val db = FirebaseFirestore.getInstance()
                    val userRef = db.collection("users").document(uid!!)
                    val userMap = mutableMapOf<String, Any>()

                    userMap["name"] = name
                    userMap["email"] = email
                    userMap["visibility"] = visibility

                    onComplete(true, user.uid)

                    userRef.set(userMap)
                        .addOnSuccessListener {
                            // Veri ekleme başarılı
                            Log.e("Auth Repo", "Kayıt Başarılı")
                        }.addOnFailureListener {
                            // Veri ekleme başarısız
                            Log.e("Auth Repo", "Kayıt Başarısız")
                        }
                } else {
                    onComplete(false, task.exception?.message)
                }
            }
    }

    fun sendRequest(
        name: String,
        surname: String,
        visibility: Int,
        onComplete: (Boolean, String?) -> Unit
    ) {
        val request = Request(name, surname, visibility)
        firestore.collection("request").document().set(request)

    }

    fun signInRepository(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, null)

                } else {
                    onComplete(false, task.exception?.message)
                }
            }


    }

    fun getCurrentUserRepository(): FirebaseUser? {
        return auth.currentUser
    }
}