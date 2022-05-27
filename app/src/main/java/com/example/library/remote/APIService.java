package com.example.library.remote;

import com.example.library.model.Book;
import com.example.library.model.Borrow;
import com.example.library.model.BorrowResponse;
import com.example.library.model.Favorite;
import com.example.library.model.Post;
import com.example.library.model.User;

import java.sql.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface APIService {
    // GET students from server
    // Server return json array
//    @GET("/index.php")
//    Call<List<Books>> getBooks();
//
//    // GET student by id student from server
//    // Server return json object
//    @GET("/index/index.php")
//    Call<List<Books>> getBooks(@Query("id") String id);


// Test

//    @GET("api/posts")
//    Call<List<Post>> getPost();
//
//    @POST("api/posts")
//    @FormUrlEncoded
//    Call<Post> addPost(@Field("name") String name);
//
//    @PUT("api/posts/{id}")
//    Call<Post> updatePost(@Path("id") int id, @Body Post user);
//
//    @DELETE("api/posts/{id}")
//    Call<Post> deletePost(@Path("id") int id);


// user

    @POST("api/login")
    @FormUrlEncoded
    Call<User> login1(@Field("email") String email,
                              @Field("password") String password);


    @POST("api/register")
    @FormUrlEncoded
    Call<User> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );

    @POST("api/login")
    @FormUrlEncoded
    Call<User> login(
            @Field("email") String email,
            @Field("password") String password
    );


    @POST("api/logout")
    @FormUrlEncoded
    Call<User> logout(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("api/show/{id}")
    Call<User> getUser(@Path("id") int id);

    @PUT("api/update/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);

    @PUT("api/updateavatar/{id}")
    Call<User> updateAvatarUser(@Path("id") int id, @Body User user);

    @GET("api/getbook/{id}")
    Call<List<BorrowResponse>> borrowResponse(@Path("id") int id);

    @GET("api/getbook1/{id}")
    Call<List<BorrowResponse>> borrowResponse1(@Path("id") int id);

    @GET("api/getbook2/{id}")
    Call<List<BorrowResponse>> borrowResponse2(@Path("id") int id);

    @GET("api/getbook3/{id}")
    Call<List<BorrowResponse>> borrowResponse3(@Path("id") int id);

    @GET("api/getfavorite/{id}")
    Call<List<Favorite>> getFavorite(@Path("id") int id);

// book
    @GET("api/books")
    Call<List<Book>> getListBooks();

    // borrow
    @POST("api/borrows")
    @FormUrlEncoded
    Call<Borrow> addBorrow(@Field("user_id") int user_id,
                           @Field("book_id") int book_id,
                           @Field("status") String status,
                           @Field("date_borrow") Date date_borrow);


    @PUT("api/borrows/{id}")
    @FormUrlEncoded
    Call<Borrow> updateBorrow(@Path("id") int id, @Field("return_expect") Date return_expect);

    @POST("api/favorites")
    @FormUrlEncoded
    Call<Favorite> addFavorite(@Field("user_id") int user_id,
                             @Field("book_id") int book_id);

    @DELETE("api/favorites/{id}")
    Call<Favorite> deleteFavorite(@Path("id") int id);
}