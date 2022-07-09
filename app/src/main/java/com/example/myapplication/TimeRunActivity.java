package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimeRunActivity extends AppCompatActivity {

    private TextView tvCounter;
    private Button btnPlay, btnStop;

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

    private Intent intent;
    private boolean running = true;

    public class GetCountThread implements Runnable {
        private Handler handler = new Handler();

        @Override
        public void run() {
            while (running) {
                if (binder == null) continue;

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            tvCounter.setText(binder.getCount() + "");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        tvCounter = (TextView) findViewById(R.id.tvCounter);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimeRunActivity.this, WorkTimeService.class);
//                startService(intent);
                bindService(intent, connection, BIND_AUTO_CREATE);
                running = true;
                new Thread(new GetCountThread()).start();
            }
        });

        btnStop = (Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(connection);
                running = false;
            }
        });
    }
}