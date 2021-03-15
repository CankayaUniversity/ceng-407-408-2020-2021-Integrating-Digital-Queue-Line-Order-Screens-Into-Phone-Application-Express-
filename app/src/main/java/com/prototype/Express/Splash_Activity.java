package com.prototype.Express;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.system.ErrnoException;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.InflaterInputStream;


public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        // TODO POST-GET TEST
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String url_address = "http://104.248.207.133:5000/api/v1/project-info";

        try
        {
            StringBuffer response = new StringBuffer();
            URL url = new URL(url_address);
            URLConnection connection = url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);

            /*
            Gson gson = new Gson();
            String message = "hello";
            message = gson.toJson(message);
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(message);
            wr.flush();
            */

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            int data = inputStreamReader.read();
            while (data != -1) {
                char current = (char) data;
                data = inputStreamReader.read();
                response.append(current);
            }
            String responseJSON = response.toString();
            Toast.makeText(this, responseJSON, Toast.LENGTH_SHORT).show();


        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }








        // SPLASH SCREEN
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(Splash_Activity.this, Login.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }


}
