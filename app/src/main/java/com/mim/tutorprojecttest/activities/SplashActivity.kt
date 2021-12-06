package com.mim.tutorprojecttest.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.mim.tutorprojecttest.MainActivity
import com.mim.tutorprojecttest.R

class SplashActivity : AppCompatActivity() {
    var SPLASH_TIME_OUT: Long = 10L;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        waitforSeconds()
    }

    private fun waitforSeconds() {
        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            // close this activity
            finish()
        }, 3000)
    }


}