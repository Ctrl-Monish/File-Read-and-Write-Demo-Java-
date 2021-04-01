package com.internship.fileread_writedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btnWrite,btnRead;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        btnRead = findViewById(R.id.buttonread);
        btnWrite = findViewById(R.id.buttonwrite);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();

                File dataDirectory = ContextCompat.getDataDir(MainActivity.this);
                File myFile = new File(dataDirectory, "file.txt");

                try {
                    FileOutputStream fos = new FileOutputStream(myFile, true);
                    fos.write(text.getBytes());
                } catch (FileNotFoundException e) {
                    Toast.makeText(MainActivity.this,"File not found",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this,"Error while writing file",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                File dataDirectory = ContextCompat.getDataDir(MainActivity.this);
                File myFile = new File(dataDirectory, "file.txt");

                try {
                    FileInputStream fis = new FileInputStream(myFile);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);

                    StringBuilder sb = new StringBuilder();
                    String buffer = br.readLine();
                    while (buffer!=null){
                        sb.append(buffer);
                        buffer = br.readLine();
                    }

                    String text = sb.toString();
                    textView.setText(text);
                } catch (FileNotFoundException e) {
                    Toast.makeText(MainActivity.this,"File not found",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this,"Error while reading file",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}