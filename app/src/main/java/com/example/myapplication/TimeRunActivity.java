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

import com.example.myapplication.main.MainActivity;

public class TimeRunActivity extends AppCompatActivity {

    private TextView tvCounter;
    private Button btnPlay;
    private Button btnStop;

    private Thread thread;

    private IMyCounterService binder;

    private int start_time;

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
    private boolean running = false;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        Intent frommain = getIntent();
        start_time = frommain.getIntExtra("time", 0);
        running = frommain.getBooleanExtra("running", false);

        tvCounter = (TextView) findViewById(R.id.tvCounter);
        btnPlay = (Button) findViewById(R.id.btnPlay);

        if (running) btnPlay.setVisibility(View.GONE);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimeRunActivity.this, WorkTimeService.class);
                intent.putExtra("start", true);
                bindService(intent, connection, BIND_AUTO_CREATE);
                startService(intent);
                running = true;
                new Thread(new GetCountThread()).start();

                Intent tomain = new Intent(TimeRunActivity.this, MainActivity.class);
                tomain.putExtra("running", true);

                btnPlay.setVisibility(View.GONE);
                btnStop.setVisibility(View.VISIBLE);
            }
        });

        btnStop = (Button) findViewById(R.id.btnStop);

        if (running) btnStop.setVisibility(View.VISIBLE);

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimeRunActivity.this, WorkTimeService.class);
                intent.putExtra("start", false);
                startService(intent);

                stopService(intent);

                unbindService(connection);

                Intent tomain = new Intent(TimeRunActivity.this, MainActivity.class);
                tomain.putExtra("unbind", true);

                btnPlay.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.GONE);

                running = false;
            }
        });

        Intent intent = new Intent(TimeRunActivity.this, WorkTimeService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);

        final Runnable runnable = new Runnable() {
            @Override
            public synchronized void run() {
                try {
                    int time = binder.getCount();
                    String time_pass = Integer.toString(time / 3600) + ":" + Integer.toString(time / 60 % 60) + ":" + Integer.toString(time % 60);
                    tvCounter.setText(time_pass);
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
                        Thread.sleep(500);
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

    private class GetCountThread implements Runnable {
//        private Handler handler = new Handler();

        @Override
        public synchronized void run() {
            while (running) {
                if (binder == null) {
                    continue;
                }

                final Runnable runnable = new Runnable() {
                    @Override
                    public synchronized void run() {
                        try {
                            int time =  binder.getCount();
                            String time_pass = Integer.toString(time / 3600) + ":" + Integer.toString(time / 60 % 60) + ":" + Integer.toString(time % 60);
                            tvCounter.setText(time_pass);
                        }catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                } ;

                handler.post(runnable);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}