package com.example.pastpaper2019;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    ArrayList GameList;
    ArrayList YearList;
    Context context;

    public GameAdapter(ArrayList gameList,ArrayList yearList, Context context) {
        GameList = gameList;
        YearList = yearList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_game,parent,false);
       ViewHolder viewHolder=new ViewHolder(view);
       return  viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
holder.Name.setText(GameList.get(position).toString());
holder.Year.setText(YearList.get(position).toString());
holder.parentLaout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(holder.parentLaout.getContext(),GameOverview.class);
        intent.putExtra("game",GameList.get(position).toString());
        holder.parentLaout.getContext().startActivity(intent);
    }
});
    }

    @Override
    public int getItemCount() {
        return YearList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
TextView Name,Year;
RelativeLayout parentLaout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.txtGameName);
            Year=itemView.findViewById(R.id.txtYear);
            parentLaout=itemView.findViewById(R.id.parentlayout);


        }
    }
}
