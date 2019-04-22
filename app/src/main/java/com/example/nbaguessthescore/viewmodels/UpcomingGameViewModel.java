package com.example.nbaguessthescore.viewmodels;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;

import com.example.nbaguessthescore.models.Game;
import com.example.nbaguessthescore.models.JSONRoot;
import com.example.nbaguessthescore.repositories.GameRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UpcomingGameViewModel extends ViewModel
{
    private GameRepository gameRepo;

    private LiveData<JSONRoot> upGameJsonRoot;

    private MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();
    private MutableLiveData<String> displayDate = new MutableLiveData<>();
    private MutableLiveData<String> displayDayName = new MutableLiveData<>();
    private MutableLiveData<Boolean> isDateToday = new MutableLiveData<>();
    private MutableLiveData<Boolean> isDateTomorrow = new MutableLiveData<>();

    public void init() throws ParseException
    {
        if(upGameJsonRoot != null)
        {
            return;
        }
        gameRepo = GameRepository.getInstance();
        getCurrentDate();
        upGameJsonRoot = gameRepo.getGamesFromRepo(1,getBaseUrl());
    }

    public LiveData<JSONRoot> getUpcomingGames() throws ParseException
    {
        if(upGameJsonRoot == null)
        {
            gameRepo = GameRepository.getInstance();
            getCurrentDate();
            upGameJsonRoot = gameRepo.getGamesFromRepo(1,getBaseUrl());
        }
        return upGameJsonRoot;
    }

    @SuppressLint("StaticFieldLeak")
    public void refreshUpcomingGames()
    {
        new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                isRefreshing.setValue(true);
            }

            @Override
            protected void onPostExecute(Void aVoid)
            {
                super.onPostExecute(aVoid);
                gameRepo = GameRepository.getInstance();
                //jsonRoot = gameRepo.getNumberOfUpcomingGames();
                isRefreshing.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids)
            {
                try
                {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<Boolean> getIsRefreshing()
    {
        return isRefreshing;
    }

    public MutableLiveData<String> getCurrentDate()
    {
        Date date = Calendar.getInstance().getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String fDate = sdf.format(date);
        displayDate.setValue(fDate);

        return displayDate;
    }

    public MutableLiveData<Boolean> getIsDateToday() throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = sdf.parse(displayDate.getValue());

        if(DateUtils.isToday(date.getTime()))
        {
            isDateToday.setValue(true);
        }
        else
        {
            isDateToday.setValue(false);
        }

        return isDateToday;
    }

    private MutableLiveData<Boolean> getIsDateTomorrow() throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = sdf.parse(displayDate.getValue());

        if(DateUtils.isToday(date.getTime() - DateUtils.DAY_IN_MILLIS))
        {
            isDateTomorrow.setValue(true);
        }
        else
        {
            isDateTomorrow.setValue(false);
        }

        return isDateTomorrow;
    }



    public MutableLiveData<String> setDateDayName() throws ParseException
    {
        String dayName = "";

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = sdf.parse(displayDate.getValue());

        if(getIsDateToday().getValue())
        {
            dayName = "Today";
            displayDayName.setValue(dayName);
        }
        else if(getIsDateTomorrow().getValue())
        {
            dayName = "Tomorrow";
            displayDayName.setValue(dayName);
        }

        else
        {
            SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
            dayName = outFormat.format(date);
            displayDayName.setValue(dayName);
        }

        return displayDayName;
    }

    public void incrementDisplayDate() throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Calendar cal = Calendar.getInstance();
        Date date = sdf.parse(displayDate.getValue());
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);

        Date newDate = cal.getTime();
        String fDate = sdf.format(newDate);
        displayDate.setValue(fDate);

        gameRepo.getGamesFromRepo(1,getBaseUrl());

        setDateDayName();
    }

    public void decrementDisplayDate() throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Calendar cal = Calendar.getInstance();
        Date date = sdf.parse(displayDate.getValue());
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);

        Date newDate = cal.getTime();
        String fDate = sdf.format(newDate);
        displayDate.setValue(fDate);

        gameRepo.getGamesFromRepo(1,getBaseUrl());

        setDateDayName();
    }

    public String getBaseUrl() throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = sdf.parse(displayDate.getValue());
        sdf = new SimpleDateFormat("yyyyMMdd");
        String fDate = sdf.format(date);
        String baseUrl = String.format("%s/scoreboard.json", fDate);

        return baseUrl;
    }
}