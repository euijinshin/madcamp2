package com.example.myapplication.activities.leave;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class CreateRoomActivity extends AppCompatActivity {

    ImageView create_profile;
    TextView create_name;
    TextView create_details;

    TextView roomName;
    TextView exitTime;
    TextView fullMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


    }
}