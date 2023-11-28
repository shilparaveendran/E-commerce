package com.example.shoppingcart.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingcart.Add_cart_Activity;
import com.example.shoppingcart.OnItemClick;
import com.example.shoppingcart.R;
import com.example.shoppingcart.utils.model.Order;
import com.example.shoppingcart.utils.model.OrderLines;
import com.example.shoppingcart.utils.model.Product_Cart;
import com.example.shoppingcart.utils.model.Products_item;
import com.example.shoppingcart.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    Context context;
    List<Order> orders;
    OnItemClick onItemClick;
    CartViewModel cartViewModel;


    public OrderAdapter(Context context, List<Order> orders, OnItemClick onItemClick, CartViewModel cartViewModel) {
        this.context = context;
        this.orders= orders;
        this.onItemClick=onItemClick;
       this. cartViewModel = cartViewModel;
    }
    public void setOrders( List<Order> orders){

        this.orders=orders;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_order_tv, parent, false);
        return new OrderAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyViewHolder holder, int position) {
        Order order = orders.get(position);
        String order_details = order.getUuid();
        holder.uuid.setText(order_details);
        holder. recyclerView.setLayoutManager(new LinearLayoutManager(context));
        List<OrderLines> orderLinesList = cartViewModel.getOrderLines(order.getId());
        OrderLinesAdapter orderLinesAdapter = new OrderLinesAdapter(context, orderLinesList);
        holder.recyclerView.setAdapter(orderLinesAdapter);
        orderLinesAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return orders.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView uuid;
        RecyclerView recyclerView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            uuid=itemView.findViewById(R.id.tv_uuid);
            recyclerView = itemView.findViewById(R.id.recycler_order_lines);
        }
    }
}
