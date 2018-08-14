package com.example.administrator.executortest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    String TAG = "executortest";
    ExecutorService fixedThreadPool, singleThreadPool, cachedThreadPool;
    ScheduledExecutorService scheduledThreadPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.newFixedThreadPool).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fixedThreadPool = Executors.newFixedThreadPool(3);
                for (int i = 0; i < 10; i++) {
                    final int index = i;
                    fixedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            String threadName = Thread.currentThread().getName();
                            Log.v(TAG, "线程：" + threadName + ",正在执行第" + index + "个任务");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            }
        });

        findViewById(R.id.newSingleThreadExecutor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleThreadPool = Executors.newSingleThreadExecutor();
                for (int i = 0; i < 10; i++) {
                    final int index = i;
                    singleThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            String threadName = Thread.currentThread().getName();
                            Log.v(TAG, "线程：" + threadName + ",正在执行第" + index + "个任务");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            }
        });

        findViewById(R.id.newCachedThreadPool).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cachedThreadPool = Executors.newCachedThreadPool();
                for (int i = 0; i < 10; i++) {
                    final int index = i;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cachedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            String threadName = Thread.currentThread().getName();
                            Log.v(TAG, "线程：" + threadName + ",正在执行第" + index + "个任务");
                            try {
                                long time = index * 500;
                                Thread.sleep(time);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            }
        });

        findViewById(R.id.newScheduledThreadPool).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduledThreadPool = Executors.newScheduledThreadPool(3);
                scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        String threadName = Thread.currentThread().getName();
                        Log.v(TAG, "线程：" + threadName + ",正在执行");
                    }
                }, 2, 1, TimeUnit.SECONDS);
            }
        });

        findViewById(R.id.shutdown).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fixedThreadPool.shutdownNow();
                singleThreadPool.shutdownNow();
                cachedThreadPool.shutdownNow();
                scheduledThreadPool.shutdownNow();
            }
        });
    }
}
