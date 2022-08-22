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
        val view = inflater.inflate(R.layout.fragment_gasimul, container, false)
        view.findViewById<ImageView>(R.id.addphoto_image).setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM)
        }

        view.findViewById<Button>(R.id.addphoto_btn_upload).setOnClickListener {
            Log.d("uploadContent", "업로드 클리")
            contentUpload()
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gasimul, container, false)
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

    fun contentUpload(){
        Log.d("uploadContent", "업로드 함수 시작")
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

        val imageFileName = "IMAGE_" + timeStamp + "_.png"
        val storageRef = storage?.reference?.child("images")?.child(imageFileName)
        Toast.makeText(context,"아",Toast.LENGTH_SHORT).show();
        storageRef?.putFile(photoUri!!)?.addOnSuccessListener{ taskSnapshot ->
            Toast.makeText(context,"업로드성공",Toast.LENGTH_SHORT).show();
        }
            ?.addOnFailureListener {
                Toast.makeText(context,"업로드실패",Toast.LENGTH_SHORT).show();
            }
    }

}