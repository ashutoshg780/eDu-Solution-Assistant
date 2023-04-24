package com.tgb.edusolutionassistant

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tgb.edusolutionassistant.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var progressDailog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDailog = ProgressDialog(this)
        progressDailog.setTitle("Please Wait")
        progressDailog.setCanceledOnTouchOutside(false)

        //forget password

        binding.forgetTv.setOnClickListener {
            startActivity(Intent(this, FpasswordActivity::class.java))
        }

        //signup button

        binding.noAccountTv.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        //login button

        binding.loginBtn.setOnClickListener {
            validateData()
        }
    }

    private var email = ""
    private var password = ""

    /*username and password validation*/

    private fun validateData() {
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid email format...", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show()
        } else {
            loginUser()
        }
    }

    /*verify username and password from database*/

    private fun loginUser() {
        progressDailog.setMessage("Logging In...")
        progressDailog.show()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                checkUser()
            }
            .addOnFailureListener { e ->
                progressDailog.dismiss()
                Toast.makeText(this, "Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    /*opens dashboard according to user type */

    private fun checkUser() {
        progressDailog.setMessage("Checking User...")

        val firebaseUser = firebaseAuth.currentUser!!

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(firebaseUser.uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot:DataSnapshot) {
                    progressDailog.dismiss()

                    val userType = snapshot.child("userType").value
                    if (userType == "user"){
                        startActivity(Intent(this@SignInActivity,DashboardUser::class.java))
                        finish()
                    }
                    else if (userType == "admin"){
                        startActivity(Intent(this@SignInActivity,Dashboard_Admin::class.java))
                        finish()
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }
}