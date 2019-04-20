package com.example.nbaguessthescore;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.nbaguessthescore.models.JSONRoot;
import com.example.nbaguessthescore.viewmodels.UpcomingGameViewModel;

public class TestingActivity extends AppCompatActivity
{
    private static final String TAG = "TestingActivity";
    private UpcomingGameViewModel upGameViewModel;

    TextView txtView;
    TextView txtView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        final Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        txtView = (TextView) findViewById(R.id.textView);
        txtView2 = (TextView) toolbar.findViewById(R.id.textView2);
        toolbar.setTitle("Upcoming gamesssssss");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        upGameViewModel = ViewModelProviders.of(this).get(UpcomingGameViewModel.class);
        upGameViewModel.init();
        upGameViewModel.getNumOfUpGames().observe(this, new Observer<JSONRoot>()
        {
            @Override
            public void onChanged(@Nullable JSONRoot jsonRoot)
            {
                txtView.setText(txtView.getText() + " " + jsonRoot.getNumGames());

                toolbar.setTitle(toolbar.getTitle() + " (" + jsonRoot.getNumGames() + ")");
            }
        });

    }

}
