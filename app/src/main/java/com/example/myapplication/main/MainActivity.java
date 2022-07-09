package com.example.myapplication.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.IMyCounterService;
import com.example.myapplication.TimeRunActivity;
import com.example.myapplication.WorkTimeService;
import com.example.myapplication.activities.leave.WaitRoomActivity;
import com.example.myapplication.activities.login.LogInActivity;
import com.example.myapplication.activities.MapActivity;
import com.example.myapplication.R;
import com.example.myapplication.main.notifications.NotificationActivity;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    private JsonPlaceHolderApi jsonPlaceHolderApi;


    private String strNick, strProfileImg, strEmail, strAge, strGender;
    TextView tv_maincounter;
    private int start_time;
    private int time;
    private NotificationHelper mNotificationhelper;

    private boolean running = false;
    private boolean isrunning = false;
    private boolean unbind = false;

    private IMyCounterService binder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            binder = IMyCounterService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button leave_btn = (Button) findViewById(R.id.leave_btn);
        Button map_btn = findViewById(R.id.map_btn);
        Button work_btn = findViewById(R.id.work_btn);
        Button login_btn = findViewById(R.id.login_btn);
        Button notif_btn = findViewById(R.id.notif_btn);

        Button send_notif = findViewById(R.id.send_notif);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.10.18.158:443/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);



        send_notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = strNick;
                String message = strEmail;
                sendOnChannel1(title, message);
            }
        });

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

        work_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TimeRunActivity.class);
                if (time != 0) intent.putExtra("running", true);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
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

        Intent intent = getIntent();
        strNick = intent.getStringExtra("name");
        strProfileImg = intent.getStringExtra("profileImg");
        strEmail = intent.getStringExtra("email");
        strGender = intent.getStringExtra("gender");
        strAge = intent.getStringExtra("age");

        start_time = intent.getIntExtra("time", 0);
        running = intent.getBooleanExtra("running", false);
        unbind = intent.getBooleanExtra("unbind", false);




        Intent service = new Intent(MainActivity.this, WorkTimeService.class);
        bindService(service, connection, BIND_AUTO_CREATE);
        if (unbind) unbindService(connection);

        TextView tv_nick = findViewById(R.id.tv_nickname);
        TextView tv_email = findViewById(R.id.tv_email);
        TextView details = findViewById(R.id.details);
        ImageView iv_profile = findViewById(R.id.iv_profile);
        tv_maincounter = findViewById(R.id.tv_mainCounter);

        // 닉네임 set
        tv_nick.setText(strNick);
        // email set
        tv_email.setText(strEmail);
        // details set
        details.setText(strAge + ", " + strGender);

        //프로필 이미지 사진 set
        Glide.with(this).load(strProfileImg).into(iv_profile);

        findViewById(R.id.logout_btn).setOnClickListener(new View.OnClickListener() {
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


        final Runnable runnable = new Runnable() {
            @Override
            public synchronized void run() {
                try {
                    time = binder.getCount();
                    String time_pass = Integer.toString(time / 3600) + ":" + Integer.toString(time / 60 % 60) + ":" + Integer.toString(time % 60);
                    tv_maincounter.setText(time_pass);

                    Intent frommain = new Intent(MainActivity.this, TimeRunActivity.class);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };

        class NewRunnable implements Runnable {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(runnable);
                }
            }
        }

        NewRunnable nr = new NewRunnable();
        Thread t = new Thread(nr);
        t.start();


    }

    public void sendOnChannel1(String title, String message) {
        NotificationCompat.Builder nb = mNotificationhelper.getChannel1Notification(title, message);
        mNotificationhelper.getManager().notify(1, nb.build());
    }

    private void createPost() {
        Post post = new Post(strGender, strAge, strNick);

        Call<Post> call = jsonPlaceHolderApi.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post postResponse = (Post) response.body();

                String content = "";
                content += "Name" + postResponse.getName() + "\n";
                content += "Age: " + postResponse.getAge() + "\n";
                content += "Score: " + postResponse.getScore() + "\n";

                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }


    private void getPosts() {


        Post post = new Post();

        Call<Post> call = jsonPlaceHolderApi.getPosts(strNick);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                TextView textView = (TextView) findViewById(R.id.details);

                String content = "";
                content += "Name" + post.getName() + "\n";
                content += "Age: " + post.getAge() + "\n";
                content += "Gender: " + post.getScore() + "\n";

                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }
}

