package com.example.myapplication.activities.leave;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
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
    String strUrl, strName, strDetails;
    ArrayList<RoomData> list;
    WaitRoomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_room);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        list = new ArrayList<>();

//        RoomData roomData = new RoomData("url", "Samuel", "9:00");
//        list.add(roomData);

        RecyclerView recyclerView = findViewById(R.id.rvWaitRoom);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new WaitRoomAdapter(WaitRoomActivity.this, list);
        recyclerView.setAdapter(adapter);

        add_btn = (FloatingActionButton) findViewById(R.id.create_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaitRoomActivity.this, CreateRoomActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, 1234);
            }
        });

        Button myroom = findViewById(R.id.myroom);
        myroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaitRoomActivity.this, MyRoomActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            strUrl = intent.getStringExtra("image");
            strName = intent.getStringExtra("roomName");
            strDetails = intent.getStringExtra("details");

            if (intent != null) {
                RoomData roomData2 = new RoomData(strUrl, strName, strDetails);
                list.add(roomData2);
            }
            adapter.notifyDataSetChanged();
        }
    }
//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        if (resultCode != RESULT_OK) {
//            return;
//        }
//        else {
//            strUrl = intent.getStringExtra("image");
//            strName = intent.getStringExtra("roomName");
//            strDetails = intent.getStringExtra("details");
//
//            if (intent != null) {
//                RoomData roomData2 = new RoomData(strUrl, strName, strDetails);
//                list.add(roomData2);
//            }
//        }
//    }
}