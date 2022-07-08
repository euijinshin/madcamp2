package com.example.myapplication.main.notifications;

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

public class NotificationAdapter extends RecyclerView.Adapter<com.example.myapplication.main.notifications.NotificationAdapter.NotificationViewHolder> {

    private ArrayList<String> itemList;
    private final Context context;
    private int currPos;

    public NotificationAdapter(Context context, ArrayList<String> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public NotificationAdapter(ArrayList<String> list) {
        context = null;
    }

    @NonNull
    @Override
    public com.example.myapplication.main.notifications.NotificationAdapter.NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        com.example.myapplication.main.notifications.NotificationAdapter.NotificationViewHolder holder = new com.example.myapplication.main.notifications.NotificationAdapter.NotificationViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.myapplication.main.notifications.NotificationAdapter.NotificationViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String item = itemList.get(position);
    }

    @Override
    public int getItemCount() {
        return (null != itemList ? itemList.size() : 0);
    }


    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        protected String name;
        //protected TextView imageName;


        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            TextView id = (TextView) itemView.findViewById(R.id.gotitem);
            this.name = id.getText().toString();
        }

    }
}