package com.tgb.edusolutionassistant

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tgb.edusolutionassistant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*login button*/

        binding.loginButton.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
        }

        //skip button

        binding.skipBtn.setOnClickListener {
            startActivity(Intent(this,DashboardUser::class.java))
        }
    }

}