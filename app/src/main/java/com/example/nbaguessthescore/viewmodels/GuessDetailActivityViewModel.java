package com.example.nbaguessthescore.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.nbaguessthescore.models.Guess;
import com.example.nbaguessthescore.repositories.Repository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class GuessDetailActivityViewModel extends ViewModel
{
    private Repository repo;

    public MutableLiveData<Integer> ptsValue = new MutableLiveData<>();
    public MutableLiveData<Boolean> isGuessExisting = new MutableLiveData<>();
    public MutableLiveData<Guess> guess = new MutableLiveData<>();

    public void init()
    {
        repo = Repository.getInstance();
        initPtsValue();
    }

    public void initPtsValue()
    {
        int initValue = 1;
        ptsValue.setValue(initValue);
    }

    public MutableLiveData<Integer> getPtsValue()
    {
        return ptsValue;
    }

    public void incrementPtsVal()
    {
        int addValue = 1;
        ptsValue.setValue(ptsValue.getValue() + addValue);
    }

    public void decrementPtsVal()
    {
        int minusValue = 1;
        ptsValue.setValue(ptsValue.getValue() - minusValue);
    }

    public void setPts(int pts)
    {
        ptsValue.setValue(0);
        ptsValue.setValue(pts);
    }

    public void submitGuess(String userId, String gameId, String selTeam, int byPts)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference guessRef = db.collection("guesses").document();

        Guess guess = new Guess(userId,gameId,selTeam,byPts);

        guessRef.set(guess).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Log.d("Success", "Added new guess to collection!");
                }
                else
                {
                    Log.d("Fail", "Did not add new guess to collection");
                }
            }
        });
    }

    public MutableLiveData<Boolean> checkIfGuessExists(String userId, String gameId)
    {
        isGuessExisting.setValue(false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference guessRef = db.collection("guesses");

        Query query = guessRef.whereEqualTo("userId",userId).whereEqualTo("gameId",gameId).limit(1);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot doc: task.getResult())
                    {
                        isGuessExisting.setValue(true);
                        Log.d("OK", "Found guess, can edit");
                    }
                }
                else
                {
                    Log.d("OK", "No guess found, can create new");
                }
            }
        });

        return isGuessExisting;
    }

    public Guess getGuess(String userId, String gameId)
    {
        guess.setValue(null);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference guessRef = db.collection("guesses");

        Query query = guessRef.whereEqualTo("userId",userId).whereEqualTo("gameId",gameId).limit(1);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot doc: task.getResult())
                    {
                        guess.setValue(doc.toObject(Guess.class));
                    }
                }
                else
                {
                    Log.d("OK", "No guess found, can create new");
                }
            }
        });

        return guess.getValue();
    }
}
