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
    ArrayList<Game> mGames;
    final private OnListItemClickListener mOnListItemClickListener;

    public UpGameAdapter(ArrayList<Game> games, OnListItemClickListener listener)
    {
        mGames = games;
        mOnListItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_upgame_item, parent,false);

        return new ViewHolder(view);
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

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            gameText = itemView.findViewById(R.id.gameText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    public interface OnListItemClickListener
    {
        void onListItemClick(int clickedItemIndex);
    }
}