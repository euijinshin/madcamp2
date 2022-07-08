package com.example.myapplication.activities.leave;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class WaitRoomAdapter extends RecyclerView.Adapter<WaitRoomAdapter.WaitRoomViewHolder> {
    private ArrayList<String> rands = null;
    private final Context context;

    public class WaitRoomViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public WaitRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.waitroom_text);
        }
    }

    public WaitRoomAdapter(Context context, ArrayList<String> list) {
        rands = list;
        this.context = context;
    }

    @NonNull
    @Override
    public WaitRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.waitroom_item, parent, false);
        WaitRoomAdapter.WaitRoomViewHolder viewHolder = new WaitRoomAdapter.WaitRoomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WaitRoomViewHolder holder, int position) {
        String text = rands.get(position);
        holder.textView.setText(text);
    }

    @Override
    public int getItemCount() {
        return rands.size();
    }
}
