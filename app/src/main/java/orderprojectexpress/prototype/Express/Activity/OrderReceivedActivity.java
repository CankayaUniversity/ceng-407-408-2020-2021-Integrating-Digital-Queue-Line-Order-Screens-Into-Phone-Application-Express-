package orderprojectexpress.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.prototype.Express.R;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import orderprojectexpress.prototype.Express.Adapter.GivenOrdersAdapter;
import orderprojectexpress.prototype.Express.Adapter.ProfileAdapter;
import orderprojectexpress.prototype.Express.Class.Item;



public class OrderReceivedActivity extends AppCompatActivity
{
    // XML
    TextView text_message, text_message2;
    ImageView image_message;
    RecyclerView rv;
    ProgressBar progressBar;

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
        image_message = findViewById(R.id.image_message);
        text_message2 = findViewById(R.id.text_message2);
        rv = findViewById(R.id.rv);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);
        text_message2.setVisibility(View.INVISIBLE);

        // USER TOKEN
        SharedPreferences sharedPreferences = this.getSharedPreferences("user_token", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        // SOCKET CONNECT
        try {
            IO.Options mOptions = new IO.Options();
            mOptions.query = "auth_token=" + token;
            Socket msocket = IO.socket("http://104.248.207.133:5000", mOptions);
            // SOCKET CHANNEL
            msocket.on("phone-receive", onNewMessage);
            msocket.connect();
        } catch (URISyntaxException e) {
            System.out.print(e);
        }

        // NOTIFICATION CREATE FUNC. CALL
        createNotificationChannel();

        receiveData();

    }

    // FUNCTIONS
    private void receiveData()
    {
        // VOLLEY
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);


        // DATASET
        final ArrayList<Item> mData;
        mData = new ArrayList<>();


        // URL BASE
        String url_base = "http://104.248.207.133:5000/api/v1/orders?user=";
        // USER ID
        SharedPreferences sharedPreferences = this.getSharedPreferences("user_id", MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");
        // COMPLETE URL
        String url = url_base + id;

        // VOLLEY
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                try
                {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject order = jsonArray.getJSONObject(i);

                        //  PREPARING ORDERS
                        if(order.getInt("isFinished") == 0)
                        {
                            Item item = new Item();
                            item.setName(order.getString("name"));

                            JSONObject order_inner = order.getJSONObject("menuItem");
                            item.setPhoto(order_inner.getString("photo"));
                            item.setDescription(order_inner.getString("description"));
                            item.setPrice(order_inner.getDouble("price"));
                            item.setCreatedAt(order_inner.getString("createdAt"));

                           mData.add(item);
                        }
                    }

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);

        // WAIT FOR ASYNC VOLLEY TO FINISH
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            public void run()
            {
                progressBar.setVisibility(View.INVISIBLE);

                // DATASET INITIALIZE
                Collections.reverse(mData);
                rv.setHasFixedSize(true);
                final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                rv.setLayoutManager(linearLayoutManager);
                GivenOrdersAdapter givenOrdersAdapter;
                givenOrdersAdapter = new GivenOrdersAdapter(getApplicationContext(), mData);
                rv.setAdapter(givenOrdersAdapter);
                givenOrdersAdapter.notifyDataSetChanged();
            }
        }, 5000);   // 5 second
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

                    // CREATE NOTIFICATION
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                            .setSmallIcon(R.drawable.logot)
                            .setContentTitle(contentTitle)
                            .setContentText(name + contentTextBase)
                            .setPriority(NotificationCompat.PRIORITY_HIGH);

                    // TODO SEND NOTIFICATION
                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                    notificationManagerCompat.notify(1, builder.build());

                    text_message.setVisibility(View.INVISIBLE);
                    text_message2.setVisibility(View.VISIBLE);
                }
            });
        }
    };
}
