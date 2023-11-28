package com.example.shoppingcart.utils.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.shoppingcart.dao.CartDAO;
import com.example.shoppingcart.database.CartDatabase;
import com.example.shoppingcart.views.Product_details_Activity;
import com.example.shoppingcart.R;
import com.example.shoppingcart.utils.model.Products_item;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    Context context;
    List<Products_item> list;

    public RecyclerAdapter(Context context, List<Products_item> list) {
        this.context = context;
        this.list = list;

    }


    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.datarow, parent, false);
        return new RecyclerAdapter.MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {

        Products_item products = list.get(position);
        String product_details = products.getTitle()+"\n"+products.getPrice();
        holder.products.setText(product_details);
        Glide.with(context)
                .load(products.getImage())
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, Product_details_Activity.class);
                Bundle bundle=new Bundle();
                bundle.putString("title",products.getTitle());
                bundle.putDouble("price",products.getPrice());
                bundle.putString("description",products.getDescription());
                bundle.putString("category",products.getCategory());
                bundle.putString("image",products.getImage());
                bundle.putString("rate",String.valueOf(products.getRating().getRate()));
                bundle.putString("count",String.valueOf(products.getRating().getCount()));


                bundle.putParcelable("product",products);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

    }

        @Override
        public int getItemCount () {
        return list.size();

        }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView products,tv_name,tv_price;
        ImageView imageView;
        LinearLayout linearLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            products = itemView.findViewById(R.id.tv_products);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            imageView=itemView.findViewById(R.id.image_view);

        }
    }
}


