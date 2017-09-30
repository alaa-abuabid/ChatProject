package com.example.alaa_ab.chatproject;

import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText text;
    Button button;
    public static Socket socket;
    public static PrintWriter pr;
    String msg;
    public static String ip="192.168.43.106";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(EditText)findViewById(R.id.msg);
        button= (Button)findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                msg= text.getText().toString();
                MyTask myTask = new MyTask();
                myTask.execute();
            }
        });
    }


    class MyTask extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... params) {
            try{
                socket= new Socket(ip,8080);
                pr= new PrintWriter(socket.getOutputStream());
                pr.write(msg);
                pr.flush();
                pr.close();
                socket.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }




            return null ;
        }
    }




}
