package com.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.prototype.Express.R;

public class OrderReceivedActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_received);

        // WAIT
        Thread timerThread = new Thread()
        {
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    open_MainActivity();
                }
            }
        };
        timerThread.start();

    }

    // INTENTS
    private void open_MainActivity()
    {
        Intent intent = new Intent(OrderReceivedActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
