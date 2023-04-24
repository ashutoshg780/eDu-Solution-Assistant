package com.tgb.edusolutionassistant

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tgb.edusolutionassistant.databinding.ActivityDescriptionBooksBinding

class BookDescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionBooksBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBooksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*Firebase Authentication*/

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()//check user for displaying email
        readData()// check user for displaying name
        imagedisplay()


        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
    }
}

    //email display

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            val email = firebaseUser.email
            binding.subTitleTv.text = email
        }
    }

    //name display

    private fun readData() {
        val uid = firebaseAuth.uid.toString()
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(uid).get().addOnSuccessListener {

            if (it.exists()) {
                val firstname = it.child("name").value
                binding.titleTv.text = firstname.toString()
            } else {
                binding.titleTv.text = "Guest User"
            }
        }
    }

    private fun imagedisplay(){
        val imageUrl = intent.getStringExtra("image_url")
        val rate = intent.getStringExtra("rate")
        val title = intent.getStringExtra("title")
        val subject = intent.getStringExtra("subject")
        val genre = intent.getStringExtra("genre")
        val author = intent.getStringExtra("author")
        val description = intent.getStringExtra("description")

        val imageView = findViewById<ImageView>(R.id.image)
        val titleView = findViewById<TextView>(R.id.title)
        val subjectView = findViewById<TextView>(R.id.subject)
        val genreView = findViewById<TextView>(R.id.course_genra)
        val authorView = findViewById<TextView>(R.id.author_weiterby_channel)
        val ratingView = findViewById<TextView>(R.id.rating)
        val descriptionView = findViewById<TextView>(R.id.description)

        titleView.text = title
        subjectView.text = subject
        genreView.text = genre
        authorView.text = author
        ratingView .text = rate
        descriptionView.text = description

        Glide.with(this)
            .load(imageUrl)
            .into(imageView)
    }
}