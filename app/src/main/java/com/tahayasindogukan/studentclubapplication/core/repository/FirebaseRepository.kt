package com.tahayasindogukan.studentclubapplication.core.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    private lateinit var databaseReference: DatabaseReference

    fun signUpRepository(
        email: String,
        password: String,
        phone: String,
        name: String,
        clubId: String,
        onComplete: (Boolean, String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Kayıt başarılı
                    val user = task.result?.user
                    val uid = user?.uid
                    // Firestore'a veri ekleme
                    val userRef = firestore.collection("clubManager").document(uid!!)
                    val userMap = mutableMapOf<String, Any>()

                    userMap["name"] = name
                    userMap["clubId"] = clubId
                    userMap["email"] = email
                    userMap["password"] = password
                    userMap["phone"] = phone
                    userMap["clubManagerId"] = uid

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
        //val request = Request(name, surname, 1)
        //firestore.collection("request").document().set(request)

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
    fun signOutRepository(onComplete: (Boolean) -> Unit) {
        auth.signOut()
        onComplete(true)
    }
    fun getCurrentUserRepository(): FirebaseUser? {
        return auth.currentUser
    }
    /*fun signUpRepository(
        userId : String,
        email: String,
        password: String,
        name: String,
        surname: String,
        correctQuestionCount: Int,
        wrongQuestionCount: Int,
        profilePicture: String,
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
                    userMap["userId"] = userId
                    userMap["name"] = name
                    userMap["surname"] = surname
                    userMap["email"] = email
                    userMap["score"] = 0
                    userMap["correctQuestionCount"] = correctQuestionCount
                    userMap["wrongQuestionCount"] = wrongQuestionCount
                    userMap["profilePicture"] = profilePicture

                    databaseReference = FirebaseDatabase.getInstance().getReference("UserProfilePicture")
                    val userId = uid
                    val uri = ""
                    val userProfilePicture = UserProfilePicture (userId , uri )
                    databaseReference.child(userId).setValue(userProfilePicture)


                    onComplete(true, user?.uid)

                    userRef.set(userMap)
                        .addOnSuccessListener {
                            // Veri ekleme başarılı
                            Log.e("Auth Repo","Kayıt Başarılı")
                        }.addOnFailureListener{
                            // Veri ekleme başarısız
                            Log.e("Auth Repo","Kayıt Başarısız")
                        }
                } else {
                    onComplete(false, task.exception?.message)
                }
            }
    }*/
}