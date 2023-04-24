package com.tgb.edusolutionassistant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.tgb.edusolutionassistant.databinding.ActivityFpasswordBinding

class FpasswordActivity : AppCompatActivity() {

    private lateinit var etPassword: EditText
    private lateinit var btnResetPassword: Button
    private lateinit var binding: ActivityFpasswordBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFpasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //back button

        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
        etPassword = findViewById(R.id.femailEt)
        btnResetPassword = findViewById(R.id.Btn_reset)

        auth = FirebaseAuth.getInstance()

        //password check

        btnResetPassword.setOnClickListener {
            val sPassword = etPassword.text.toString()
            auth.sendPasswordResetEmail(sPassword)
                .addOnSuccessListener {
                    Toast.makeText(this, "Please Check your Email", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, SignInActivity::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                }
        }
    }
}
