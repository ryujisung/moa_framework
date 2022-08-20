package com.example.moa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignupActivity : AppCompatActivity() {
    var auth : FirebaseAuth?=null //lateinit로 수정
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()
        findViewById<Button>(R.id.btn_signup).setOnClickListener {
            sigininAndSignup()
        }

        findViewById<TextView>(R.id.tv_login).setOnClickListener {
            sigininAndSignup()
            val loginintent = Intent(this,MainActivity2::class.java)
            startActivity(loginintent)
        }
    }
    fun sigininAndSignup(){
        val ao: EditText = findViewById(R.id.pn)
        val aw: EditText = findViewById(R.id.pw)
        val awc: EditText = findViewById(R.id.pwcheck)
        if (aw.text.toString() == awc.text.toString()){
            auth?.createUserWithEmailAndPassword(ao.text.toString(),aw.text.toString())
                ?.addOnCompleteListener {
                        task ->
                    if (task.isSuccessful){
                        MovemainPage(task.result?.user)
                    }else if(task.exception?.message.isNullOrEmpty()){
                        Toast.makeText(this,task.exception?.message, Toast.LENGTH_LONG).show()
                    }else{
                        sigininEmail()
                    }
                }
        }else{
            Toast.makeText(this,"비밀번호가 맞지않습니다. 다시 입력해주세요",Toast.LENGTH_LONG).show()
        }
    }
    fun sigininEmail(){
        val ao: EditText = findViewById(R.id.pn)
        val aw: EditText = findViewById(R.id.pw)
        auth?.createUserWithEmailAndPassword(ao.text.toString(),aw.text.toString())
            ?.addOnCompleteListener {
                    task ->
                if (task.isSuccessful){
                    MovemainPage(task.result?.user)
                }else{
                    Toast.makeText(this,task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }
    fun MovemainPage(user: FirebaseUser?){ //task람다 형식으로 로그인
        if(user != null){
            val loginintent = Intent(this,searchloadnameActivity::class.java)
            startActivity(loginintent)
        }
    }
}