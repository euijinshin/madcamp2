package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class WorkTimeService extends Service {

    public WorkTimeService() {
    }
    private int count;
    private boolean start;

    IMyCounterService.Stub binder = new IMyCounterService.Stub() {
        @Override
        public int getCount() throws RemoteException {
            return count;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        isStop = true;
        return super.onUnbind(intent);
    }

    private boolean isStop;

    @Override
    public void onDestroy() {
        super.onDestroy();
        isStop = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        start = intent.getBooleanExtra("start", false);
        Thread counter = new Thread(new Counter());
        if (start) {
            count = 0;
            counter.start();
        };

        return startId;
    }

    private class Counter implements Runnable {
        private Handler handler = new Handler();

        @Override
        public void run() {
            while (!isStop) {
                if (!start) break;
                count ++;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getApplicationContext(), count + "", Toast.LENGTH_SHORT).show();
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "서비스 종료", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
