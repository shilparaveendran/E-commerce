package com.example.shoppingcart.utils.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingcart.OrderLinesActivity;
import com.example.shoppingcart.R;
import com.example.shoppingcart.utils.model.Order;
import com.example.shoppingcart.utils.model.OrderLines;
import com.example.shoppingcart.views.Product_details_Activity;

import java.util.List;

public class OrderLinesAdapter extends RecyclerView.Adapter<OrderLinesAdapter.MyViewHolder>{

    Context context;
    List<OrderLines> orderLinesList;

    public OrderLinesAdapter(Context context, List<OrderLines> orderLinesList) {
        this.context = context;
        this.orderLinesList= orderLinesList;
    }


    public void setOrderLines(  List<OrderLines> orderLinesList){

        this.orderLinesList= orderLinesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderLinesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.order_lines_item, parent, false);
        return new OrderLinesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderLinesAdapter.MyViewHolder holder, int position) {

        OrderLines orderLines = orderLinesList.get(position);
        String order_details = orderLines.getTitle()+"\n"+orderLines.getPrice();
         holder.order_items.setText(order_details);
        Glide.with(context)
                .load(orderLines.getImage())
                .into(holder.imageView);
        holder.quantity.setText(orderLines.getQuantity() + " ");
        holder.tot_price.setText(orderLines.getTotalIemPrice() + " ");


    }

    @Override
    public int getItemCount() {

        return orderLinesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView order_items,quantity,tot_price;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            order_items = itemView.findViewById(R.id.tv_name);
            imageView= itemView.findViewById(R.id.img_product);
            quantity = itemView.findViewById(R.id.tv_qnty);
            tot_price = itemView.findViewById(R.id.tv_tot_price);


        }
    }
}

