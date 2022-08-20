package com.example.moa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.remove
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

class realMainActivity : AppCompatActivity() {

    var viewList = arrayListOf<View>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_real_main)

        // 하단 탭이 눌렸을 때 화면을 전환하기 위해선 이벤트 처리하기 위해 BottomNavigationView 객체 생성
        var bnv_main = findViewById(R.id.bnv_main) as BottomNavigationView

        // OnNavigationItemSelectedListener를 통해 탭 아이템 선택 시 이벤트를 처리
        // navi_menu.xml 에서 설정했던 각 아이템들의 id를 통해 알맞은 프래그먼트로 변경하게 한다.
        bnv_main.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        // 다른 프래그먼트 화면으로 이동하는 기능
                        val homeFragment = homeFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, homeFragment).commit()
                    }
                    R.id.writegaesimul -> {
                        val boardFragment = gasimulFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, boardFragment).commit()
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