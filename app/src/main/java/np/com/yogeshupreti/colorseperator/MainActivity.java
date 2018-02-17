package np.com.yogeshupreti.colorseperator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {
    Camera camera;
    CameraManager camManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent =new Intent(MainActivity.this,Quiz.class);
        startActivity(intent);
    }

}
