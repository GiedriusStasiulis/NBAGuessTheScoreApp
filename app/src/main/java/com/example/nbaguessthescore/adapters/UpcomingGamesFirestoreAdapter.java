package com.example.nbaguessthescore.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ahmadrosid.svgloader.SvgLoader;
import com.example.nbaguessthescore.R;
import com.example.nbaguessthescore.activities.IUpcomingGamesActivity;
import com.example.nbaguessthescore.activities.UpcomingGamesActivity;
import com.example.nbaguessthescore.models.UpcomingGame;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UpcomingGamesFirestoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final String TAG = "UpcomingGameFirestoreAdapter";

    private ArrayList<UpcomingGame> upGames = new ArrayList<>();
    private IUpcomingGamesActivity iUpcomingGamesActivity;
    private UpcomingGamesActivity upcomingGamesActivity;
    private Context context;
    private Context appContext;
    private int selectedUpGameIndex;
    private Activity activity;

    public UpcomingGamesFirestoreAdapter(Context _context, ArrayList<UpcomingGame> _upGames, Activity _activity)
    {
        upGames = _upGames;
        this.context = _context;
        this.activity = _activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        RecyclerView.ViewHolder holder;
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_upgame_item, parent, false);

        holder = new ViewHolder(view);

        appContext = parent.getContext();

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        if(holder instanceof ViewHolder)
        {
            if(upGames.get(position).getGameStartTimeUTC().equals("TBD"))
            {
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");

                try
                {
                    Date dTime = sdf.parse(upGames.get(position).getGameStartTimeUTC());
                    Calendar gc = new GregorianCalendar();
                    gc.setTime(dTime);
                    gc.add(Calendar.HOUR, 2);
                    Date dTimeUTC2 = gc.getTime();
                    String newTime = sdf.format(dTimeUTC2);
                    ((ViewHolder)holder).gameStartTime.setText(newTime);
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                ((ViewHolder)holder).gameStartTime.setText(upGames.get(position).getGameStartTimeUTC());
            }

            ((ViewHolder)holder).teamA_triCode.setText(upGames.get(position).getTeamATriCode());
            ((ViewHolder)holder).teamB_triCode.setText(upGames.get(position).getTeamBTriCode());


            SvgLoader.pluck()
                    .with(activity)
                    .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)//use ur place holder
                    .load(upGames.get(position).getTeamALogoSrc(),((ViewHolder)holder).teamA_logo);

            SvgLoader.pluck()
                    .with(activity)
                    .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)//use ur place holder
                    .load(upGames.get(position).getTeamBLogoSrc(),((ViewHolder)holder).teamB_logo);

            ((ViewHolder)holder).guessText.setText("-");
        }
    }

    @Override
    public int getItemCount()
    {
        return upGames.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
        iUpcomingGamesActivity = (IUpcomingGamesActivity) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView teamA_triCode;
        public TextView teamB_triCode;
        public TextView gameStartTime;
        public TextView guessText;
        public ImageView teamA_logo;
        public ImageView teamB_logo;

        public ViewHolder(View itemView)
        {
            super(itemView);
            teamA_triCode = itemView.findViewById(R.id.teamA_triCode);
            teamB_triCode = itemView.findViewById(R.id.teamB_triCode);
            gameStartTime = itemView.findViewById(R.id.gameStartTime);
            guessText = itemView.findViewById(R.id.guessText);
            teamA_logo = itemView.findViewById(R.id.teamA_logo);
            teamB_logo = itemView.findViewById(R.id.teamB_logo);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            selectedUpGameIndex = getAdapterPosition();
            iUpcomingGamesActivity.onUpGameSelected(upGames.get(selectedUpGameIndex));
        }
    }
}
