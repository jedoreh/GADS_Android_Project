package com.example.jothamgadsleaderboardproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Submission extends AppCompatActivity {

    Button btnBackToLeaderBoard;
    EditText edtFirstName;
    EditText edtLastName;
    EditText edtEmail;
    EditText edtGitHub;
    Button btnSubmit;

    ImageButton imgBtnCloseConfirm;
    Button btnConfirm;

    CardView cardViewSubmitSuccess;
    CardView cardViewSubmitFail;
    CardView cardViewSubmit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        btnBackToLeaderBoard = findViewById(R.id.btnBackToLeaderBoard);


        btnBackToLeaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Submission.this, LeaderBoard.class);
                startActivity(intent);
            }
        });

        btnConfirm = findViewById(R.id.btnConfirm);
        btnSubmit = findViewById(R.id.btnSubmit);
        cardViewSubmit = findViewById(R.id.cardViewSubmit);
        cardViewSubmitFail = findViewById(R.id.cardViewSubmitFail);
        cardViewSubmitSuccess = findViewById(R.id.cardViewSubmitSuccess);

        edtEmail = findViewById(R.id.edtEmail);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtGitHub = findViewById(R.id.edtGitHub);

        imgBtnCloseConfirm = findViewById(R.id.imgbtnCloseConfirm);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViewSubmit.setVisibility(View.VISIBLE);
            }
        });

        imgBtnCloseConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViewSubmit.setVisibility(View.INVISIBLE);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViewSubmit.setVisibility(View.INVISIBLE);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://docs.google.com/forms/d/e/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

                Post post = new Post(edtEmail.getText().toString(),
                        edtFirstName.getText().toString(),
                        edtLastName.getText().toString(),
                        edtGitHub.getText().toString());


                Call<Post> call = jsonPlaceHolderApi.createPost(post);

                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {

                        if(!response.isSuccessful()){
                            cardViewSubmitFail.setVisibility(View.VISIBLE);
                            return;
                        }


                        Post postResponse = response.body();


                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        cardViewSubmitFail.setVisibility(View.VISIBLE);
                    }



                });




                cardViewSubmitSuccess.setVisibility(View.VISIBLE);
            }
        });

        cardViewSubmitSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViewSubmitSuccess.setVisibility(View.INVISIBLE);
            }
        });


    }




}