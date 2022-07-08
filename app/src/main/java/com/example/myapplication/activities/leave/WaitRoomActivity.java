package com.example.myapplication.activities.leave;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Random;

public class WaitRoomActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private RecyclerView recyclerView;
    WaitRoomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.myapplication.R.layout.activity_wait_room);

        items = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rvWaitRoom);
        recyclerView.setLayoutManager(new LinearLayoutManager(WaitRoomActivity.this));

        adapter = new WaitRoomAdapter(WaitRoomActivity.this, items);
        recyclerView.setAdapter(adapter);

    }
}