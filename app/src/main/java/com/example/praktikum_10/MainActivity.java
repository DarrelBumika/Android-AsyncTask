package com.example.praktikum_10;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import java.net.URL;

public class MainActivity extends Activity {

    private Button downloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadButton = findViewById(R.id.button);

        downloadButton.setOnClickListener(v -> {
            try {
                URL url1 = new URL("https://file-examples.com/wp-content/uploads/2018/04/file_example_MP4_480_1_5MG.mp4");

                new DownloadFilesTask(this).execute(url1);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

