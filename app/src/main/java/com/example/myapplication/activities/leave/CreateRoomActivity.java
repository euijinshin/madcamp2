package com.example.myapplication.activities.leave;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.io.IOException;

public class CreateRoomActivity extends AppCompatActivity {

    ImageView create_profile;
    TextView create_name, create_details; // user name and details
    TextView roomName, exitTime, fullMember; // room details
    TextView createConfirm, createCancel; // buttons

    public static RoomData roomData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initView();

//        create_profile.setImageResource();
//        create_name.setText();
//        create_details.setText();

        createConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String details = exitTime.getText().toString() + " " + fullMember.getText().toString();
//                roomData = new RoomData("url", roomName.getText().toString(), details);
                Intent intent = new Intent();
//                intent.putExtra("roomData", roomData);
                intent.putExtra("image", "url");
                intent.putExtra("roomName", roomName.getText().toString());
                intent.putExtra("details", details);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        createCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void initView() {
        create_profile = findViewById(R.id.create_profile);
        create_name = findViewById(R.id.create_name);
        create_details = findViewById(R.id.create_details);

        roomName = findViewById(R.id.roomName);
        exitTime = findViewById(R.id.exitTime);
        fullMember = findViewById(R.id.fullMember);

        createConfirm = findViewById(R.id.createConfirm);
        createCancel = findViewById(R.id.createCancel);
    }
}