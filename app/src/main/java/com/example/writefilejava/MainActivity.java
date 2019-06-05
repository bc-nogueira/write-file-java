package com.example.writefilejava;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.writeBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                writeFile();
            }
        });
    }

    private void writeFile() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        File sdCard = Environment.getExternalStorageDirectory();// storage/emulated/0
        File file = new File(sdCard, "/Download/writeJava.txt");

        try {
            file.createNewFile();

            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append("Testando");

            myOutWriter.close();

            fOut.flush();
            fOut.close();

            TextView statusTxt = findViewById(R.id.statusTxt);
            statusTxt.setText("Done writing");
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
