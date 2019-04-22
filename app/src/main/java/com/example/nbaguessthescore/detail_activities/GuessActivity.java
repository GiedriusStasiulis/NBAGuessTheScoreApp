package com.example.nbaguessthescore.detail_activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.nbaguessthescore.R;

public class GuessActivity extends AppCompatActivity
{
    private static final String TAG = "GuessActivity";

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        Log.d(TAG, "onCreate: Started!");

        textView = findViewById(R.id.detailGameID);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("GameID"))
        {
            String gameId = bundle.getString("GameID");
            textView.setText(gameId);
        }
        else
        {
            textView.setText("No GameID received :(");
        }
    }
}
