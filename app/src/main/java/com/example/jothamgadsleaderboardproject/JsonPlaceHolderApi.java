package com.example.jothamgadsleaderboardproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<List<Post>> getPosts();

 /*   @POST("posts")
    Call<Post> createPost(@Body Post post);*/

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Post> createPost(
            @Field("entry.1824927963") String emailAddress,
            @Field("entry.1877115667") String name,
            @Field("entry.2006916086") String lastName,
            @Field("entry.284483984") String linkToProject
            );

}
