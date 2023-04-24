package com.tgb.edusolutionassistant

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tgb.edusolutionassistant.databinding.ActivityBookBinding
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var database: DatabaseReference

    var title: Array<String>? = null
    var subject: Array<String>? = null
    var genre: Array<String>? = null
    var author: Array<String>? = null
    var sem: Array<Int>? = null
    var description: Array<String>? = null

    lateinit var image_link: String

    lateinit var algo:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /*Firebase Authentication*/

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()  //for user check
        readData()  //for read data
        imagedisplay()

        /*logout button*/

        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()  // for user check
        }
    }

    private fun loadImageAndSetTextViews(imageUrl: String?, rate: String?, imageView: ImageView, rateTextView: TextView) {
        Glide.with(this)
            .load(imageUrl)
            .into(imageView)
        rateTextView.text=rate
    }

    private fun imagedisplay() {
        val recommend_title = intent.getStringExtra("recommend_title")
        val heading_title = findViewById<TextView>(R.id.heading_title)
        heading_title.text = recommend_title

        val Tsubject = intent.getStringExtra("subject")
        val Tgenre = intent.getStringExtra("genre")
        val Tauthor = intent.getStringExtra("author")
        val Trate = intent.getStringExtra("rate")

        val imageUrlArray = arrayOf(
            intent.getStringExtra("image_url_0"),
            intent.getStringExtra("image_url_1"),
            intent.getStringExtra("image_url_2"),
            intent.getStringExtra("image_url_3"),
            intent.getStringExtra("image_url_4"),
            intent.getStringExtra("image_url_5"),
            intent.getStringExtra("image_url_6"),
            intent.getStringExtra("image_url_7"),
            intent.getStringExtra("image_url_8")
        )

        val rateArray = arrayOf(
            intent.getStringExtra("rating_0"),
            intent.getStringExtra("rating_1"),
            intent.getStringExtra("rating_2"),
            intent.getStringExtra("rating_3"),
            intent.getStringExtra("rating_4"),
            intent.getStringExtra("rating_5"),
            intent.getStringExtra("rating_6"),
            intent.getStringExtra("rating_7"),
            intent.getStringExtra("rating_8")
        )

        val imageViewArray = arrayOf(
            findViewById<ImageView>(R.id.cardBook1),
            findViewById<ImageView>(R.id.cardBook2),
            findViewById<ImageView>(R.id.cardBook3),
            findViewById<ImageView>(R.id.cardBook4),
            findViewById<ImageView>(R.id.cardBook5),
            findViewById<ImageView>(R.id.cardBook6),
            findViewById<ImageView>(R.id.cardBook7),
            findViewById<ImageView>(R.id.cardBook8),
            findViewById<ImageView>(R.id.cardBook9)
        )

        val rateTextViewArray = arrayOf(
            findViewById<TextView>(R.id.rate_0),
            findViewById<TextView>(R.id.rate_1),
            findViewById<TextView>(R.id.rate_2),
            findViewById<TextView>(R.id.rate_3),
            findViewById<TextView>(R.id.rate_4),
            findViewById<TextView>(R.id.rate_5),
            findViewById<TextView>(R.id.rate_6),
            findViewById<TextView>(R.id.rate_7),
            findViewById<TextView>(R.id.rate_8)
        )

        for (i in 0..8) {

            loadImageAndSetTextViews(imageUrlArray[i],rateArray[i],imageViewArray[i],rateTextViewArray[i])

            imageViewArray[i].setOnClickListener{
                val intent = Intent(this, BookDescriptionActivity::class.java)
                intent.putExtra("image_url", imageUrlArray[i])
                intent.putExtra("rate",Trate + rateArray[i])

                image_link= imageUrlArray[i].toString()
                algo = intent.getStringExtra("ALGO").toString()

                get_getails()

                intent.putExtra("title", title?.get(0))
                intent.putExtra("subject",Tsubject + subject?.get(0))
                intent.putExtra("genre",Tgenre + genre?.get(0)+"-"+ sem?.get(0))
                intent.putExtra("author",Tauthor + author?.get(0))
                intent.putExtra("description", description?.get(0))

                startActivity(intent)
            }
        }
    }

    private fun get_getails() {

//        if (algo == "cc_book"){
//            RETROFIT_API_Object.apiInterface.finding_cc_book_ditails(image_link).enqueue(object : Callback<Details_cc?> {
//                override fun onResponse(call: Call<Details_cc?>, response: Response<Details_cc?>) {
//                    title = response.body()?.title?.toTypedArray()
//                    subject = response.body()?.Subject?.toTypedArray()
//                    genre = response.body()?.Course?.toTypedArray()
//                    sem = response.body()?.Semester?.toTypedArray()
//                    author = response.body()?.author?.toTypedArray()
//                    description = response.body()?.Desc?.toTypedArray()
//                }
//                override fun onFailure(call: Call<Details_cc?>, t: Throwable) {
//                    Toast.makeText(this@BookActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            })
//        }
//        else if (algo == "fc_book"){
//            RETROFIT_API_Object.apiInterface.finding_fc_book_ditails(image_link).enqueue(object : Callback<Details_fc?> {
//                override fun onResponse(call: Call<Details_fc?>, response: Response<Details_fc?>) {
//                    title = response.body()?.title?.toTypedArray()
//                    subject = response.body()?.Subject?.toTypedArray()
//                    genre = response.body()?.Course?.toTypedArray()
//                    author = response.body()?.author?.toTypedArray()
//                    description = response.body()?.Desc?.toTypedArray()
//                }
//
//                override fun onFailure(call: Call<Details_fc?>, t: Throwable) {
//                    Toast.makeText(this@BookActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            })
//        }
//        else if (algo == "genre_book"){
            RETROFIT_API_Object.apiInterface.finding_genre_book_ditails(image_link).enqueue(object : Callback<Details_genre?> {
                override fun onResponse(
                    call: Call<Details_genre?>,
                    response: Response<Details_genre?>
                ) {
                    title = response.body()?.title?.toTypedArray()
                    genre = response.body()?.genre?.toTypedArray()
                    author = response.body()?.author?.toTypedArray()
                    description = response.body()?.Desc?.toTypedArray()
                }

                override fun onFailure(call: Call<Details_genre?>, t: Throwable) {
                    Toast.makeText(this@BookActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
//        }
//        else if (algo == "cc_notes"){
//            RETROFIT_API_Object.apiInterface.finding_cc_notes_ditails()_ditails(image_link).enqueue(object : Callback<Details_cc?> {
//                override fun onResponse(call: Call<Details_cc?>, response: Response<Details_cc?>) {
//                    title = response.body()?.title?.toTypedArray()
//                    subject = response.body()?.Subject?.toTypedArray()
//                    genre = response.body()?.Course?.toTypedArray()
//                    sem = response.body()?.Semester?.toTypedArray()
//                    author = response.body()?.author?.toTypedArray()
//                    description = response.body()?.Desc?.toTypedArray()
//                }
//                override fun onFailure(call: Call<Details_cc?>, t: Throwable) {
//                    Toast.makeText(this@BookActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            })
//        }
//        else if (algo == "fc_notes"){
//            RETROFIT_API_Object.apiInterface.finding_fc_notes_ditails(image_link).enqueue(object : Callback<Details_fc?> {
//                override fun onResponse(call: Call<Details_fc?>, response: Response<Details_fc?>) {
//                    title = response.body()?.title?.toTypedArray()
//                    subject = response.body()?.Subject?.toTypedArray()
//                    genre = response.body()?.Course?.toTypedArray()
//                    author = response.body()?.author?.toTypedArray()
//                    description = response.body()?.Desc?.toTypedArray()
//                }
//
//                override fun onFailure(call: Call<Details_fc?>, t: Throwable) {
//                    Toast.makeText(this@BookActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            })
//        }
//        else if (algo == "genre_notes"){
//            RETROFIT_API_Object.apiInterface.finding_genre_notes_ditails(image_link).enqueue(object : Callback<Details_genre?> {
//                override fun onResponse(
//                    call: Call<Details_genre?>,
//                    response: Response<Details_genre?>
//                ) {
//                    title = response.body()?.title?.toTypedArray()
//                    genre = response.body()?.genre?.toTypedArray()
//                    author = response.body()?.author?.toTypedArray()
//                    description = response.body()?.Desc?.toTypedArray()
//                }
//
//                override fun onFailure(call: Call<Details_genre?>, t: Throwable) {
//                    Toast.makeText(this@BookActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            })
//        }
//        else if (algo == "cc_videos"){
//            RETROFIT_API_Object.apiInterface.finding_cc_book_ditails(image_link).enqueue(object : Callback<Details_cc?> {
//                override fun onResponse(call: Call<Details_cc?>, response: Response<Details_cc?>) {
//                    title = response.body()?.title?.toTypedArray()
//                    subject = response.body()?.Subject?.toTypedArray()
//                    genre = response.body()?.Course?.toTypedArray()
//                    sem = response.body()?.Semester?.toTypedArray()
//                    author = response.body()?.author?.toTypedArray()
//                    description = response.body()?.Desc?.toTypedArray()
//                }
//                override fun onFailure(call: Call<Details_cc?>, t: Throwable) {
//                    Toast.makeText(this@BookActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            })
//        }
//        else if (algo == "fc_videos"){
//            RETROFIT_API_Object.apiInterface.finding_fc_book_ditails(image_link).enqueue(object : Callback<Details_fc?> {
//                override fun onResponse(call: Call<Details_fc?>, response: Response<Details_fc?>) {
//                    title = response.body()?.title?.toTypedArray()
//                    subject = response.body()?.Subject?.toTypedArray()
//                    genre = response.body()?.Course?.toTypedArray()
//                    author = response.body()?.author?.toTypedArray()
//                    description = response.body()?.Desc?.toTypedArray()
//                }
//
//                override fun onFailure(call: Call<Details_fc?>, t: Throwable) {
//                    Toast.makeText(this@BookActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            })
//        }
//        else if (algo == "genre_videos"){
//            RETROFIT_API_Object.apiInterface.finding_genre_book_ditails(image_link).enqueue(object : Callback<Details_genre?> {
//                override fun onResponse(
//                    call: Call<Details_genre?>,
//                    response: Response<Details_genre?>
//                ) {
//                    title = response.body()?.title?.toTypedArray()
//                    genre = response.body()?.genre?.toTypedArray()
//                    author = response.body()?.author?.toTypedArray()
//                    description = response.body()?.Desc?.toTypedArray()
//                }
//
//                override fun onFailure(call: Call<Details_genre?>, t: Throwable) {
//                    Toast.makeText(this@BookActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            })
//        }
    }

    // check user
    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {  //check if user is there or not

            startActivity(Intent(this, MainActivity::class.java))  //start activity
            finish()
        } else {
            val email = firebaseUser.email  //for displaying user email

            binding.subTitleTv.text = email
        }
    }

    //for displaying name of user

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

}