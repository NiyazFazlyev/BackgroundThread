package ru.job4j.backgroundthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "Main Activity";
    private boolean stopThread = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startThread(View view){
//        TestThread thread = new TestThread(10);
//        thread.start();
        stopThread = false;
        TestRunnable runnable = new TestRunnable(10);
        new Thread(runnable).start();
    }

    public void stopThread(View view){
        this.stopThread = true;
        Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show();
    }

    public class TestRunnable implements Runnable{
        private int times;

        public TestRunnable(int times){
            this.times = times;
        }

        @Override
        public void run(){
            int count = 0;
            while (!stopThread && count != this.times){
                Log.d(MainActivity.TAG, "startThread: " + count);
                count++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }
    }
}
