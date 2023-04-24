package com.tgb.edusolutionassistant

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RETROFIT_API_Interface {
//    Preforming Recommendation Task for Data(Book, Note, Tutorial/Video, Search)
//
//    Extracting Books
//
//    Current Course Book
    @POST("/cc/book")
    @FormUrlEncoded
    fun get_cc_Books(@Field("course") course:String, @Field("semester") semester:String): Call<Recommendation>

//    Feature Course Books
    @POST("/fc/book")
    @FormUrlEncoded
    fun get_fc_Books(@Field("course") course:String): Call<Recommendation>

//    Genre Books
    @POST("/genre/book")
    @FormUrlEncoded
    fun get_genre_Books(@Field("genre") genre:String): Call<Recommendation>

//    Extracting Notes

//    Current Course Notes
    @POST("/cc/notes")
    @FormUrlEncoded
    fun get_cc_notes(@Field("course") course:String, @Field("semester") semester:String): Call<Recommendation>

//    Feature Course Notes
    @POST("/fc/notes")
    @FormUrlEncoded
    fun get_fc_notes(@Field("course") course:String): Call<Recommendation>

//    Genre Notes
    @POST("/genre/notes")
    @FormUrlEncoded
    fun get_genre_notes(@Field("genre") genre:String): Call<Recommendation>

//    Extracting Videos

//    Current Course Videos
    @POST("/cc/videos")
    @FormUrlEncoded
    fun get_cc_videos(@Field("course") course:String, @Field("semester") semester:String): Call<Recommendation>

//    Feature Course Videos
    @POST("/fc/videos")
    @FormUrlEncoded
    fun get_fc_videos(@Field("course") course:String): Call<Recommendation>

//    Genre Videos
    @POST("/genre/videos")
    @FormUrlEncoded
    fun get_genre_videos(@Field("genre") genre:String): Call<Recommendation>

//    Preforming Search Task
    @POST("/search/link")
    @FormUrlEncoded
    fun get_search_data(@Field("name") name:String, @Field("genre") genre:String): Call<Recommendation>


//    Extracting Complete Details of a Unique Data (Book, Note, Tutorial/Video)
//
//    Extracting Details of Books
//
//    Current Course Book
    @POST("/cc/book/details")
    @FormUrlEncoded
    fun finding_cc_book_ditails(@Field("link") image_link: String): Call<Details_cc>


//    Feature Course Books
    @POST("/fc/book/details")
    fun finding_fc_book_ditails(@Field("link") image_link: String): Call<Details_fc>

//    Genre Books
    @POST("/genre/book/details")
    @FormUrlEncoded
    fun  finding_genre_book_ditails(@Field("link") image_link: String): Call<Details_genre>

//    Extracting Details of Notes

//    Current Course Notes
    @POST("/cc/notes/details")
    @FormUrlEncoded
    fun  finding_cc_notes_ditails(@Field("link") image_link: String): Call<Details_cc>

//    Feature Course Notes
    @POST("/fc/notes/details")
    @FormUrlEncoded
    fun  finding_fc_notes_ditails(@Field("link") image_link: String): Call<Details_fc>

//    Genre Notes
    @POST("/genre/notes/details")
    @FormUrlEncoded
    fun  finding_genre_notes_ditails(@Field("link") image_link: String): Call<Details_genre>

//    Extracting Details of Videos

//    Current Course Videos
    @POST("/cc/videos/details")
    @FormUrlEncoded
    fun  finding_cc_videos_ditails(@Field("link") image_link: String): Call<Details_cc>

//    Feature Course Videos
    @POST("/fc/videos/details")
    @FormUrlEncoded
    fun  finding_fc_videos_ditails(@Field("link") image_link: String): Call<Details_fc>

//    Genre Videos
    @POST("/genre/videos/details")
    @FormUrlEncoded
    fun  finding_genre_videos_ditails(@Field("link") image_link: String): Call<Details_genre>
}