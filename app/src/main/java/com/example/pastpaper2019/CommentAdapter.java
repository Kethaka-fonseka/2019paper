package com.example.pastpaper2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.commentViewHolder> {

    ArrayList commentList;
    Context context;

    public CommentAdapter(ArrayList commentList, Context context) {
        this.commentList = commentList;
        this.context = context;
    }

    @NonNull
    @Override
    public commentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_comments,parent,false);
commentViewHolder commentViewHolder=new commentViewHolder(view);
return  commentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull commentViewHolder holder, int position) {
holder.comment.setText(commentList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public  class commentViewHolder extends  RecyclerView.ViewHolder{

        TextView comment;
        public commentViewHolder(@NonNull View itemView) {
            super(itemView);
            comment=itemView.findViewById(R.id.commentRecycle);
        }
    }
}
