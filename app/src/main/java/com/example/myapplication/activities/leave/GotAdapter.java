package com.example.myapplication.activities.leave;


import android.content.Context;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class GotAdapter extends RecyclerView.Adapter<GotAdapter.GotViewHolder> {

    private ArrayList<String> itemList;
    private final Context context;
    private int currPos;

    public GotAdapter(Context context, ArrayList<String> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public GotAdapter(ArrayList<String> list) {
        context = null;
    }

    @NonNull
    @Override
    public GotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.got_item, parent, false);
        GotViewHolder holder = new GotViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GotViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String item = itemList.get(position);
    }

    @Override
    public int getItemCount() {
        return (null != itemList ? itemList.size() : 0);
    }


    public class GotViewHolder extends RecyclerView.ViewHolder {
        protected String name;
        //protected TextView imageName;


        public GotViewHolder(@NonNull View itemView) {
            super(itemView);
            TextView id = (TextView) itemView.findViewById(R.id.gotitem);
            this.name = id.getText().toString();
        }

    }
}