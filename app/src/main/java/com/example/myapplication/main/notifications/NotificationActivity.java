package com.example.myapplication.main.notifications;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.activities.leave.GotFragment;
import com.example.myapplication.activities.leave.InviteFragment;
import com.example.myapplication.activities.leave.WaitRoomActivity;
import com.example.myapplication.activities.leave.WaitRoomAdapter;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ArrayList<String> list = new ArrayList<>();

        list.add("Samuel");

        RecyclerView recyclerView = findViewById(R.id.rvNotification);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NotificationAdapter adapter = new NotificationAdapter(NotificationActivity.this, list);
        recyclerView.setAdapter(adapter);
    }
}