package com.example.nbaguessthescore.repositories;

import java.util.Date;

public class Repository
{
    private static Repository instance;

    private Date startingDate;

    public static Repository getInstance()
    {
        if(instance == null)
        {
            instance = new Repository();
        }
        return instance;
    }

    public void SendGuessToWebservice()
    {

    }
}
