package com.tgb.edusolutionassistant

import android.app.ProgressDialog
import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tgb.edusolutionassistant.databinding.ActivityDashboardUserBinding
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardUser : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardUserBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var database: DatabaseReference

    var recommend_link: Array<String>? = null
    var recommend_rate: Array<Double>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //for search bar

        binding.search1.setOnClickListener {
            startActivity(Intent(this, BookActivity::class.java))
        }

        binding.card1btn1.setOnClickListener {
            val intent = Intent(this, BookActivity::class.java)

            get_cc_books_recommendation()

            val heading = "BOOKS"
            intent.putExtra("recommend_title", heading)

            intent.putExtra("image_url_0", recommend_link?.get(0))
            intent.putExtra("image_url_1", recommend_link?.get(1))
            intent.putExtra("image_url_2", recommend_link?.get(2))
            intent.putExtra("image_url_3", recommend_link?.get(3))
            intent.putExtra("image_url_4", recommend_link?.get(4))
            intent.putExtra("image_url_5", recommend_link?.get(5))
            intent.putExtra("image_url_6", recommend_link?.get(6))
            intent.putExtra("image_url_7", recommend_link?.get(7))
            intent.putExtra("image_url_8", recommend_link?.get(8))

            intent.putExtra("rating_0", recommend_rate?.get(0))
            intent.putExtra("rating_1", recommend_rate?.get(1))
            intent.putExtra("rating_2", recommend_rate?.get(2))
            intent.putExtra("rating_3", recommend_rate?.get(3))
            intent.putExtra("rating_4", recommend_rate?.get(4))
            intent.putExtra("rating_5", recommend_rate?.get(5))
            intent.putExtra("rating_6", recommend_rate?.get(6))
            intent.putExtra("rating_7", recommend_rate?.get(7))
            intent.putExtra("rating_8", recommend_rate?.get(8))

            intent.putExtra("subject","Subject: ")
            intent.putExtra("genre","Course: ")
            intent.putExtra("author","Author: ")
            intent.putExtra("rate","Rating: ")

            intent.putExtra("ALGO", "cc_book")

            startActivity(intent)
        }

        //for notes button

        binding.card2btn1.setOnClickListener {
            val intent = Intent(this, BookActivity::class.java)

            val heading = "BOOKS"
            intent.putExtra("recommend_title", heading)

            get_fc_books_recommendation()

            intent.putExtra("image_url_0", recommend_link?.get(0))
            intent.putExtra("image_url_1", recommend_link?.get(1))
            intent.putExtra("image_url_2", recommend_link?.get(2))
            intent.putExtra("image_url_3", recommend_link?.get(3))
            intent.putExtra("image_url_4", recommend_link?.get(4))
            intent.putExtra("image_url_5", recommend_link?.get(5))
            intent.putExtra("image_url_6", recommend_link?.get(6))
            intent.putExtra("image_url_7", recommend_link?.get(7))
            intent.putExtra("image_url_8", recommend_link?.get(8))

            intent.putExtra("rating_0", recommend_rate?.get(0))
            intent.putExtra("rating_1", recommend_rate?.get(1))
            intent.putExtra("rating_2", recommend_rate?.get(2))
            intent.putExtra("rating_3", recommend_rate?.get(3))
            intent.putExtra("rating_4", recommend_rate?.get(4))
            intent.putExtra("rating_5", recommend_rate?.get(5))
            intent.putExtra("rating_6", recommend_rate?.get(6))
            intent.putExtra("rating_7", recommend_rate?.get(7))
            intent.putExtra("rating_8", recommend_rate?.get(8))

            intent.putExtra("subject","Subject: ")
            intent.putExtra("genre","Course: ")
            intent.putExtra("author","Author: ")
            intent.putExtra("rate","Rating: ")

            startActivity(intent)
        }

        //for videos button

        binding.card3btn1.setOnClickListener {
            val intent = Intent(this, BookActivity::class.java)

            val heading = "BOOKS"
            intent.putExtra("recommend_title", heading)

            get_genre_books_recommendation()

            intent.putExtra("image_url_0", recommend_link?.get(0))
            intent.putExtra("image_url_1", recommend_link?.get(1))
            intent.putExtra("image_url_2", recommend_link?.get(2))
            intent.putExtra("image_url_3", recommend_link?.get(3))
            intent.putExtra("image_url_4", recommend_link?.get(4))
            intent.putExtra("image_url_5", recommend_link?.get(5))
            intent.putExtra("image_url_6", recommend_link?.get(6))
            intent.putExtra("image_url_7", recommend_link?.get(7))
            intent.putExtra("image_url_8", recommend_link?.get(8))

            intent.putExtra("rating_0", recommend_rate?.get(0))
            intent.putExtra("rating_1", recommend_rate?.get(1))
            intent.putExtra("rating_2", recommend_rate?.get(2))
            intent.putExtra("rating_3", recommend_rate?.get(3))
            intent.putExtra("rating_4", recommend_rate?.get(4))
            intent.putExtra("rating_5", recommend_rate?.get(5))
            intent.putExtra("rating_6", recommend_rate?.get(6))
            intent.putExtra("rating_7", recommend_rate?.get(7))
            intent.putExtra("rating_8", recommend_rate?.get(8))

            //intent.putExtra("subject","Subject: ")
            intent.putExtra("genre","Genre: ")
            intent.putExtra("author","Author: ")
            intent.putExtra("rate","Rating: ")

            startActivity(intent)
        }

        //for book button

        binding.card1btn2.setOnClickListener {
            val intent = Intent(this, BookActivity::class.java)

            val heading = "NOTES"
            intent.putExtra("recommend_title", heading)

            get_cc_notes_recommendation()

            intent.putExtra("image_url_0", recommend_link?.get(0))
            intent.putExtra("image_url_1", recommend_link?.get(1))
            intent.putExtra("image_url_2", recommend_link?.get(2))
            intent.putExtra("image_url_3", recommend_link?.get(3))
            intent.putExtra("image_url_4", recommend_link?.get(4))
            intent.putExtra("image_url_5", recommend_link?.get(5))
            intent.putExtra("image_url_6", recommend_link?.get(6))
            intent.putExtra("image_url_7", recommend_link?.get(7))
            intent.putExtra("image_url_8", recommend_link?.get(8))

            intent.putExtra("rating_0", recommend_rate?.get(0))
            intent.putExtra("rating_1", recommend_rate?.get(1))
            intent.putExtra("rating_2", recommend_rate?.get(2))
            intent.putExtra("rating_3", recommend_rate?.get(3))
            intent.putExtra("rating_4", recommend_rate?.get(4))
            intent.putExtra("rating_5", recommend_rate?.get(5))
            intent.putExtra("rating_6", recommend_rate?.get(6))
            intent.putExtra("rating_7", recommend_rate?.get(7))
            intent.putExtra("rating_8", recommend_rate?.get(8))

            intent.putExtra("subject","Subject: ")
            intent.putExtra("genre","Course: ")
            intent.putExtra("author","Written by: ")
            intent.putExtra("rate","Rating: ")

            startActivity(intent)
        }

        //for notes button

        binding.card2btn2.setOnClickListener {
            val intent = Intent(this, BookActivity::class.java)

            val heading = "NOTES"
            intent.putExtra("recommend_title", heading)

            get_fc_notes_recommendation()

            intent.putExtra("image_url_0", recommend_link?.get(0))
            intent.putExtra("image_url_1", recommend_link?.get(1))
            intent.putExtra("image_url_2", recommend_link?.get(2))
            intent.putExtra("image_url_3", recommend_link?.get(3))
            intent.putExtra("image_url_4", recommend_link?.get(4))
            intent.putExtra("image_url_5", recommend_link?.get(5))
            intent.putExtra("image_url_6", recommend_link?.get(6))
            intent.putExtra("image_url_7", recommend_link?.get(7))
            intent.putExtra("image_url_8", recommend_link?.get(8))

            intent.putExtra("rating_0", recommend_rate?.get(0))
            intent.putExtra("rating_1", recommend_rate?.get(1))
            intent.putExtra("rating_2", recommend_rate?.get(2))
            intent.putExtra("rating_3", recommend_rate?.get(3))
            intent.putExtra("rating_4", recommend_rate?.get(4))
            intent.putExtra("rating_5", recommend_rate?.get(5))
            intent.putExtra("rating_6", recommend_rate?.get(6))
            intent.putExtra("rating_7", recommend_rate?.get(7))
            intent.putExtra("rating_8", recommend_rate?.get(8))

            intent.putExtra("subject","Subject: ")
            intent.putExtra("genre","Course: ")
            intent.putExtra("author","Written by: ")
            intent.putExtra("rate","Rating: ")

            startActivity(intent)
        }

        //for videos button

        binding.card3btn2.setOnClickListener {
            val intent = Intent(this, BookActivity::class.java)

            val heading = "NOTES"
            intent.putExtra("recommend_title", heading)

            get_genre_notes_recommendation()

            intent.putExtra("image_url_0", recommend_link?.get(0))
            intent.putExtra("image_url_1", recommend_link?.get(1))
            intent.putExtra("image_url_2", recommend_link?.get(2))
            intent.putExtra("image_url_3", recommend_link?.get(3))
            intent.putExtra("image_url_4", recommend_link?.get(4))
            intent.putExtra("image_url_5", recommend_link?.get(5))
            intent.putExtra("image_url_6", recommend_link?.get(6))
            intent.putExtra("image_url_7", recommend_link?.get(7))
            intent.putExtra("image_url_8", recommend_link?.get(8))

            intent.putExtra("rating_0", recommend_rate?.get(0))
            intent.putExtra("rating_1", recommend_rate?.get(1))
            intent.putExtra("rating_2", recommend_rate?.get(2))
            intent.putExtra("rating_3", recommend_rate?.get(3))
            intent.putExtra("rating_4", recommend_rate?.get(4))
            intent.putExtra("rating_5", recommend_rate?.get(5))
            intent.putExtra("rating_6", recommend_rate?.get(6))
            intent.putExtra("rating_7", recommend_rate?.get(7))
            intent.putExtra("rating_8", recommend_rate?.get(8))

            //intent.putExtra("subject","Subject: ")
            intent.putExtra("genre","Genre: ")
            intent.putExtra("author","Written by: ")
            intent.putExtra("rate","Rating: ")

            startActivity(intent)
        }

        //for book button

        binding.card1btn3.setOnClickListener {
            val intent = Intent(this, BookActivity::class.java)

            val heading = "TUTORIALS"
            intent.putExtra("recommend_title", heading)

            get_cc_videos_recommendation()

            intent.putExtra("image_url_0", recommend_link?.get(0))
            intent.putExtra("image_url_1", recommend_link?.get(1))
            intent.putExtra("image_url_2", recommend_link?.get(2))
            intent.putExtra("image_url_3", recommend_link?.get(3))
            intent.putExtra("image_url_4", recommend_link?.get(4))
            intent.putExtra("image_url_5", recommend_link?.get(5))
            intent.putExtra("image_url_6", recommend_link?.get(6))
            intent.putExtra("image_url_7", recommend_link?.get(7))
            intent.putExtra("image_url_8", recommend_link?.get(8))

            intent.putExtra("rating_0", recommend_rate?.get(0))
            intent.putExtra("rating_1", recommend_rate?.get(1))
            intent.putExtra("rating_2", recommend_rate?.get(2))
            intent.putExtra("rating_3", recommend_rate?.get(3))
            intent.putExtra("rating_4", recommend_rate?.get(4))
            intent.putExtra("rating_5", recommend_rate?.get(5))
            intent.putExtra("rating_6", recommend_rate?.get(6))
            intent.putExtra("rating_7", recommend_rate?.get(7))
            intent.putExtra("rating_8", recommend_rate?.get(8))

            intent.putExtra("subject","Subject: ")
            intent.putExtra("genre","Course: ")
            intent.putExtra("author","Channel: ")
            intent.putExtra("rate","Rating: ")

            startActivity(intent)
        }

        //for notes button

        binding.card2btn3.setOnClickListener {
            val intent = Intent(this, BookActivity::class.java)

            val heading = "TUTORIALS"
            intent.putExtra("recommend_title", heading)

            get_fc_videos_recommendation()

            intent.putExtra("image_url_0", recommend_link?.get(0))
            intent.putExtra("image_url_1", recommend_link?.get(1))
            intent.putExtra("image_url_2", recommend_link?.get(2))
            intent.putExtra("image_url_3", recommend_link?.get(3))
            intent.putExtra("image_url_4", recommend_link?.get(4))
            intent.putExtra("image_url_5", recommend_link?.get(5))
            intent.putExtra("image_url_6", recommend_link?.get(6))
            intent.putExtra("image_url_7", recommend_link?.get(7))
            intent.putExtra("image_url_8", recommend_link?.get(8))

            intent.putExtra("rating_0", recommend_rate?.get(0))
            intent.putExtra("rating_1", recommend_rate?.get(1))
            intent.putExtra("rating_2", recommend_rate?.get(2))
            intent.putExtra("rating_3", recommend_rate?.get(3))
            intent.putExtra("rating_4", recommend_rate?.get(4))
            intent.putExtra("rating_5", recommend_rate?.get(5))
            intent.putExtra("rating_6", recommend_rate?.get(6))
            intent.putExtra("rating_7", recommend_rate?.get(7))
            intent.putExtra("rating_8", recommend_rate?.get(8))

            intent.putExtra("subject","Subject: ")
            intent.putExtra("genre","Course: ")
            intent.putExtra("author","Channel: ")
            intent.putExtra("rate","Rating: ")

            startActivity(intent)
        }

        //for videos button

        binding.card3btn3.setOnClickListener {
            val intent = Intent(this, BookActivity::class.java)

            val heading = "TUTORIALS"
            intent.putExtra("recommend_title", heading)

            get_genre_videos_recommendation()

            intent.putExtra("image_url_0", recommend_link?.get(0))
            intent.putExtra("image_url_1", recommend_link?.get(1))
            intent.putExtra("image_url_2", recommend_link?.get(2))
            intent.putExtra("image_url_3", recommend_link?.get(3))
            intent.putExtra("image_url_4", recommend_link?.get(4))
            intent.putExtra("image_url_5", recommend_link?.get(5))
            intent.putExtra("image_url_6", recommend_link?.get(6))
            intent.putExtra("image_url_7", recommend_link?.get(7))
            intent.putExtra("image_url_8", recommend_link?.get(8))

            intent.putExtra("rating_0", recommend_rate?.get(0))
            intent.putExtra("rating_1", recommend_rate?.get(1))
            intent.putExtra("rating_2", recommend_rate?.get(2))
            intent.putExtra("rating_3", recommend_rate?.get(3))
            intent.putExtra("rating_4", recommend_rate?.get(4))
            intent.putExtra("rating_5", recommend_rate?.get(5))
            intent.putExtra("rating_6", recommend_rate?.get(6))
            intent.putExtra("rating_7", recommend_rate?.get(7))
            intent.putExtra("rating_8", recommend_rate?.get(8))

            //intent.putExtra("subject","Subject: ")
            intent.putExtra("genre","Genre: ")
            intent.putExtra("author","Channel: ")
            intent.putExtra("rate","Rating: ")

            startActivity(intent)
        }

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        readData()//for email display
        fetchinfo()

        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun get_cc_books_recommendation() {
        RETROFIT_API_Object.apiInterface.get_cc_Books("BCA","3").enqueue(object : Callback<Recommendation?> {
            override fun onResponse(call: Call<Recommendation?>, response: Response<Recommendation?>) {
                recommend_link = response.body()?.image_link?.toTypedArray()
                recommend_rate = response.body()?.rating?.toTypedArray()
            }

            override fun onFailure(call: Call<Recommendation?>, t: Throwable) {
                Toast.makeText(this@DashboardUser, "${t.localizedMessage}", Toast.LENGTH_SHORT)
            }
        })
    }

    private fun get_fc_books_recommendation() {
        RETROFIT_API_Object.apiInterface.get_fc_Books("MA").enqueue(object : Callback<Recommendation?> {
            override fun onResponse(call: Call<Recommendation?>, response: Response<Recommendation?>) {
                recommend_link = response.body()?.image_link?.toTypedArray()
                recommend_rate = response.body()?.rating?.toTypedArray()
            }

            override fun onFailure(call: Call<Recommendation?>, t: Throwable) {
                Toast.makeText(this@DashboardUser, "${t.localizedMessage}", Toast.LENGTH_SHORT)
            }
        })
    }

    private fun get_genre_books_recommendation() {
        RETROFIT_API_Object.apiInterface.get_genre_Books("Business").enqueue(object : Callback<Recommendation?> {
            override fun onResponse(call: Call<Recommendation?>, response: Response<Recommendation?>) {
                recommend_link = response.body()?.image_link?.toTypedArray()
                recommend_rate = response.body()?.rating?.toTypedArray()
            }

            override fun onFailure(call: Call<Recommendation?>, t: Throwable) {
                Toast.makeText(this@DashboardUser, "${t.localizedMessage}", Toast.LENGTH_SHORT)
            }
        })
    }

    private fun get_cc_notes_recommendation() {
        RETROFIT_API_Object.apiInterface.get_cc_notes("BCA","3").enqueue(object : Callback<Recommendation?> {
            override fun onResponse(call: Call<Recommendation?>, response: Response<Recommendation?>) {
                recommend_link = response.body()?.image_link?.toTypedArray()
                recommend_rate = response.body()?.rating?.toTypedArray()
            }

            override fun onFailure(call: Call<Recommendation?>, t: Throwable) {
                Toast.makeText(this@DashboardUser, "${t.localizedMessage}", Toast.LENGTH_SHORT)
            }
        })
    }

    private fun get_fc_notes_recommendation() {
        RETROFIT_API_Object.apiInterface.get_fc_notes("MA").enqueue(object : Callback<Recommendation?> {
            override fun onResponse(call: Call<Recommendation?>, response: Response<Recommendation?>) {
                recommend_link = response.body()?.image_link?.toTypedArray()
                recommend_rate = response.body()?.rating?.toTypedArray()
            }

            override fun onFailure(call: Call<Recommendation?>, t: Throwable) {
                Toast.makeText(this@DashboardUser, "${t.localizedMessage}", Toast.LENGTH_SHORT)
            }
        })
    }

    private fun get_genre_notes_recommendation() {
        RETROFIT_API_Object.apiInterface.get_genre_notes("Business").enqueue(object : Callback<Recommendation?> {
            override fun onResponse(call: Call<Recommendation?>, response: Response<Recommendation?>) {
                recommend_link = response.body()?.image_link?.toTypedArray()
                recommend_rate = response.body()?.rating?.toTypedArray()
            }

            override fun onFailure(call: Call<Recommendation?>, t: Throwable) {
                Toast.makeText(this@DashboardUser, "${t.localizedMessage}", Toast.LENGTH_SHORT)
            }
        })
    }

    private fun get_cc_videos_recommendation() {
        RETROFIT_API_Object.apiInterface.get_cc_videos("BCA","3").enqueue(object : Callback<Recommendation?> {
            override fun onResponse(call: Call<Recommendation?>, response: Response<Recommendation?>) {
                recommend_link = response.body()?.image_link?.toTypedArray()
                recommend_rate = response.body()?.rating?.toTypedArray()
            }

            override fun onFailure(call: Call<Recommendation?>, t: Throwable) {
                Toast.makeText(this@DashboardUser, "${t.localizedMessage}", Toast.LENGTH_SHORT)
            }
        })
    }

    private fun get_fc_videos_recommendation() {
        RETROFIT_API_Object.apiInterface.get_fc_videos("MA").enqueue(object : Callback<Recommendation?> {
            override fun onResponse(call: Call<Recommendation?>, response: Response<Recommendation?>) {
                recommend_link = response.body()?.image_link?.toTypedArray()
                recommend_rate = response.body()?.rating?.toTypedArray()
            }

            override fun onFailure(call: Call<Recommendation?>, t: Throwable) {
                Toast.makeText(this@DashboardUser, "${t.localizedMessage}", Toast.LENGTH_SHORT)
            }
        })
    }

    private fun get_genre_videos_recommendation() {
        RETROFIT_API_Object.apiInterface.get_genre_videos("Business").enqueue(object : Callback<Recommendation?> {
            override fun onResponse(call: Call<Recommendation?>, response: Response<Recommendation?>) {
                recommend_link = response.body()?.image_link?.toTypedArray()
                recommend_rate = response.body()?.rating?.toTypedArray()
            }
            override fun onFailure(call: Call<Recommendation?>, t: Throwable) {
                Toast.makeText(this@DashboardUser, "${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }

//for email display

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {

            binding.subTitleTv.text = "Not Logged In"
        } else {

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

    //for heading display

    private fun fetchinfo() {
        val id = firebaseAuth.uid.toString()
        database = FirebaseDatabase.getInstance().getReference("Information")
        database.child(id).get().addOnSuccessListener {

            if (it.exists()) {

                val cou_book = it.child("books_of_current_course").value
                binding.textBox1.text = cou_book.toString()

                val fut_book = it.child("future_course_name").value
                binding.textBox2.text = fut_book.toString()

                val genre = it.child("books_of_intrest").value
                binding.textBox3.text = genre.toString()
            }
        }
    }
}

