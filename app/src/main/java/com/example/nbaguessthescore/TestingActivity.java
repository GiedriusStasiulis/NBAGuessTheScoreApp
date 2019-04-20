package com.example.nbaguessthescore;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.nbaguessthescore.models.JSONRoot;
import com.example.nbaguessthescore.viewmodels.UpcomingGameViewModel;

public class TestingActivity extends AppCompatActivity
{
    Toolbar toolbar;

    private UpcomingGameViewModel upcomingGameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Upcoming games");
        toolbar.setSubtitle("Games today: ");



        upcomingGameViewModel = ViewModelProviders.of(this).get(UpcomingGameViewModel.class);
        upcomingGameViewModel.init();
        upcomingGameViewModel.getNumOfUpGames().observe(this, new Observer<JSONRoot>()
        {
            @Override
            public void onChanged(@Nullable JSONRoot jsonRoot)
            {
                toolbar.setSubtitle(toolbar.getSubtitle() + " " + jsonRoot.getNumGames());
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
        String msg = " ";
        switch (item.getItemId())
        {
            case R.id.refresh:

                msg = "Refresh";
                break;

            case R.id.action_settings:

                msg = "Settings";
                break;

            case R.id.logout:

                msg = "Logout";
                break;
        }
        Toast.makeText(this, msg + " checked", Toast.LENGTH_LONG).show();

        return super.onOptionsItemSelected(item);
    }
}
