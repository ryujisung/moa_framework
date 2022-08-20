package com.example.moa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class searchloadnameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchloadname)
        findViewById<Button>(R.id.search_btn).setOnClickListener {
            val loginintent = Intent(this,realMainActivity::class.java)

            startActivity(loginintent)
        }
    }

}