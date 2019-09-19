package ru.job4j.backgroundthread;

import android.util.Log;

//public class TestRunnable implements Runnable{
//    private int times;
//    private volatile boolean stopThread;
//
//    public TestRunnable(int times, boolean stopThread){
//        this.times = times;
//        this.stopThread = stopThread;
//    }
//
//    @Override
//    public void run(){
//        int count = 0;
//        while (!stopThread && count != this.times){
//            Log.d(MainActivity.TAG, "startThread: " + count);
//            count++;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e){
//                e.printStackTrace();
//            }
//
//        }
//    }
//}
