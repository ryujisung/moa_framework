package com.example.moa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth

class IntroActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        auth = FirebaseAuth.getInstance()

        var handler = Handler()
        handler.postDelayed({
            if(auth.currentUser != null){
                var intent = Intent(this,realMainActivity::class.java)
                startActivity(intent)
            }
            else {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        },3000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}