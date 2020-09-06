package com.example.jothamgadsleaderboardproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnGADSStartUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGADSStartUp = findViewById(R.id.btnGADSStartUp);

        btnGADSStartUp.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {
                                                  Intent intent = new Intent(MainActivity.this, LeaderBoard.class);
                                                  startActivity(intent);
                                              }
                                          }

        );

    }
}