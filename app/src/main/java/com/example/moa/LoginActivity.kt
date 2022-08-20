package com.example.moa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        val emailInput = findViewById<EditText>(R.id.et_pn)
        val passwordInput = findViewById<EditText>(R.id.et_pw)
        findViewById<Button>(R.id.btn_login).setOnClickListener {
            auth.signInWithEmailAndPassword(
                emailInput.text.toString(),
                passwordInput.text.toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val loginintent = Intent(this,searchloadnameActivity::class.java)
                        startActivity(loginintent)
                    } else {
                        Toast.makeText(this, "실패!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        findViewById<ImageView>(R.id.iv_2).setOnClickListener {
            val signupintent = Intent(this,SignupActivity::class.java)




            startActivity(signupintent)
        }
    }
}