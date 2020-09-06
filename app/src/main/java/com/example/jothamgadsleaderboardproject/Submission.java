package com.example.jothamgadsleaderboardproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

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