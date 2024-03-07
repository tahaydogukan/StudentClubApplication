package com.tahayasindogukan.studentclubapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.tahayasindogukan.studentclubapplication.core.repository.FirebaseViewModel
import com.tahayasindogukan.studentclubapplication.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    val db = FirebaseFirestore.getInstance()
    private val viewModel: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gecisYap.setOnClickListener {
            startActivity(Intent(this, UcuncuActivity::class.java))
        }

        binding.secondActivityBtnSendActivity.setOnClickListener {

            val name =binding.secondActivityEtActivityName.text.toString()
            val surname = binding.secondActivityEtActivitySurname.text.toString()
            viewModel.sendRequestViewModel(name,surname,0){
                success,message->
                Toast.makeText(this,"Başarıyla tamamlandı",Toast.LENGTH_SHORT).show()
            }
        }



    }

}