package orderprojectexpress.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.prototype.Express.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity
{
    // VARIABLES
    String user_email;
    String user_password;

    // XML
    EditText XML_email;
    EditText XML_password;
    Button Button_Login;
    TextView textView4;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // CASTING TO XML
        XML_email = findViewById(R.id.XML_email);
        XML_password = findViewById(R.id.XML_password);
        Button_Login = findViewById(R.id.Button_Login);
        textView4 = findViewById(R.id.textView4);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

         // IF USER HAVE AN ALREADY ACCOUNT & WANT TO LOGIN UNTIL DATABASE INTEGRATION
        Button_Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // INPUT
                user_email = XML_email.getText().toString().trim();
                user_password = XML_password.getText().toString().trim();

                Button_Login.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                loginUser(user_email, user_password);
            }
        });


        // IF USER PRESS TO_REGISTER
        textView4.setOnClickListener(new View.OnClickListener()
        {
           @Override
           public void onClick(View v)
           {
               openActivity_Register();
           }
        });
    }

    //INTENTs
    public void openActivity_Register()
    {
        Intent intent4 = new Intent(this, RegisterActivity.class);
        startActivity(intent4);
    }

    public void open_MainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void loginUser(String user_email, String user_password)
    {
        final String url = "http://104.248.207.133:5000/api/v1/auth/login";

        Map<String, String> params = new HashMap();
        params.put("email", user_email);
        params.put("password", user_password);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                //TODO: handle success
                try
                {
                    String response_login = response.getString("success");
                    System.out.print("\n\n\n" + response_login+ "\n\n\n");

                    // REGISTER SUCCESSFUL
                    if(response_login == "true")
                    {
                        String token = response.getString("token");
                        String id = response.getString("id");
                        System.out.print("\n\n\n" + token + "\n\n\n");

                        // USER TOKEN
                        SharedPreferences user_token = getSharedPreferences("user_token", MODE_PRIVATE);
                        SharedPreferences.Editor editor = user_token.edit();
                        editor.putString("token", token);
                        editor.apply();

                        // TODO USER ID
                        SharedPreferences user_id = getSharedPreferences("user_id", MODE_PRIVATE);
                        SharedPreferences.Editor editor3 = user_id.edit();
                        editor3.putString("id", id);
                        editor3.apply();
                        
                        // USER STATUS
                        SharedPreferences preferences = getSharedPreferences("status", MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = preferences.edit();
                        editor2.putBoolean("status_type", true);
                        editor2.apply();

                        open_MainActivity();
                    }

                    // REGISTER UNSUCCESFULL
                    if(response_login == "false")
                    {
                        String error = response.getString("error");
                        System.out.print("\n\n\n" + error + "\n\n\n");
                        Button_Login.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(LoginActivity.this, "Kullanıcı Bilgileri Yanlış", Toast.LENGTH_SHORT).show();
                    }


                }catch (JSONException e){
                    System.out.print(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                System.out.print("\n\n\n\n\n");
                error.printStackTrace();
                System.out.print("\n\n\n\n\n");
                //TODO: handle failure
                Button_Login.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(LoginActivity.this, "Kullanıcı Bilgileri Yanlış", Toast.LENGTH_SHORT).show();

            }
        });

        Volley.newRequestQueue(this).add(jsonRequest);
    }

}
