package com.tahayasindogukan.studentclubapplication.ui.login.login.loginFragments

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.tahayasindogukan.studentclubapplication.core.entitiy.Activity
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.core.entitiy.ClubManager
import com.tahayasindogukan.studentclubapplication.core.repository.FirebaseRepository

class FirebaseViewModel : ViewModel() {

    val clubs = MutableLiveData<List<Club>>()
    val clubManagers = MutableLiveData<List<ClubManager>>()

    val activies = MutableLiveData<List<Activity>>()
    var club = MutableLiveData<Club>()

    private val firebaseAuth = Firebase.auth
    private val firebaseRepository = FirebaseRepository()
    private val firebaseFirestoreInstance = FirebaseFirestore.getInstance()

    private val _requests = MutableLiveData<List<DocumentSnapshot>>()


    fun assignManager(clubId: String,clubManagerId:String,context: Context){

        val firebaseRef = FirebaseFirestore.getInstance()
        val firebaseClubRef = firebaseRef.collection("club").document(clubId)

        val clubManagerIdHashMap = hashMapOf<String, Any>()


        if (clubManagerId != null) clubManagerIdHashMap["clubManagerId"] = clubManagerId

        firebaseClubRef.update(clubManagerIdHashMap)
            .addOnSuccessListener {
                Toast.makeText(context, "Succesfully Assigned", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Assigned is Failed", Toast.LENGTH_SHORT).show()
            }

    }

    fun searchClubByCategory(clubCategory: String) {

        val ref =FirebaseFirestore.getInstance()
        val collectionRef = ref.collection("club")
            .whereEqualTo("clubCategory", clubCategory)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {

                    val club = mutableListOf<Club>()
                    for (document in querySnapshot) {
                        // Burada her bir belgeyi işleyebilirsiniz
                        document.toObject(Club::class.java).let { club.add(it) }
                    }
                    clubs.postValue(club)

                } else {
                    Log.e("getMyClubActivities", "Veri alınamadı")
                }
            }
    }
    fun updatePassword(newPassword: String, completion: (isSuccessful: Boolean) -> Unit) {
        val currentUser = firebaseRepository.getCurrentUserRepository()

        if (currentUser != null) {
            currentUser.updatePassword(newPassword)
                .addOnCompleteListener { task ->
                    completion(task.isSuccessful)
                }
        } else {
            completion(false) // Kullanıcı oturum açmamışsa hata
        }
    }
    fun createClub(
        // Club status 1 normal 2 değişiklik yapılmış 3 club silinmiş
        // Document ID to identify the request to edit (obtained from original creation)
        clubId: String,
        clubManagerId: String? = null,
        clubName:String,
        newClubName: String? = null,
        clubDescription: String? = null,
        newClubDescription: String? = null,
        clubPhoto:String,
        newClubPhoto : String? = null,
        clubCategory: String,
        clubStatus: String? = null,
        context:Context
    ) {
        val ref = FirebaseFirestore.getInstance().collection("club")

        val club = mapOf(
        "clubId" to  clubId,
        "clubManagerId" to clubManagerId,
        "clubName" to  clubName,
        "newClubName" to newClubName,
        "clubDescription" to clubDescription,
        "newClubDescription" to newClubDescription,
        "clubPhoto" to  clubPhoto,
        "newClubPhoto" to newClubPhoto,
        "clubCategory" to clubCategory,
        "clubStatus" to clubStatus
        )

        ref.add(club)
            .addOnSuccessListener {
                Toast.makeText(context, "Başarılı", Toast.LENGTH_SHORT).show()

                val documentId = it.id

                // Aynı collection'a ID'yi ekleyin
                val updatedRequest = mapOf(
                    "clubId" to documentId,

                )
                it.update(updatedRequest)
                    .addOnSuccessListener {
                        Log.d("Id", "ID Başarıyla Güncellendi")
                    }
                    .addOnFailureListener { e ->
                        Log.w("Id", "ID Güncelleme Başarısız", e)
                    }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Düzenleme başarısız", Toast.LENGTH_SHORT).show()
            }
    }
    fun editClub(
        // Club status 1 normal 2 değişiklik yapılmış 3 club silinmiş
        // Document ID to identify the request to edit (obtained from original creation)
        clubId: String,
        newClubStatus:String,
        newClubName: String? = null,
        newClubDescription: String? = null,
        newClubPhoto: String? = null,
        context:Context
    ) {
        val ref = FirebaseFirestore.getInstance().collection("club").document(clubId)

        val updates = hashMapOf<String, Any>()

        // Include existing fields if provided (for reference or population)
        if (clubId != null) updates["clubId"] = clubId
        if (newClubStatus != null) updates["clubStatus"] = newClubStatus
        if (newClubName != null) updates["newClubName"] = newClubName
        if (newClubDescription != null) updates["newClubDescription"] = newClubDescription
        if (newClubPhoto != null) updates["newClubPhoto"] = newClubPhoto

        ref.update(updates)
            .addOnSuccessListener {
                Toast.makeText(context, "Düzenleme başarılı", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Düzenleme başarısız", Toast.LENGTH_SHORT).show()
            }
    }
    fun getClubManagers() {
        val clubCollection = FirebaseFirestore.getInstance().collection("clubManager")
        clubCollection.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val clubManagersList = mutableListOf<ClubManager>()
                for (document in task.result!!) {
                    clubManagersList.add(document.toObject(ClubManager::class.java))
                }
                clubManagers.postValue(clubManagersList)
                Log.e("getClubManagers", clubManagersList.toString())

            } else {
                Log.e("ClubViewModel", "Error getting clubManagers: ", task.exception)
            }
        }
    }
    fun getClubs() {
        val clubCollection = firebaseFirestoreInstance.collection("club")
        clubCollection.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val clubsList = mutableListOf<Club>()
                for (document in task.result!!) {
                    clubsList.add(document.toObject(Club::class.java))
                }
                clubs.postValue(clubsList)
            } else {
                Log.e("ClubViewModel", "Error getting clubs: ", task.exception)
            }
        }
    }
    fun checkManagerOfWhichClub(){
        FirebaseAuth.getInstance().currentUser?.uid?.let { uid ->
            FirebaseFirestore.getInstance().collection("club")
                .whereEqualTo("clubManagerId", uid)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.documents.isNotEmpty()) {
                        // Kullanıcı giriş yapmıştır ve verilere erişebilir.
                        var club1 = querySnapshot.documents[0].toObject(Club::class.java)
                        club.postValue(club1!!)
                        Log.e("clubNameVM", club1.toString())

                        // ...
                    } else {
                        // Kullanıcı giriş yapamaz ve verilere erişemez.
                    }
                }
        }
    }

    fun signInViewModel(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        firebaseRepository.signInRepository(email, password) { success, message ->
            onComplete(success, message)
        }
    }


    fun signUpViewModel(
        email: String,
        password: String,
        phone: String,
        name: String,
        clubId: String,
        onComplete: (Boolean, String?) -> Unit
    ) {
        firebaseRepository.signUpRepository(
            email,
            password,
            phone,
            name,
            clubId
        ) { success, message ->
            if (success) {
                onComplete(success, message)

            }
            // Kayıt başarılı, ek işlemleri yapabilirsiniz.
            val user = firebaseRepository.getCurrentUserRepository()
            // User nesnesini kullanabilir veya UI'yi güncelleyebilirsiniz.

            /*else {
           Kayıt başarısız, kullanıcıya hata mesajı gösterilebilir.
        }    */
        }
    }

    fun sendPasswordResetEmail(email: String, mContext: Context) {
        if (email.isNotEmpty()) {
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(mContext, "Email is sent successfuly", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(
                        mContext, task.exception!!.message.toString(), Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }



    fun currentUserViewModel(): FirebaseUser? {
        return firebaseRepository.getCurrentUserRepository()
    }

    fun signOutViewModel(onComplete: (Boolean) -> Unit) {
        firebaseRepository.signOutRepository(onComplete)
    }
}