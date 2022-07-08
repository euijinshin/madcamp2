package com.example.myapplication.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.activities.leave.LeaveActivity;
import com.example.myapplication.activities.leave.WaitRoomActivity;
import com.example.myapplication.activities.login.LogInActivity;
import com.example.myapplication.activities.MapActivity;
import com.example.myapplication.R;
import com.example.myapplication.activities.WorktimeActivity;
import com.example.myapplication.main.notifications.NotificationActivity;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;


public class MainActivity extends AppCompatActivity {

    private String strNick, strProfileImg, strEmail;
    private NotificationHelper mNotificationhelper;

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
                Intent intent = new Intent(getApplicationContext(), WorktimeActivity.class);
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

        TextView tv_nick = findViewById(R.id.tv_nickname);
        TextView tv_email = findViewById(R.id.tv_email);
        ImageView iv_profile = findViewById(R.id.iv_profile);

        // 닉네임 set
        tv_nick.setText(strNick);
        //
        tv_email.setText(strEmail);

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
    }

    public void sendOnChannel1(String title, String message) {
        NotificationCompat.Builder nb = mNotificationhelper.getChannel1Notification(title, message);
        mNotificationhelper.getManager().notify(1, nb.build());
    }
}
