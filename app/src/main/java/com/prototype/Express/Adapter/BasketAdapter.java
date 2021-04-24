package com.prototype.Express.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.prototype.Express.Activity.BasketActivity;
import com.prototype.Express.Class.Item;
import com.prototype.Express.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.MyViewHolder>
{
    // VARIABLES
    Context context;
    ArrayList<Item> mData;
    Button button_approve;
    int price_total;
    int item_quantity;

    // VIEWHOLDERS
    MyViewHolder myViewHolder;

    public BasketAdapter(Context context, ArrayList<Item> mData, Button button_approve)
    {
        this.context = context;
        this.mData = mData;
        this.button_approve = button_approve;
        button_approve.setVisibility(View.INVISIBLE);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view;

        view = LayoutInflater.from(context).inflate(R.layout.basket_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return  myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position)
    {
        holder.name.setText(mData.get(holder.getAdapterPosition()).getName());
        Picasso.get().load(mData.get(holder.getAdapterPosition()).getPhoto()).into(holder.image);
        holder.description.setText(mData.get(holder.getAdapterPosition()).getDescription());
        holder.price.setText(String.valueOf(mData.get(holder.getAdapterPosition()).getPrice() * mData.get(holder.getAdapterPosition()).getQuantity()) + "₺");
        holder.quantity.setText(String.valueOf(mData.get(holder.getAdapterPosition()).getQuantity()));
        item_quantity = mData.get(holder.getAdapterPosition()).getQuantity();

        price_total = price_total + (mData.get(holder.getAdapterPosition()).getPrice() * mData.get(holder.getAdapterPosition()).getQuantity());

        if(price_total != 0)
        {
            button_approve.setVisibility(View.VISIBLE);
            button_approve.setText("SİPARİŞİ ONAYLA: " + price_total + "₺");

            button_approve.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(context, "Siparişiniz Alındı!", Toast.LENGTH_SHORT).show();

                    // EMIT DATA TO SOCKET SERVER
                    socket_emit();
                    System.out.print("\n\n\n EMIT CALLED \n\n\n");

                    socket_receive();
                    System.out.print("\n\n\n RECEIVE CALLED \n\n\n");

                }
            });
        }
    }


    @Override
    public int getItemCount()
    {
        return mData.size();
    }




    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        // VARIABLES OF ITEM
        TextView name;
        TextView price;
        ConstraintLayout descriptionLayout;
        ImageView image;
        TextView description, quantity;
        ImageView remove;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            // CASTING VARIABLES OF ITEM
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            descriptionLayout = itemView.findViewById(R.id.descriptionLayout);
            image = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
            quantity = itemView.findViewById(R.id.quantity);
            remove = itemView.findViewById(R.id.remove);
        }
    }


    // TODO SOCKET
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://104.248.207.133:5000");
        }catch (URISyntaxException e){}
    }

    private void socket_emit()
    {
        String emit_data = "text message as string";

        mSocket.connect();
        mSocket.emit("phone-send", emit_data);
        System.out.print("\n\n\n SOCKET_EMIT FUNCTION \n\n\n");
    }

    private void socket_receive()
    {
        mSocket.on("phone-receive", onNewMessage);
        mSocket.connect();
        System.out.print("\n\n\n SOCKET_RECEIVE FUNCTION \n\n\n");
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener()
    {
        @Override
        public void call(final Object... args)
        {
            ((BasketActivity)context).runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    JSONObject data = (JSONObject) args[0];
                    String message;
                    System.out.print("\n\n\n THREAD \n\n\n");

                    try {
                        System.out.print("\n\n\n Emitter.Listener try block \n\n\n");
                        message = data.getString("phone-receive");

                    }catch (JSONException e){
                        System.out.print("\n\n\n Emitter.Listener catch block \n\n\n");
                        return;
                    }

                    Toast.makeText(context, "Received Message: " + message, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
}
