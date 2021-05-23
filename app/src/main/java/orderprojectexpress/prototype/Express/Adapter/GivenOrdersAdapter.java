package orderprojectexpress.prototype.Express.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import orderprojectexpress.prototype.Express.Class.GlobalVariables;
import orderprojectexpress.prototype.Express.Class.Item;
import com.prototype.Express.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class GivenOrdersAdapter extends RecyclerView.Adapter<GivenOrdersAdapter.MyViewHolder>
{
    // VARIABLES
    Context context;
    ArrayList<Item> mData;

    public GivenOrdersAdapter(Context context, ArrayList<Item> mData)
    {
        this.context = context;
        this.mData = mData;
    }


    @NonNull
    @Override
    public GivenOrdersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view;

        view = LayoutInflater.from(context).inflate(R.layout.item3, parent, false);

        GivenOrdersAdapter.MyViewHolder myViewHolder = new GivenOrdersAdapter.MyViewHolder(view);

        return  myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final GivenOrdersAdapter.MyViewHolder holder, final int position)
    {
        holder.name.setText(mData.get(holder.getAdapterPosition()).getName());
        Picasso.get().load(mData.get(holder.getAdapterPosition()).getPhoto()).into(holder.image);
        holder.price.setText(String.valueOf(mData.get(holder.getAdapterPosition()).getPrice()) + "₺");
        holder.description.setText(mData.get(holder.getAdapterPosition()).getDescription());
        holder.time.setText(mData.get(holder.getAdapterPosition()).getCreatedAt());
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
        ImageView image;
        TextView description;
        TextView time;


        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            // CASTING VARIABLES OF ITEM
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
            time = itemView.findViewById(R.id.time);
        }
    }






}
