package ru.job4j.backgroundthread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "Main Activity";
    private volatile boolean stopThread = false;
    private Handler mainHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startThread(View view) {
//        TestThread thread = new TestThread(10);
//        thread.start();
        stopThread = false;
        TestRunnable runnable = new TestRunnable(10);
        new Thread(runnable).start();
    }

    public void loadImage(View view){
        LoadImage load = new LoadImage();
        new Thread(load).start();
    }

    public void stopThread(View view) {
        this.stopThread = true;
        Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show();
    }

    public class LoadImage implements Runnable {
        @Override
        public void run() {
            final Bitmap bitmap =
                    loadImageFromNetwork("http://belgorod.expert/wp-content/uploads/2019/01/img_504589.png");
            final ImageView imageView = findViewById(R.id.picture);
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                }
            });
        }
    }

    public class TestRunnable implements Runnable {
        private int times;

        TextView textView = findViewById(R.id.textView);

        public TestRunnable(int times) {
            this.times = times;
        }

        @Override
        public void run() {
            int count = 0;
            Handler threadHandler = new Handler(Looper.getMainLooper());
            while (!stopThread && count != this.times) {
                if (count == 5) {
//                    threadHandler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            textView.setText("50%");
//                        }
//                    });
//                    textView.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            textView.setText("50%");
//                        }
//                    });
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            textView.setText("50%");
                        }
                    });
                }
                Log.d(MainActivity.TAG, "startThread: " + count);
                count++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private Bitmap loadImageFromNetwork(String url) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory
                    .decodeStream((InputStream) new URL(url)
                            .getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
