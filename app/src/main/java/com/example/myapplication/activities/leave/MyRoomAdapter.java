package com.example.myapplication.activities.leave;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MyRoomAdapter extends RecyclerView.Adapter<MyRoomAdapter.ViewHolder> {

    private ArrayList<JoinMember> mData = null;
    private final Context context;

    public MyRoomAdapter(Context context, ArrayList<JoinMember> mData) {
        this.mData = mData;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView score;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.people_name);
            score = itemView.findViewById(R.id.people_score);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.myroom_people, parent, false) ;
        MyRoomAdapter.ViewHolder vh = new MyRoomAdapter.ViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = mData.get(position).getName();
        String score = mData.get(position).getScore();
        holder.name.setText(name);
        holder.score.setText(score);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
