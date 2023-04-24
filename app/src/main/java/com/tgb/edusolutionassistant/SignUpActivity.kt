package com.tgb.edusolutionassistant

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.tgb.edusolutionassistant.databinding.ActivitySignUpBinding
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var progressDailog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //firebase Authentication

        firebaseAuth = FirebaseAuth.getInstance()

        progressDailog = ProgressDialog(this)
        progressDailog.setTitle("Please Wait")
        progressDailog.setCanceledOnTouchOutside(false)

        //back button

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }

        //Register button

        binding.registerBtn.setOnClickListener {
            validateData()
        }
    }


    //variavle initialization

    private var name = ""
    private var email = ""
    private var password = ""

    //binding of variables to their respective identities

    private fun validateData() {
        name = binding.nameEt.text.toString().trim()
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()
        val cPassword = binding.cpasswordEt.text.toString().trim()

        //check all fields are filled or not

        if (name.isEmpty()) {
            Toast.makeText(this, "Enter your name...", Toast.LENGTH_SHORT).show()

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid Email Pattern...", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show()

        } else if (cPassword.isEmpty()) {
            Toast.makeText(this, "Confirm password...", Toast.LENGTH_SHORT).show()
        } else if (password != cPassword) {
            Toast.makeText(this, "Password doesn't match...", Toast.LENGTH_SHORT).show()
        } else {
            createUserAccount()
        }
    }

    //permissions for uploading data to database

    private fun createUserAccount() {
        progressDailog.setMessage("Creating Account...")
        progressDailog.show()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                updateUserInfo()
            }
            .addOnFailureListener { e ->
                progressDailog.dismiss()
                Toast.makeText(
                    this,
                    "Failed creating account due to${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    //uploading data to database

    private fun updateUserInfo() {
        progressDailog.setMessage("Saving user info...")
        val timestamp = System.currentTimeMillis()

        val uid = firebaseAuth.uid

        val hashMap: HashMap<String, Any?> = HashMap()
        hashMap["uid"] = uid
        hashMap["email"] = email
        hashMap["name"] = name
        hashMap["profileImage"] = ""
        hashMap["userType"] = "user"
        hashMap["timestamp"] = timestamp

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(uid!!)
            .setValue(hashMap)
            .addOnSuccessListener {
                progressDailog.dismiss()
                Toast.makeText(this, "Account created...", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                progressDailog.dismiss()
                Toast.makeText(
                    this, "Failed saving user info due to${e.message}", Toast.LENGTH_SHORT
                ).show()
            }
    }
}