package com.example.myapplication.activities.leave;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MyRoomActivity extends AppCompatActivity {

    ArrayList<JoinMember> list;
    MyRoomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_room);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        list = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rv_myroom);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new MyRoomAdapter(MyRoomActivity.this, list);
        recyclerView.setAdapter(adapter);

        JoinMember joinMember1 = new JoinMember("Samuel", "5", "imgUrl");
        JoinMember joinMember2 = new JoinMember("John", "4", "imgUrl");
        JoinMember joinMember3 = new JoinMember("Kim", "1", "imgUrl");
        JoinMember joinMember4 = new JoinMember("Lucy", "2", "imgUrl");
        list.add(joinMember1);
        list.add(joinMember2);
        list.add(joinMember3);
        list.add(joinMember4);

        adapter.notifyDataSetChanged();
    }
}