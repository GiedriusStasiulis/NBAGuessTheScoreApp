package com.example.nbaguessthescore.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.nbaguessthescore.R;
import com.example.nbaguessthescore.models.Game;
import java.util.ArrayList;

public class UpGameAdapter extends RecyclerView.Adapter<UpGameAdapter.ViewHolder>
{
    private ArrayList<Game> mGames;
    private IUpGameOnClickListener mUpGameOnClickListener;

    public UpGameAdapter(ArrayList<Game> games, IUpGameOnClickListener upGameOnClickListener)
    {
        this.mGames = games;
        this.mUpGameOnClickListener = upGameOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_upgame_item, parent,false);

        return new ViewHolder(view, mUpGameOnClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position)
    {
        String guessStr = "Your guess: -";

        viewHolder.teamA_triCode.setText(mGames.get(position).getHTeam().getTriCode());
        viewHolder.teamB_triCode.setText(mGames.get(position).getVTeam().getTriCode());
        viewHolder.gameStartTime.setText(mGames.get(position).getStartTimeEastern());
        viewHolder.guessText.setText(guessStr);
    }

    @Override
    public int getItemCount()
    {
        return mGames.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView teamA_triCode;
        TextView teamB_triCode;
        TextView gameStartTime;
        TextView guessText;
        ImageView teamA_logo;
        ImageView teamB_logo;
        IUpGameOnClickListener upGameOnClickListener;

        public ViewHolder(@NonNull View itemView, IUpGameOnClickListener upGameOnClickListener)
        {
            super(itemView);
            teamA_triCode = itemView.findViewById(R.id.teamA_triCode);
            teamB_triCode = itemView.findViewById(R.id.teamB_triCode);
            gameStartTime = itemView.findViewById(R.id.gameStartTime);
            guessText = itemView.findViewById(R.id.guessText);
            teamA_logo = itemView.findViewById(R.id.teamA_logo);
            teamB_logo = itemView.findViewById(R.id.teamB_logo);

            this.upGameOnClickListener = upGameOnClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            upGameOnClickListener.onUpGameClick(getAdapterPosition());
        }
    }

    public interface IUpGameOnClickListener
    {
        void onUpGameClick(int position);
    }
}