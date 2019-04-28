package com.example.nbaguessthescore.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.nbaguessthescore.R;
import com.example.nbaguessthescore.detail_activities.GuessDetailActivity;

import java.text.ParseException;
import java.util.Objects;

public class DashboardActivity extends AppCompatActivity
{
    private static final String TAG = "DashboardActivity";

    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Welcome!");
        Objects.requireNonNull(getSupportActionBar()).setSubtitle("UserName");

        Button upGamesBtn = findViewById(R.id.upGamesBtn);
        Button gameResultsBtn = findViewById(R.id.gameResultsBtn);
        Button myGuessesBtn = findViewById(R.id.myGuessesBtn);
        Button highScoresBtn = findViewById(R.id.highScoresBtn);
        Button myProfileBtn = findViewById(R.id.myProfileBtn);
        Button howToPlayBtn = findViewById(R.id.howToPlayBtn);

        upGamesBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intentToUpGameAct = new Intent(v.getContext(), UpcomingGamesActivity.class);
                startActivity(intentToUpGameAct);
            }
        });

        gameResultsBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                
            }
        });

        myGuessesBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        highScoresBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        myProfileBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        howToPlayBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }
}
