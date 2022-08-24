package com.example.moa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [profileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class profileFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewview = inflater.inflate(R.layout.fragment_profile, container, false)
        viewview.findViewById<Button>(R.id.logout_btn).setOnClickListener {
            Toast.makeText(context,"로그아웃", Toast.LENGTH_SHORT).show();
            var intent = Intent(context,LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        // Inflate the layout for this fragment
        return viewview
    }
}