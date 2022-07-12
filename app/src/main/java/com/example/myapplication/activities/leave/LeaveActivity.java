package com.example.myapplication.activities.leave;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

public class LeaveActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private GotFragment fragmentGot;
    private InviteFragment fragmentInvite;
    private FragmentTransaction transaction;

    private String host;
    private String roomDetail;
    private String imageUrl;

    int roomId, user_id;

    private JsonPlaceHolderApi jsonPlaceHolderApi;


    List<RoomData> qqlist;
    ArrayList<Integer> otherID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);

//        posts();
          gets();


        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.249.18.158:443/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        jsonPlaceHolderApi = retrofit.create(com.example.myapplication.main.JsonPlaceHolderApi.class);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
//        host = intent.getStringExtra("name");
//        roomDetail = intent.getStringExtra("details");
//        imageUrl = intent.getStringExtra("image");
        roomId = intent.getIntExtra("roomId", 0);
        user_id = intent.getIntExtra("user_id", 0);

        gets();

        fragmentManager = getSupportFragmentManager();

        fragmentGot = new GotFragment();
        fragmentInvite = new InviteFragment();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragmentGot).commitAllowingStateLoss();
    }

    public void clickHandler(View view)
    {
        transaction = fragmentManager.beginTransaction();

        switch(view.getId())
        {
            case R.id.leave:
                transaction.replace(R.id.container, fragmentGot).commitAllowingStateLoss();
                break;
            case R.id.invite:
                transaction.replace(R.id.container, fragmentInvite).commitAllowingStateLoss();
                break;
        }
    }

    private void gets() {
        jsonPlaceHolderApi.getMyroom(roomId).enqueue(new Callback<List<RoomData>>() {
            @Override
            public void onResponse(Call<List<RoomData>> call, Response<List<RoomData>> response) {
                if(response.isSuccessful()) {
                    Log.d("ddddd", "ddd");
                    qqlist = response.body();

                    host = qqlist.get(0).getRoomName();
                    roomDetail = qqlist.get(0).getRoomDetails();
                    imageUrl = qqlist.get(0).getImageUrl();


                    TextView hostname = findViewById(R.id.hostname);
                    hostname.setText(host);

                    TextView details = findViewById(R.id.leave_details);
                    details.setText(roomDetail);

                    ImageView img = findViewById(R.id.leave_img);

                    Glide.with(LeaveActivity.this).load(imageUrl).into(img);


                }
                else {
                    Log.d("sss", "sss");
                }
            }

            @Override
            public void onFailure(Call<List<RoomData>> call, Throwable t) {
                Log.d("CCCCCCCCCC", t.getMessage());
            }
        });
    }

//    private void posts() {
//
//        RoomData roomData = new RoomData(user_id);
//
//        Call<String> call = jsonPlaceHolderApi.posts(roomData);
//
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful()) {
//                    Log.d("SSSSSSSS", "Success");
//                }
//                if(response.body() == null)
//                    return;
//            }
//            @Override
//            public void onFailure(Call<String> call, Throwable t){
//                Log.d("SSSSSSSS", "Failure");
//            }
//        });
//    }
}
