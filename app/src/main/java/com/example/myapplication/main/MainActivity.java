package com.example.myapplication.main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.activities.RuntimeActivity;
import com.example.myapplication.activities.leave.WaitRoomActivity;
import com.example.myapplication.activities.MapActivity;
import com.example.myapplication.R;
import com.example.myapplication.main.notifications.NotificationActivity;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    private JsonPlaceHolderApi jsonPlaceHolderApi;


    private String strNick, strProfileImg, strEmail, strAge, strGender;
    TextView tv_maincounter, tv_nick, tv_email, tv_details;
    ImageView iv_profile;
    Button leave_btn, map_btn, notif_btn, time_btn, logout_btn;

    private boolean running = false;
    private boolean isrunning = false;
    private boolean unbind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initIntent();
        initView();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.249.18.158:443/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(com.example.myapplication.main.JsonPlaceHolderApi.class);

        setValues();

        // 닉네임, email, details set
        tv_nick.setText(strNick);
        tv_email.setText(strEmail);
        tv_details.setText(strAge + ", " + strGender);

        //프로필 이미지 사진 set
        Glide.with(this).load(strProfileImg).into(iv_profile);



    }

    // StopWatch function
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            Boolean isStop = intent.getBooleanExtra("isStopSW", false);
            long time = intent.getLongExtra("today", 0);

            if (isStop) {
                Date pass = new Date(time);
                SimpleDateFormat passFormat = new SimpleDateFormat("hh:mm:ss");
                String getTime = passFormat.format(pass);

                TextView textView = findViewById(R.id.tv_mainCounter);
                textView.setText(getTime);

                LinearLayout linearLayout = findViewById(R.id.linear_worktime);
                linearLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    public void initView() {
        // Buttons
        leave_btn = (Button) findViewById(R.id.leave_btn);
        map_btn = findViewById(R.id.map_btn);
        notif_btn = findViewById(R.id.notif_btn);
        time_btn = findViewById(R.id.work_btn);
        logout_btn = findViewById(R.id.logout_btn);
        //profile set
        tv_nick = findViewById(R.id.tv_nickname);
        tv_email = findViewById(R.id.tv_email);
        tv_details = findViewById(R.id.details);
        iv_profile = findViewById(R.id.iv_profile);
        tv_maincounter = findViewById(R.id.tv_mainCounter);
    }

    public void initIntent() {
        Intent intent = getIntent();
        // Profile get from kakao
        strNick = intent.getStringExtra("name");
        strProfileImg = intent.getStringExtra("profileImg");
        strEmail = intent.getStringExtra("email");
        strGender = intent.getStringExtra("gender");
        strAge = intent.getStringExtra("age");
    }

    public void setValues() {
        // set button OnClick
        leave_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), WaitRoomActivity.class);
                startActivity(intent);
            }
        });

        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });

        notif_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RuntimeActivity.class);
                startActivityForResult(intent, 1111);
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        //로그아웃 성공시 수행하는 지점
                        finish(); //현재 액티비티 종료
                    }
                });
            }
        });
    }


//    private void createPosts() {
//        Post post = new Post(strAge, strGender, strNick);
//
//        Call<String> call = jsonPlaceHolderApi.createPosts(post);
//
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (!response.isSuccessful()) {
//                    text.setText("실패 2 서버 에러");
//                }
//                if(response.body() == null)
//                    return;
//                String success = response.body().toString();
//                text.setText(success);
//
//            }
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                text.setText("실패 3 서버 에러");
//            }
//        });
//    }
//
//
//    private void getPosts() {
//
//
//        Post post = new Post();
//        jsonPlaceHolderApi.getPosts(strNick).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        String success = response.body().toString();
//                        text.setText(success);
//                    } else {
//                        text.setText("실패 1 response 내용이 없음");
//                    }
//                } else {
//                    text.setText("실패 2 서버 에러");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                text.setText("실패 3 서버 에러");
//            }
//        });
//    }
}

