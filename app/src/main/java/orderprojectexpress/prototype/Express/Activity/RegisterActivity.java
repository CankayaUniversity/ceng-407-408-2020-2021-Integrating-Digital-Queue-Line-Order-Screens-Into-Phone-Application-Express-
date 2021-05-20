package orderprojectexpress.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // XML
        final EditText editText_RegisterEmail = findViewById(R.id.editText_RegisterEmail);
        final EditText editText_RegisterUserName = findViewById(R.id.editText_RegisterUserName);
        final CheckBox checkBox_Old = findViewById(R.id.checkBox_Old);
        final CheckBox checkBox_TermsOfUse = findViewById(R.id.checkBox_TermsOfUse);
        final Button Button_Register = findViewById(R.id.Button_Register);
        final EditText editText_RegisterPassword = findViewById(R.id.editText_RegisterPassword);

        final Pattern EMAIL_ADRESS;
        final Pattern PASSWORD;


        // E-MAIL REGEX
        EMAIL_ADRESS = Pattern.compile(
          "[a-zA-z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
          "\\@" +
          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
          "(" +
          "\\." +
          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
          ")+"
        );

        //PASSWORD REGEX
        PASSWORD = Pattern.compile(
            "^" +
            "(?=.*[0-9])" +
            "(?=.*[a-z])" +
            "(?=.*[A-Z])" +
            "(?=.*[@#$%^&+=])" +
            "(?=\\S+$)" +
            ".{6,120}" +
            "$"
        );


        // INTENT TO LOGIN PAGE AFTER SUCCESSFUL REGISTER
        Button_Register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                // INPUT

                String RegisterEmail = editText_RegisterEmail.getText().toString().trim();
                String RegisterUserName = editText_RegisterUserName.getText().toString().trim();
                String RegisterPassword = editText_RegisterPassword.getText().toString().trim();

                // CHECKING checkBox_Old, checkBox_TermsOfUse
                if(checkBox_Old.isChecked() && checkBox_TermsOfUse.isChecked())
                {
                    //CHECK REGISTER FIELDS
                    if(TextUtils.isEmpty(editText_RegisterUserName.getText().toString()))
                    {
                        Toast.makeText(RegisterActivity.this, "User Name field cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                    else if (TextUtils.isEmpty(editText_RegisterEmail.getText().toString()))
                    {
                        Toast.makeText(RegisterActivity.this, "E-mail field cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                    else if(TextUtils.isEmpty(editText_RegisterPassword.getText().toString()))
                    {
                        Toast.makeText(RegisterActivity.this, "Password field cannot be empty", Toast.LENGTH_SHORT).show();
                    }


                    // NAVIGATION TO LOGIN PAGE
                    else
                    {
                        registerUser(RegisterUserName, RegisterEmail, RegisterPassword);
                        open_LoginActivity();
                    }
                }

                else
                {
                    Toast.makeText(RegisterActivity.this, "Check Boxes", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }






    // INTENTS
    public void open_LoginActivity()
    {
        Intent intent6 = new Intent(this, LoginActivity.class);
        startActivity(intent6);
    }

    // FUNCTIONS
    private void registerUser(String username, String email, String password)
    {
        final String url = "http://104.248.207.133:5000/api/v1/auth/register";

        Map<String, String> params = new HashMap();
        params.put("name", username);
        params.put("password", password);
        params.put("email", email);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                //TODO: handle success
                try
                {
                    String response_register = response.getString("success");
                    System.out.print("\n\n\n" + response_register + "\n\n\n");

                    // REGISTER SUCCESSFUL
                    if(response_register == "true")
                    {
                        Toast.makeText(RegisterActivity.this, "Express'e Hoşgeldiniz!", Toast.LENGTH_SHORT).show();
                        open_LoginActivity();
                    }

                    // REGISTER UNSUCCESFULL
                    else
                    {
                        String error = response.getString("error");
                        System.out.print("\n\n\n" + error + "\n\n\n");
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
            }
        });

        Volley.newRequestQueue(this).add(jsonRequest);
    }
}
