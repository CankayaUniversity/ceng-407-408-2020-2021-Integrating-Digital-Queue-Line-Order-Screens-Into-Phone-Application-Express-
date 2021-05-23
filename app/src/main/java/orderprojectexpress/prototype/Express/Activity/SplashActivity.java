package orderprojectexpress.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.prototype.Express.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // SPLASH SCREEN
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    open_LoginActivity();
                }
            }
        };
        timerThread.start();
    }

    private void open_LoginActivity()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void open_MainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
