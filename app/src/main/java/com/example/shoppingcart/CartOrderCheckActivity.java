package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.shoppingcart.utils.adapter.OrderAdapter;
import com.example.shoppingcart.utils.adapter.RecyclerAdapter;
import com.example.shoppingcart.utils.model.Order;
import com.example.shoppingcart.utils.model.Product_Cart;
import com.example.shoppingcart.utils.model.Products_item;
import com.example.shoppingcart.viewmodel.CartViewModel;
import com.example.shoppingcart.views.RecyclerActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CartOrderCheckActivity extends AppCompatActivity implements OnItemClick {
    private OrderAdapter orderAdapter;
    RecyclerView recyclerView;
    CartViewModel cartViewModel;
    TextView textView;
    List<Order> orders = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_order_check);

        textView = findViewById(R.id.tv_uuid);
        recyclerView = findViewById(R.id.order_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        setAdapter(orders);


        cartViewModel.getAllOrderedItems().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {

                orderAdapter.setOrders(orders);
            }
        });
    }
@Override
    public void onItemClick(Order order){
        Intent intent = new Intent(CartOrderCheckActivity.this, OrderLinesActivity.class);
        startActivity(intent);
    }

    private void setAdapter(List<Order> orders) {
        orderAdapter = new OrderAdapter(CartOrderCheckActivity.this,orders, (OnItemClick) this, cartViewModel);
        recyclerView.setAdapter(orderAdapter);
        orderAdapter.notifyDataSetChanged();
    }
}