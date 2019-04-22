package com.example.nbaguessthescore;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nbaguessthescore.adapters.UpGameAdapter;
import com.example.nbaguessthescore.models.Game;
import com.example.nbaguessthescore.models.JSONRoot;
import com.example.nbaguessthescore.viewmodels.UpcomingGameViewModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class TestingActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private Toolbar dateSelToolbar;
    private ProgressBar prBar;
    private Animation animation;
    private TextView dayName;
    private TextView selDate;
    private TextView numGames;
    private Button dateNextBtn;
    private Button dateBackBtn;

    private RecyclerView upGameRView;
    private RecyclerView.Adapter upGameAdapter;

    private UpcomingGameViewModel upcomingGameViewModel;

    private JSONRoot jRoot = new JSONRoot();

    ArrayList<Game> games = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        prBar = findViewById(R.id.progressBar);
        prBar.setVisibility(View.GONE);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Upcoming games");

        dateSelToolbar = findViewById(R.id.dateScrollToolbar);
        dayName = findViewById(R.id.dayNameTextView);
        selDate = findViewById(R.id.selDateTextView);
        numGames = findViewById(R.id.numGamesTextView);
        dateNextBtn = findViewById(R.id.selDateForward);
        dateBackBtn = findViewById(R.id.selDateBackwards);

        upcomingGameViewModel = ViewModelProviders.of(this).get(UpcomingGameViewModel.class);

        try
        {
            upcomingGameViewModel.init();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        try
        {
            upcomingGameViewModel.getUpcomingGames().observe(this, new Observer<JSONRoot>()
            {
                @Override
                public void onChanged(@Nullable JSONRoot jsonRoot)
                {
                    assert jsonRoot != null;
                    jRoot = jsonRoot;
                    numGames.clearComposingText();
                    numGames.setText(String.format(Locale.ENGLISH,"Games: %d", jRoot.getGamesArrList().size()));

                    games.clear();
                    games.addAll(jsonRoot.getGamesArrList());

                    upGameAdapter.notifyDataSetChanged();
                }
            });
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        upcomingGameViewModel.getCurrentDate().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
                selDate.setText(s);
            }
        });

        try
        {
            upcomingGameViewModel.setDateDayName().observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s)
                {
                    dayName.setText(s);
                }
            });
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        upcomingGameViewModel.getIsRefreshing().observe(this, new Observer<Boolean>()
        {
            @Override
            public void onChanged(@Nullable Boolean aBoolean)
            {
                if(aBoolean)
                {
                   Log.d("OnSuccess", "Progress bar starting!");
                   showProgressBar();
                }

                else
                {
                    hideProgressBar();
                    Log.d("OnSuccess", "Progress bar finished!");
                }
            }
        });

        dateNextBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    upcomingGameViewModel.incrementDisplayDate();
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }
        });

        dateBackBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    upcomingGameViewModel.decrementDisplayDate();
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }
        });

        try
        {
            upcomingGameViewModel.getIsDateToday().observe(this, new Observer<Boolean>()
            {
                @Override
                public void onChanged(@Nullable Boolean aBoolean)
                {
                    if(aBoolean == true)
                    {
                        dateBackBtn.setClickable(false);
                        dateBackBtn.setAlpha(0.5F);
                    }
                    else
                    {
                        dateBackBtn.setClickable(true);
                        dateBackBtn.setAlpha(1);
                    }
                }
            });
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        upGameRView = findViewById(R.id.upGameRv);
        upGameRView.hasFixedSize();
        upGameRView.setLayoutManager(new LinearLayoutManager(this));

        upGameAdapter = new UpGameAdapter(games);
        upGameRView.setAdapter(upGameAdapter);
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
        String msg = "";
        switch (item.getItemId())
        {
            case R.id.refresh:

                msg = "Refresh";
                upcomingGameViewModel.refreshUpcomingGames();
                return true;

            case R.id.action_settings:

                msg = "Settings";
                break;

            case R.id.logout:

                msg = "Logout";
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        Toast.makeText(this, msg + " checked", Toast.LENGTH_LONG).show();

        return super.onOptionsItemSelected(item);
    }

    private void showProgressBar()
    {
        prBar.setVisibility(View.VISIBLE);

        animation = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setRepeatCount(-20);
        animation.setDuration(2000);

        ((ProgressBar)findViewById(R.id.progressBar)).setAnimation(animation);
    }

    private void hideProgressBar()
    {
        prBar.setVisibility(View.GONE);
        ((ProgressBar)findViewById(R.id.progressBar)).clearAnimation();
    }
}
