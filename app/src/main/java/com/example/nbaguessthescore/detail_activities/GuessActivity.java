package com.example.nbaguessthescore.detail_activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.nbaguessthescore.R;
import com.example.nbaguessthescore.models.UpcomingGame;

import java.text.ParseException;

public class GuessActivity extends AppCompatActivity
{
    private static final String TAG = "GuessActivity";

    private TextView detailGameID;
    private TextView guessPtsTxtView;
    private ToggleButton teamASelectBtn;
    private ToggleButton teamBSelectBtn;
    private Button incrementPtsVal;
    private Button decrementPtsVal;
    private Button submitGuess;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        Log.d(TAG, "onCreate: Started!");

        detailGameID = findViewById(R.id.detailGameID);
        guessPtsTxtView = findViewById(R.id.guessPtsTxtView);
        teamASelectBtn = findViewById(R.id.teamASelectBtn);
        teamBSelectBtn = findViewById(R.id.teamBSelectBtn);
        incrementPtsVal = findViewById(R.id.incrementPtsVal);
        decrementPtsVal = findViewById(R.id.decrementPtsVal);
        submitGuess = findViewById(R.id.submitGuess);

        UpcomingGame upGame = (UpcomingGame) getIntent().getSerializableExtra("UpcomingGame");

        detailGameID.setText(upGame.getGameId());
        guessPtsTxtView.setText("0");
        teamASelectBtn.setText(upGame.getTeamATriCode());
        teamBSelectBtn.setText(upGame.getTeamBTriCode());
        teamASelectBtn.setTextOn(upGame.getTeamATriCode());
        teamASelectBtn.setTextOff(upGame.getTeamATriCode());
        teamBSelectBtn.setTextOn(upGame.getTeamBTriCode());
        teamBSelectBtn.setTextOff(upGame.getTeamBTriCode());

        teamASelectBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(teamBSelectBtn.isChecked())
                {
                    teamBSelectBtn.setChecked(false);
                }
            }
        });

        teamBSelectBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(teamASelectBtn.isChecked())
                {
                    teamASelectBtn.setChecked(false);
                }
            }
        });

        incrementPtsVal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            }
        });

        decrementPtsVal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            }
        });

        submitGuess.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            }
        });


    }
}
