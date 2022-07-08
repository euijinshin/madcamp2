package com.example.myapplication.activities.leave;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;

public class LeaveActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private GotFragment fragmentGot;
    private InviteFragment fragmentInvite;
    private FragmentTransaction transaction;

    private String host;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);

        Intent intent = getIntent();
        host = intent.getStringExtra("name");

        TextView hostname = findViewById(R.id.hostname);
        hostname.setText(host);

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
}
