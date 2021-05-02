package com.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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

    // VARIABLES


    private Socket socket;
    {
        try {
            socket = IO.socket("http://104.248.207.133:5000");
        } catch (URISyntaxException e) {}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // XML
        display = findViewById(R.id.display);

        socket.on("user", onNewMessage);


        try {
            IO.Options mOptions = new IO.Options();
            String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYwOGYwODlkMmMwOGE5OWY2ZjNjNmY2ZCIsImlhdCI6MTYxOTk4NjU4OSwiZXhwIjoxNjIyNTc4NTg5fQ.lwO_ZRr4EOW0vQYNKlsgjFapOrEIBS3Auzx804jSu6g";
            mOptions.query = "auth_token=" + token;
            Socket msocket = IO.socket("http://104.248.207.133:5000", mOptions);
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
        socket.emit("pc-send", "can");
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String message;
                    try {
                        message = data.getString("data");
                    } catch (JSONException e) {
                        return;
                    }

                    display.setText(message);
                }
            });
        }
    };
}
