package com.example.nbaguessthescore.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nbaguessthescore.R;

public class UpcomingGameViewHolder extends RecyclerView.ViewHolder
{
    public TextView teamA_triCode;
    public TextView teamB_triCode;
    public TextView gameStartTime;
    public TextView guessText;
    public ImageView teamA_logo;
    public ImageView teamB_logo;

    public UpcomingGameViewHolder(@NonNull View itemView)
    {
        super(itemView);
        teamA_triCode = itemView.findViewById(R.id.teamA_triCode);
        teamB_triCode = itemView.findViewById(R.id.teamB_triCode);
        gameStartTime = itemView.findViewById(R.id.gameStartTime);
        guessText = itemView.findViewById(R.id.guessText);
        teamA_logo = itemView.findViewById(R.id.teamA_logo);
        teamB_logo = itemView.findViewById(R.id.teamB_logo);
    }
}
