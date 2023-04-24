package com.tgb.edusolutionassistant

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.tgb.edusolutionassistant.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var progressDailog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner = findViewById<Spinner>(R.id.CcmEt)
        val spinner1 = findViewById<Spinner>(R.id.dobEt)
        val spinner2 = findViewById<Spinner>(R.id.BkccEt)
        val spinner3 = findViewById<Spinner>(R.id.FcnEt)
        val spinner4 = findViewById<Spinner>(R.id.CryrEt)
        val spinner5 = findViewById<Spinner>(R.id.BkiEt)

        ArrayAdapter.createFromResource(
            this,
            R.array.current_course_mode_options,
            R.layout.color_spinner_layout
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout)
            spinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.Age_options,
            R.layout.color_spinner_layout
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout)
            spinner1.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.Books_For_Current_Course_options,
            R.layout.color_spinner_layout
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout)
            spinner2.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.Books_For_Future_Course_options,
            R.layout.color_spinner_layout
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout)
            spinner3.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.current_course_year_options,
            R.layout.color_spinner_layout
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout)
            spinner4.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.Books_Of_Intrest_options,
            R.layout.color_spinner_layout
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.color_spinner_dropdown_layout)
            spinner5.adapter = adapter
        }

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (position == 1) {
                    spinner3.setSelection(1)
                }else if(position== 2){
                    spinner3.setSelection(2)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        /*Firebase Authentication*/

        firebaseAuth = FirebaseAuth.getInstance()

        progressDailog = ProgressDialog(this)
        progressDailog.setTitle("Please Wait")
        progressDailog.setCanceledOnTouchOutside(false)

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
        binding.registerBtn.setOnClickListener {
            validateData()
        }
    }

    //variable declaration

    private var date_of_birth = ""
    private var books_of_current_course = ""
    private var current_course_year = ""
    private var current_course_mode = ""
    private var future_course_name = ""
    private var future_goals = ""
    private var books_of_intrest = ""

    //data check

    private fun validateData() {
        date_of_birth = binding.dobEt.selectedItem.toString().trim()
        books_of_current_course = binding.BkccEt.selectedItem.toString().trim()
        current_course_year = binding.CryrEt.selectedItem.toString().trim()
        current_course_mode = binding.CcmEt.selectedItem.toString().trim()
        future_course_name = binding.FcnEt.selectedItem.toString().trim()
        future_goals = binding.FgEt.text.toString().trim()
        books_of_intrest = binding.BkiEt.selectedItem.toString().trim()

        if (date_of_birth == "Age Group") {
            Toast.makeText(this, "Please select your Age Group", Toast.LENGTH_SHORT).show()
        } else if (books_of_current_course == "CURRENTLY PURSUING") {
            Toast.makeText(this, "Please select your Currently Course", Toast.LENGTH_SHORT).show()
        } else if (current_course_year == "SEMESTER") {
            Toast.makeText(this, "Please select your Semester", Toast.LENGTH_SHORT).show()
        } else if (current_course_mode == "COURSE MODE") {
            Toast.makeText(this, "Please select your Current course Mode", Toast.LENGTH_SHORT).show()
        } else if (future_course_name == "FUTURE REFERENCE") {
            Toast.makeText(this, "Enter your Future References", Toast.LENGTH_SHORT).show()
        } else if (future_goals.isEmpty()) {
            Toast.makeText(this, "Enter your future goals...", Toast.LENGTH_SHORT).show()
        } else if (books_of_intrest == "GENRE") {
            Toast.makeText(this, "Enter your Genre", Toast.LENGTH_SHORT).show()
        } else {
            updateUserInfo()
        }
    }

    //upload data to firebase

    private fun updateUserInfo() {
        progressDailog.show()

        val timestamp = System.currentTimeMillis()

        val uid = firebaseAuth.uid

        val hashMap: HashMap<String, Any?> = HashMap()

        hashMap["uid"] = "$uid"
        hashMap["timestamp"] = "$timestamp"
        hashMap["DOB"] = date_of_birth
        hashMap["books_of_current_course"] = books_of_current_course
        hashMap["current_course_year"] = current_course_year
        hashMap["current_course_mode"] = current_course_mode
        hashMap["future_course_name"] = future_course_name
        hashMap["future_goals"] = future_goals
        hashMap["books_of_intrest"] = books_of_intrest

        val ref = FirebaseDatabase.getInstance().getReference("Information")
        ref.child("$uid")
            .setValue(hashMap)
            .addOnSuccessListener {
                progressDailog.dismiss()
                Toast.makeText(this, "Information Added successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@LoginActivity, DashboardUser::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                progressDailog.dismiss()
                Toast.makeText(
                    this, "failed to add info due to ${e.message}", Toast.LENGTH_SHORT).show()
            }

    }
}