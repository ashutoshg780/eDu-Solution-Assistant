package com.tgb.edusolutionassistant

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tgb.edusolutionassistant.databinding.ActivityDashboardAdminBinding

class Dashboard_Admin : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardAdminBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //for search bar

        binding.search1.setOnClickListener {
            startActivity(Intent(this,BookActivity::class.java))
        }

        //for book button

        binding.card1btn1.setOnClickListener {
            startActivity(Intent(this,BookActivity::class.java))
        }

        //for notes button

        binding.card2btn1.setOnClickListener {
            startActivity(Intent(this,BookActivity::class.java))
        }

        //for videos button

        binding.card3btn1.setOnClickListener {
            startActivity(Intent(this,BookActivity::class.java))
        }

        //for book button

        binding.card1btn2.setOnClickListener {
            startActivity(Intent(this,BookActivity::class.java))
        }

        //for notes button

        binding.card2btn2.setOnClickListener {
            startActivity(Intent(this,BookActivity::class.java))
        }

        //for videos button

        binding.card3btn2.setOnClickListener {
            startActivity(Intent(this,BookActivity::class.java))
        }

        //for book button

        binding.card1btn3.setOnClickListener {
            startActivity(Intent(this,BookActivity::class.java))
        }

        //for notes button

        binding.card2btn3.setOnClickListener {
            startActivity(Intent(this,BookActivity::class.java))
        }

        //for videos button

        binding.card3btn3.setOnClickListener {
            startActivity(Intent(this,BookActivity::class.java))
        }

        /*Firebase Authentication*/

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        readData()
        fetchinfo()

        /*logout button*/

        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
    }

    //for email display

    private  fun  checkUser(){
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser == null){

            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        else{
            val email = firebaseUser.email

            binding.subTitleTv.text = email
        }
    }

    //for name display

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

    //for page heading

    private fun fetchinfo(){
        val id = firebaseAuth.uid.toString()
        database = FirebaseDatabase.getInstance().getReference("Information")
        database.child(id).get().addOnSuccessListener {

            if (it.exists()) {

                val cou_book = it.child("books_of_current_course").value
                binding.textBox1.text = cou_book.toString().uppercase()

                val fut_book = it.child("future_course_name").value
                binding.textBox2.text = fut_book.toString().uppercase()

                val genre = it.child("books_of_intrest").value
                binding.textBox3.text = genre.toString().uppercase()
            }
        }
    }

}