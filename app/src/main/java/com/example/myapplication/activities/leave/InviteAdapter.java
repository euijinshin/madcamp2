package com.example.myapplication.activities.leave;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class InviteAdapter extends RecyclerView.Adapter<InviteAdapter.InviteViewHolder> {

    private ArrayList<String> itemList;
    private final Context context;
    private int currPos;

    public InviteAdapter(Context context, ArrayList<String> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public InviteAdapter(ArrayList<String> list) {
        context = null;
    }

    @NonNull
    @Override
    public InviteAdapter.InviteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invite_item, parent, false);
        InviteAdapter.InviteViewHolder holder = new InviteViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InviteAdapter.InviteViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String item = itemList.get(position);
    }

    @Override
    public int getItemCount() {
        return (null != itemList ? itemList.size() : 0);
    }


    public class InviteViewHolder extends RecyclerView.ViewHolder {
        protected String name;
        //protected TextView imageName;


        public InviteViewHolder(@NonNull View itemView) {
            super(itemView);
            TextView id = (TextView) itemView.findViewById(R.id.inviteitem);
            this.name = id.getText().toString();
        }

    }
}