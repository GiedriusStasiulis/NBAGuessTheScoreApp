package com.example.nbaguessthescore.detail_activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.ahmadrosid.svgloader.SvgLoader;
import com.example.nbaguessthescore.R;
import com.example.nbaguessthescore.models.UpcomingGame;
import com.example.nbaguessthescore.viewmodels.GuessDetailActivityViewModel;

import java.util.Locale;
import java.util.Objects;

public class GuessDetailActivity extends AppCompatActivity
{
    private static final String TAG = "GuessDetailActivity";

    private boolean isGuessExist;

    public Toolbar toolbar;
    public TextView guessPtsTxtView;
    public ToggleButton teamASelectBtn;
    public ToggleButton teamBSelectBtn;
    public Button decrementPtsVal;
    public Button submitGuess;
    public ImageView teamA_logo;
    public ImageView teamB_logo;
    public TextView teamAFullName;
    public TextView teamBFullName;

    public UpcomingGame upGame;

    private View mParentLayout;

    private GuessDetailActivityViewModel guessDetailActivityViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        mParentLayout = findViewById(android.R.id.content);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Upcoming games");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        teamA_logo = findViewById(R.id.teamA_logo);
        teamB_logo = findViewById(R.id.teamB_logo);
        teamAFullName = findViewById(R.id.teamAFullName);
        teamBFullName = findViewById(R.id.teamBFullName);

        teamASelectBtn = findViewById(R.id.teamASelectBtn);
        teamBSelectBtn = findViewById(R.id.teamBSelectBtn);
        guessPtsTxtView = findViewById(R.id.guessPtsTxtView);
        Button incrementPtsVal = findViewById(R.id.incrementPtsVal);
        decrementPtsVal = findViewById(R.id.decrementPtsVal);
        submitGuess = findViewById(R.id.submitGuess);
        Button add5Pts = findViewById(R.id.add5Pts);
        Button add10Pts = findViewById(R.id.add10Pts);
        Button add15Pts = findViewById(R.id.add15Pts);
        Button add20Pts = findViewById(R.id.add20Pts);

        upGame = (UpcomingGame) getIntent().getSerializableExtra("UpcomingGame");

        guessDetailActivityViewModel = ViewModelProviders.of(this).get(GuessDetailActivityViewModel.class);
        guessDetailActivityViewModel.init();

        SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)//use ur place holder
                .load(upGame.getTeamALogoSrc(),teamA_logo);

        SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)//use ur place holder
                .load(upGame.getTeamBLogoSrc(),teamB_logo);

        teamAFullName.setText(upGame.getTeamAFullName());
        teamBFullName.setText(upGame.getTeamBFullName());

        teamASelectBtn.setText(upGame.getTeamATriCode());
        teamBSelectBtn.setText(upGame.getTeamBTriCode());
        teamASelectBtn.setTextOn(upGame.getTeamATriCode());
        teamASelectBtn.setTextOff(upGame.getTeamATriCode());
        teamBSelectBtn.setTextOn(upGame.getTeamBTriCode());
        teamBSelectBtn.setTextOff(upGame.getTeamBTriCode());


        guessDetailActivityViewModel.checkIfGuessExists("U010101",upGame.getGameId()).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean)
            {
                isGuessExist = aBoolean;
            }
        });

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
                if(isGuessExist)
                {
                    Toast.makeText(GuessDetailActivity.this,"Guess already exists. Please go to My Guesses to update or delete.", Toast.LENGTH_LONG).show();
                    finish();
                }

                else
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
                        makeSnackBarMessage("Please select which team will win.");
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        String msg;
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;

            case R.id.action_settings:

                msg = "Settings";
                break;

            case R.id.logout:

                msg = "Logout";
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }

    private void makeSnackBarMessage(String message)
    {
        Snackbar.make(mParentLayout, message, Snackbar.LENGTH_SHORT).show();
    }
}