package com.example.nbaguessthescore.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        viewHolder.gameText.setText(mGames.get(position).getGameId());
    }

    @Override
    public int getItemCount()
    {
        return mGames.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView gameText;
        IUpGameOnClickListener upGameOnClickListener;

        public ViewHolder(@NonNull View itemView, IUpGameOnClickListener upGameOnClickListener)
        {
            super(itemView);
            gameText = itemView.findViewById(R.id.gameText);

            this.upGameOnClickListener = upGameOnClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            upGameOnClickListener.onUpGameClick(getAdapterPosition());
        }
    }

    //OnClickListener interface
    public interface IUpGameOnClickListener
    {
        void onUpGameClick(int position);
    }
}