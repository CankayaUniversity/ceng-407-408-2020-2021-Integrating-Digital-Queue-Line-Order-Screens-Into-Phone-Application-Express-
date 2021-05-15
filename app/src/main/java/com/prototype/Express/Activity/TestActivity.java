package com.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Observable;
import android.os.Bundle;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;
import com.prototype.Express.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class TestActivity extends AppCompatActivity
{
    // XML
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // XML
        display = findViewById(R.id.display);

        // USER TOKEN
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("user_token", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        try {
            IO.Options mOptions = new IO.Options();
            mOptions.query = "auth_token=" + token;
            Socket msocket = IO.socket("http://104.248.207.133:5000", mOptions);
            msocket.on("success", onNewMessage);
            msocket.connect();
        } catch (URISyntaxException e) {
            System.out.print(e);
        }


        // CONNECT
        //socket.connect();

        // EMIT DATA
        //sendMessage();
    }

    public void sendMessage()
    {
        //socket.emit("phone-send", "can");
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    JSONObject user;
                    String name;
                    try {
                        user = data.getJSONObject("user");
                        name = user.getString("name");
                    } catch (JSONException e)
                    {
                        System.out.println(e);
                        return;
                    }

                    display.append(name);
                }
            });
        }
    };
}
