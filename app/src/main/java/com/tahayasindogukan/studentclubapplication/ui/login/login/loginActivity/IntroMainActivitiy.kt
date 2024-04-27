package com.tahayasindogukan.studentclubapplication.ui.login.login.loginActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tahayasindogukan.studentclubapplication.databinding.ActivityIntroMainActivitiyBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroMainActivitiy : AppCompatActivity() {
    private lateinit var binding: ActivityIntroMainActivitiyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroMainActivitiyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val scope = CoroutineScope(Dispatchers.Main)

        scope.launch {
            delay(2000)
            startActivity(Intent(this@IntroMainActivitiy, LoginMainActivity::class.java))
        }

    }
}