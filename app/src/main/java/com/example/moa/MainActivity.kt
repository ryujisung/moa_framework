package com.example.moa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.log_btn).setOnClickListener {
            val loginintent = Intent(this,LoginActivity::class.java)

            startActivity(loginintent)
        }

        findViewById<TextView>(R.id.tv_signup).setOnClickListener {
            val signupintent = Intent(this,SignupActivity::class.java)

            startActivity(signupintent)
        }

    }
}