package orderprojectexpress.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import orderprojectexpress.prototype.Express.Adapter.BasketAdapter;
import orderprojectexpress.prototype.Express.Class.GlobalVariables;
import orderprojectexpress.prototype.Express.Class.Item;
import com.prototype.Express.R;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity
{
    ArrayList<Item> mData;

    // XML VARIABLES
    RecyclerView rw;
    Button button_approve, button_remove;
    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        // XML INITIALIZING
        rw = findViewById(R.id.rw);
        button_approve = findViewById(R.id.button_approve);
        button_remove = findViewById(R.id.button_remove);
        text = findViewById(R.id.text);

        text.setVisibility(View.INVISIBLE);

        // ADAPTER
        rw.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rw.setLayoutManager(linearLayoutManager);
        final BasketAdapter basketAdapter;
        mData = new ArrayList<>();
        mData.addAll(GlobalVariables.getInstance().encounters);
        basketAdapter = new BasketAdapter(getApplicationContext(), mData, button_approve);
        rw.setAdapter(basketAdapter);
        basketAdapter.notifyDataSetChanged();

        button_remove.setVisibility(View.INVISIBLE);
        if(GlobalVariables.getInstance().encounters.size() != 0)
        {
            button_remove.setVisibility(View.VISIBLE);
        }

        else
        {
            text.setVisibility(View.VISIBLE);
        }

        button_remove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(BasketActivity.this, "Sepetiniz Sıfırlandı.", Toast.LENGTH_SHORT).show();

                for(int i = 0; i < GlobalVariables.getInstance().encounters.size(); i++)
                {
                    GlobalVariables.getInstance().encounters.removeAll(GlobalVariables.getInstance().encounters);
                    button_approve.setVisibility(View.INVISIBLE);
                    button_remove.setVisibility(View.INVISIBLE);
                    basketAdapter.notifyDataSetChanged();
                    break;
                }

                if(GlobalVariables.getInstance().encounters.size() == 0)
                {
                    finish();
                }
            }
        });

    }
}
