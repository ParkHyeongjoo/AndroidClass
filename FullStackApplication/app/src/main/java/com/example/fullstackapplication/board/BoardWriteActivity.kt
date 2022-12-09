package com.example.fullstackapplication.board

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.fullstackapplication.R
import com.example.fullstackapplication.utils.FBAuth
import com.example.fullstackapplication.utils.FBdatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class BoardWriteActivity : AppCompatActivity() {

    lateinit var imgBoardAdd: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        val etBoardTitle = findViewById<EditText>(R.id.etBoardTitle)
        val etBoardContent = findViewById<EditText>(R.id.etBoardContent)
        imgBoardAdd = findViewById(R.id.imgBoardAdd)
        val imgBoardWrite = findViewById<ImageView>(R.id.imgBoardWrite)

        imgBoardAdd.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )
            launcher.launch(intent)
        }

        imgBoardWrite.setOnClickListener {
            val title = etBoardTitle.text.toString()
            val content = etBoardContent.text.toString()

//            board
//              - key (게시물의 고유한 uid : push())
//                  - boardVO (title, content, 사용자 uid, time)
//            FBdatabase.getBoardRef().push().setValue(BoardVO("1","1","1","1"))

//            auth
            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()

//            실제데이터를 넣어주자
//            setValue 가 되기전에 미리 BoardVO가 저장될 key값(uid)을 만들자
            var key = FBdatabase.getBoardRef().push().key.toString()

            FBdatabase.getBoardRef().child(key).setValue(BoardVO(title, content, uid, time))
            imgUpload(key)
            finish()

        }
    }

    fun imgUpload(key: String) {

        val storage = Firebase.storage
        val storageRef = storage.reference
        val mountainsRef = storageRef.child("$key.png")

        // Get the data from an ImageView as bytes
        imgBoardAdd.isDrawingCacheEnabled = true
        imgBoardAdd.buildDrawingCache()
        val bitmap = (imgBoardAdd.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) imgBoardAdd.setImageURI(it.data?.data)
    }
}