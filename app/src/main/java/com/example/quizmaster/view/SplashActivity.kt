package com.example.quizmaster.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.quizmaster.R
import com.example.quizmaster.util.Constants

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIMEOUT: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val intent = Intent(
                this@SplashActivity,
                if (Constants.getLoggedIn(this@SplashActivity)) MainActivity::class.java else LoginActivity::class.java
            )
            startActivity(intent)
            finish()
        }, SPLASH_TIMEOUT)
    }
}