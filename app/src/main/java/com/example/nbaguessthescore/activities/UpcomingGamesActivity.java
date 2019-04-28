package com.example.nbaguessthescore.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
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
import com.example.nbaguessthescore.R;
import com.example.nbaguessthescore.adapters.UpcomingGamesFirestoreAdapter;
import com.example.nbaguessthescore.detail_activities.GuessActivity;
import com.example.nbaguessthescore.models.UpcomingGame;
import com.example.nbaguessthescore.viewmodels.UpcomingGamesActivityViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

public class UpcomingGamesActivity extends AppCompatActivity implements IUpcomingGamesActivity, SwipeRefreshLayout.OnRefreshListener, UpcomingGamesFirestoreAdapter.IUpGameOnClickListener
{
    private static final String TAG = "UpcomingGamesActivity";

    public Toolbar toolbar;
    public Toolbar dateSelToolbar;
    public TextView dayName;
    public TextView selDate;
    public TextView numGames;
    public Button dateNextBtn;
    public Button dateBackBtn;
    public ProgressBar loadingProgressBar;
    public Animation loadingAnimation;
    public RecyclerView recyclerView;
    private View mParentLayout;

    private ArrayList<UpcomingGame> upGames = new ArrayList<>();

    public UpcomingGamesActivityViewModel upcomingGameViewModel;
    private UpcomingGamesFirestoreAdapter upcomingGamesFirestoreAdapter;
    private DocumentSnapshot mLastQueriedDocument;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_games);

        mParentLayout = findViewById(android.R.id.content);

        loadingProgressBar = findViewById(R.id.progressBar);
        loadingProgressBar.setVisibility(View.GONE);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Upcoming games");
        dateSelToolbar = findViewById(R.id.dateScrollToolbar);
        dayName = findViewById(R.id.dayNameTextView);
        selDate = findViewById(R.id.selDateTextView);
        numGames = findViewById(R.id.numGamesTextView);
        dateNextBtn = findViewById(R.id.selDateForward);
        dateBackBtn = findViewById(R.id.selDateBackwards);

        upcomingGameViewModel = ViewModelProviders.of(this).get(UpcomingGamesActivityViewModel.class);

        initRecyclerView();

        try
        {
            upcomingGameViewModel.init();
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

                getUpGames();
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

        dateNextBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    upcomingGameViewModel.incrementDisplayDate();
                    makeSnackBarMessage("Next date btn pressed!");
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
                    makeSnackBarMessage("Back date btn pressed!");
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
                    if(aBoolean)
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
    }

    private void initRecyclerView()
    {
        if(upcomingGamesFirestoreAdapter == null){
            upcomingGamesFirestoreAdapter = new UpcomingGamesFirestoreAdapter(this, upGames, UpcomingGamesActivity.this, this);
        }

        recyclerView = findViewById(R.id.upGamesRecyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(upcomingGamesFirestoreAdapter);
    }

    private void getUpGames()
    {
        upGames.clear();

        FirebaseFirestore dbReference = FirebaseFirestore.getInstance();
        CollectionReference notesCollectionRef = dbReference.collection("allGames");

        Query upGamesQuery = null;

        if(mLastQueriedDocument != null)
        {
            upGamesQuery = notesCollectionRef
                    .whereEqualTo("StatusNum",1)
                    .whereEqualTo("GameDateUTC",selDate.getText().toString())
                    .orderBy("OrderNo",Query.Direction.ASCENDING)
                    .startAfter(mLastQueriedDocument);
        }
        else
            {
            upGamesQuery = notesCollectionRef
                    .whereEqualTo("StatusNum",1)
                    .whereEqualTo("GameDateUTC",selDate.getText().toString())
                    .orderBy("OrderNo",Query.Direction.ASCENDING);
        }

        upGamesQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot document: task.getResult())
                    {
                        UpcomingGame upGame = document.toObject(UpcomingGame.class);
                        upGames.add(upGame);
                    }

                    upcomingGamesFirestoreAdapter.notifyDataSetChanged();
                }

                else{
                    makeSnackBarMessage("Query Failed. Check Logs.");
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
        String msg;
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
        loadingProgressBar.setVisibility(View.VISIBLE);

        loadingAnimation = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        loadingAnimation.setRepeatCount(-20);
        loadingAnimation.setDuration(2000);

        (findViewById(R.id.progressBar)).setAnimation(loadingAnimation);
    }

    private void hideProgressBar()
    {
        loadingProgressBar.setVisibility(View.GONE);
        (findViewById(R.id.progressBar)).clearAnimation();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onUpGameSelected(UpcomingGame upGame)
    {

    }

    private void makeSnackBarMessage(String message)
    {
        Snackbar.make(mParentLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onUpGameClick(int position)
    {
        Log.d(TAG, "onUpGameClick: clicked");

        Intent intentToGuessAct = new Intent(this, GuessActivity.class);
        intentToGuessAct.putExtra("UpcomingGame", upGames.get(position));
        startActivity(intentToGuessAct);
    }
}