package com.example.moa

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [gasimulFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class gasimulFragment : Fragment() {
    val PICK_IMAGE_FROM_ALBUM = 0

    var photoUri: Uri? = null

    var storage: FirebaseStorage? = null

    private var auth: FirebaseAuth? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Firebase Database
        // Firebase Auth
        auth = FirebaseAuth.getInstance()

        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM)
        val viewview = inflater.inflate(R.layout.fragment_gasimul, container, false)
        viewview.findViewById<ImageView>(R.id.addphoto_image).setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM)
        }

        viewview.findViewById<Button>(R.id.addphoto_btn_upload).setOnClickListener {
            Log.d("start","시작")
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

            val imageFileName = "IMAGE_" + timeStamp + "_.png"
            val storageRef = storage?.reference?.child("images")?.child(imageFileName)
            storageRef?.putFile(photoUri!!)?.continueWithTask{return@continueWithTask storageRef.downloadUrl }?.addOnSuccessListener{ taskSnapshot ->
                Log.d("start","성공")
                Toast.makeText(context,"업로드성공",Toast.LENGTH_SHORT).show();

            }
                ?.addOnFailureListener {
                    Log.d("fail","실패")
                    Toast.makeText(context,"업로드실패",Toast.LENGTH_SHORT).show();
                }
            Log.d("end","끝")
        }

        // Inflate the layout for this fragment
        return viewview
    }
    override fun onActivityResult(requestCode: Int, resultCode  : Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_FROM_ALBUM) {
            //이미지 선택시
            if(resultCode == Activity.RESULT_OK){
                //이미지뷰에 이미지 세팅
                photoUri = data?.data
                view?.findViewById<ImageView>(R.id.addphoto_image)?.setImageURI(data?.data)
            }


        }

    }


}