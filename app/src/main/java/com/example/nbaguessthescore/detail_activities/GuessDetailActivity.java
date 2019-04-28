package com.example.nbaguessthescore.detail_activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.nbaguessthescore.R;
import com.example.nbaguessthescore.models.UpcomingGame;
import com.example.nbaguessthescore.viewmodels.GuessDetailActivityViewModel;

import java.util.Locale;

public class GuessDetailActivity extends AppCompatActivity
{
    private static final String TAG = "GuessDetailActivity";

    private TextView guessPtsTxtView;
    private ToggleButton teamASelectBtn;
    private ToggleButton teamBSelectBtn;
    private Button decrementPtsVal;
    public UpcomingGame upGame;

    private View mParentLayout;

    private GuessDetailActivityViewModel guessDetailActivityViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        mParentLayout = findViewById(android.R.id.content);

        guessPtsTxtView = findViewById(R.id.guessPtsTxtView);
        teamASelectBtn = findViewById(R.id.teamASelectBtn);
        teamBSelectBtn = findViewById(R.id.teamBSelectBtn);
        Button incrementPtsVal = findViewById(R.id.incrementPtsVal);
        decrementPtsVal = findViewById(R.id.decrementPtsVal);
        Button submitGuess = findViewById(R.id.submitGuess);
        Button add5Pts = findViewById(R.id.add5Pts);
        Button add10Pts = findViewById(R.id.add10Pts);
        Button add15Pts = findViewById(R.id.add15Pts);
        Button add20Pts = findViewById(R.id.add20Pts);

        upGame = (UpcomingGame) getIntent().getSerializableExtra("UpcomingGame");

        guessDetailActivityViewModel = ViewModelProviders.of(this).get(GuessDetailActivityViewModel.class);
        guessDetailActivityViewModel.init();

        teamASelectBtn.setText(upGame.getTeamATriCode());
        teamBSelectBtn.setText(upGame.getTeamBTriCode());
        teamASelectBtn.setTextOn(upGame.getTeamATriCode());
        teamASelectBtn.setTextOff(upGame.getTeamATriCode());
        teamBSelectBtn.setTextOn(upGame.getTeamBTriCode());
        teamBSelectBtn.setTextOff(upGame.getTeamBTriCode());

        guessDetailActivityViewModel.getPtsValue().observe(this, new Observer<Integer>()
        {
            @Override
            public void onChanged(@Nullable Integer integer)
            {
                if(integer == 1)
                {
                    decrementPtsVal.setEnabled(false);
                }
                else
                {
                    decrementPtsVal.setEnabled(true);
                }
                String ptsVal = String.format(Locale.ENGLISH,"%d",integer);
                guessPtsTxtView.setText(ptsVal);
            }
        });

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
                guessDetailActivityViewModel.incrementPtsVal();
            }
        });

        decrementPtsVal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                guessDetailActivityViewModel.decrementPtsVal();
            }
        });

        add5Pts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                guessDetailActivityViewModel.setPts(5);
            }
        });

        add10Pts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                guessDetailActivityViewModel.setPts(10);
            }
        });

        add15Pts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                guessDetailActivityViewModel.setPts(15);
            }
        });

        add20Pts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                guessDetailActivityViewModel.setPts(20);
            }
        });

        submitGuess.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(teamASelectBtn.isChecked())
                {
                    String selTeam = teamASelectBtn.getTextOn().toString();
                    guessDetailActivityViewModel.submitGuess("U010101",upGame.getGameId(),selTeam,Integer.parseInt(guessPtsTxtView.getText().toString()));
                    Toast.makeText(GuessDetailActivity.this,"Thank you! Your guess has been saved.", Toast.LENGTH_LONG).show();
                    finish();
                }

                if(teamBSelectBtn.isChecked())
                {
                    String selTeam = teamBSelectBtn.getTextOn().toString();
                    guessDetailActivityViewModel.submitGuess("U010101",upGame.getGameId(),selTeam,Integer.parseInt(guessPtsTxtView.getText().toString()));
                    Toast.makeText(GuessDetailActivity.this,"Thank you! Your guess has been saved.", Toast.LENGTH_LONG).show();
                    finish();
                }

                else if(!teamASelectBtn.isChecked() && !teamBSelectBtn.isChecked())
                {
                    makeSnackBarMessage("Please select which team will win :)");
                }
            }
        });
    }

    private void makeSnackBarMessage(String message)
    {
        Snackbar.make(mParentLayout, message, Snackbar.LENGTH_SHORT).show();
    }
}