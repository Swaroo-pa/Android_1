package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Notification extends AppCompatActivity {
    public static final String FILE_NAME = "example2.txt";
    TextView message;
    EditText text;
    Button send,load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        send = findViewById(R.id.send);
        load = findViewById(R.id.load);
        text = findViewById(R.id.text);
        message = findViewById(R.id.view);

        fileInsertion(send);
        fileDisplaying(load);



    }
    public void fileInsertion(View view){
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String input = " "+text.getText().toString();
                FileOutputStream fos = null;  // a file source for writing
                try {
                    fos = openFileOutput(FILE_NAME,MODE_APPEND); // open file for writing or create
                    fos.write(input.getBytes());
                    text.getText().clear();
                    message.setText(" ");
                    Toast.makeText(getApplicationContext(),"File saved at"+getFilesDir()+"/"+FILE_NAME,Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(fos != null){
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
    }
    public void fileDisplaying(View view){
        load.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FileInputStream fis = null; // a file source for reading data
                try {
                    fis = openFileInput(FILE_NAME);  // to open a private file for reading
                    InputStreamReader isr = new InputStreamReader(fis);  // being a bridge between bytes and characters
                    BufferedReader buffer = new BufferedReader(isr);  // reading a large data at a time
                    StringBuilder sb = new StringBuilder();
                    String output;

                    while((output = buffer.readLine()) !=null){
                        sb.append(output).append("\n");
                        message.setText(sb.toString());
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(fis != null){
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
    }
}