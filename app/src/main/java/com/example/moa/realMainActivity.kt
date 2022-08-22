package com.example.moa

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.remove
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.jar.Manifest

class realMainActivity : AppCompatActivity() {

    var viewList = arrayListOf<View>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_real_main)



        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        // 하단 탭이 눌렸을 때 화면을 전환하기 위해선 이벤트 처리하기 위해 BottomNavigationView 객체 생성
        var bnv_main = findViewById(R.id.bnv_main) as BottomNavigationView

        // OnNavigationItemSelectedListener를 통해 탭 아이템 선택 시 이벤트를 처리
        //
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


        }
        bnv_main.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        // 다른 프래그먼트 화면으로 이동하는 기능
                        val homeFragment = homeFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, homeFragment).commit()
                    }
                    R.id.search -> {
                        val settingFragment = searchFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, settingFragment).commit()
                    }
                    R.id.writegaesimul -> {
                        if (ContextCompat.checkSelfPermission(this@realMainActivity, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                            val settingFragment = gasimulFragment()
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.fl_container, settingFragment).commit()
                        }
                        else {
                            Toast.makeText(this@realMainActivity, "스토리지 읽기 권한이 없습니다.", Toast.LENGTH_LONG).show()
                        }
                    }
                    R.id.findroad -> {
                        val settingFragment = findroadFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, settingFragment).commit()
                    }
                    R.id.MyProfile -> {
                        val settingFragment = profileFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, settingFragment).commit()
                    }
                }
                true
            }
            selectedItemId = R.id.home
        }
    }
}