package com.example.myapplication.activities.leave;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class WaitRoomActivity extends AppCompatActivity {

    private FloatingActionButton add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_room);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ArrayList<String> list = new ArrayList<>();

        list.add("Samuel");

        RecyclerView recyclerView = findViewById(R.id.rvWaitRoom);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        WaitRoomAdapter adapter = new WaitRoomAdapter(WaitRoomActivity.this, list);
        recyclerView.setAdapter(adapter);

        add_btn = (FloatingActionButton) findViewById(R.id.create_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaitRoomActivity.this, CreateRoomActivity.class);
                startActivity(intent);
            }
        });
    }
}