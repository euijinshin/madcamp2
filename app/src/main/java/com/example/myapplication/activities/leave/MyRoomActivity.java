package com.example.myapplication.activities.leave;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.main.JsonPlaceHolderApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MyRoomActivity extends AppCompatActivity {

    ArrayList<JoinMember> list;
    MyRoomAdapter adapter;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    int user_id;
    List<RoomData> qqlist;
    String roomName, imgUrl, roomDetails;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_room);


        Intent secondIntent = getIntent();
        user_id = secondIntent.getIntExtra("user_id", 0);

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.249.18.158:443/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        jsonPlaceHolderApi = retrofit.create(com.example.myapplication.main.JsonPlaceHolderApi.class);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        gets();

//        list = new ArrayList<>();
//
//        RecyclerView recyclerView = findViewById(R.id.rv_myroom);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
//
//        adapter = new MyRoomAdapter(MyRoomActivity.this, list);
//        recyclerView.setAdapter(adapter);
//
//        JoinMember joinMember1 = new JoinMember("Samuel", "5", "imgUrl");
//        JoinMember joinMember2 = new JoinMember("John", "4", "imgUrl");
//        JoinMember joinMember3 = new JoinMember("Kim", "1", "imgUrl");
//        JoinMember joinMember4 = new JoinMember("Lucy", "2", "imgUrl");
//        list.add(joinMember1);
//        list.add(joinMember2);
//        list.add(joinMember3);
//        list.add(joinMember4);
//
//        adapter.notifyDataSetChanged();
    }

    private void gets() {
        jsonPlaceHolderApi.gets(user_id).enqueue(new Callback<List<RoomData>>() {
            @Override
            public void onResponse(Call<List<RoomData>> call, Response<List<RoomData>> response) {
                if (response.isSuccessful()) {


                    Log.d("ddddd", "ddd");
                    qqlist = response.body();

                    roomName = qqlist.get(0).getRoomName();
                    roomDetails = qqlist.get(0).getRoomDetails();
                    imgUrl = qqlist.get(0).getImageUrl();


                    TextView hostname = findViewById(R.id.myroomName);
                    hostname.setText(roomName);

                    TextView details = findViewById(R.id.myroomDetails);
                    details.setText(roomDetails);

//                    ImageView img = findViewById(R.id.myr);

//                    Glide.with(MyRoomActivity.this).load(imgUrl).into(img);
                }
            }
            @Override
            public void onFailure(Call<List<RoomData>> call, Throwable t) {
                Log.d("CCCCCCCCCC", t.getMessage());
            }
        });
    }

//    private void getUser() {
//        jsonPlaceHolderApi.getId(user_id).enqueue(new Callback<List<String>>() {
//            @Override
//            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<List<String>> call, Throwable t) {
//
//            }
//        });
//    }
}