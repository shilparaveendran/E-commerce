package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shoppingcart.utils.adapter.OrderAdapter;
import com.example.shoppingcart.utils.adapter.OrderLinesAdapter;
import com.example.shoppingcart.utils.model.Order;
import com.example.shoppingcart.utils.model.OrderLines;
import com.example.shoppingcart.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class OrderLinesActivity extends AppCompatActivity {

    private OrderLinesAdapter orderLinesAdapter;
    RecyclerView recyclerView;
    CartViewModel cartViewModel;


    List<OrderLines> orderLinesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_lines);


        recyclerView = findViewById(R.id.order_lines_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderLinesAdapter = new OrderLinesAdapter(OrderLinesActivity.this,orderLinesList);
        recyclerView.setAdapter(orderLinesAdapter);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getAllOrderLinesItems().observe(this, new Observer<List<OrderLines>>() {
            @Override
            public void onChanged(List<OrderLines> orderLines) {
                orderLinesAdapter.setOrderLines(orderLines);
               // orderLinesList = orderLines;
            }
        });



    }
}