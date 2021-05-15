package com.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.prototype.Express.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class OrderReceivedActivity extends AppCompatActivity
{
    // SOCKET CLASS
    private Socket socket;
    {
        try {
            socket = IO.socket("http://104.248.207.133:5000");
        } catch (URISyntaxException e) {}
    }

    // XML
    TextView text_message, textView_status;

    // NOTIFICATION
    String CHANNEL_ID = "Order Ready Receiver";
    String contentTitle = "Siparişiniz Hazırlandı!";
    String contentTextBase = " siparişiniz sizi bekliyor! Gelip alabilirsiniz.";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_received);

        // XML
        text_message = findViewById(R.id.text_message);
        textView_status = findViewById(R.id.textView_status);
        textView_status.setText("Siparişiniz Hazırlanıyor...");
        textView_status.setVisibility(View.INVISIBLE);

        // SOCKET CHANNEL
        socket.on("phone-receive", onNewMessage);

        // USER TOKEN
        SharedPreferences sharedPreferences = this.getSharedPreferences("user_token", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        // SOCKET CONNECT
        try {
            IO.Options mOptions = new IO.Options();
            mOptions.query = "auth_token=" + token;
            Socket msocket = IO.socket("http://104.248.207.133:5000", mOptions);
            msocket.connect();
            Toast.makeText(this, "Socket good to go!", Toast.LENGTH_SHORT).show();
        } catch (URISyntaxException e) {
            System.out.print(e);
        }

        // NOTIFICATION CREATE FUNC. CALL
        createNotificationChannel();

        /*
        // CREATE NOTIFICATION
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.picture)
                .setContentTitle(contentTitle)
                .setContentText(contentTextBase)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        // TODO SEND NOTIFICATION
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, builder.build());

         */

        /*
        // THREAD_WAIT
        Thread timerThread = new Thread()
        {
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    //update();
                }
            }
        };
        timerThread.start();
         */
    }

    // VISUAL UPDATE
    private void update()
    {
        text_message.setVisibility(View.INVISIBLE);
        textView_status.setVisibility(View.VISIBLE);
    }

    // CREATE NOTIFICATION CHANNEL
    private void createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence name = "Order Ready";
            String description = "This notification fires itself once your order is ready to pick";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // INTENTS
    private void open_MainActivity()
    {
        Intent intent = new Intent(OrderReceivedActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // LISTEN SOCKET
    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String name;
                    try {
                        name = data.getString("name");
                    } catch (JSONException e) {
                        return;
                    }
                    System.out.println("\n\n\n Socket: " + name);
                    textView_status.setText(name);

                    // CREATE NOTIFICATION
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                            .setSmallIcon(R.drawable.picture)
                            .setContentTitle(contentTitle)
                            .setContentText(name + contentTextBase)
                            .setPriority(NotificationCompat.PRIORITY_HIGH);

                    // TODO SEND NOTIFICATION
                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                    notificationManagerCompat.notify(1, builder.build());
                }
            });
        }
    };
}
