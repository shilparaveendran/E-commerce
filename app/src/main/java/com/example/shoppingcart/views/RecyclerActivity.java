package com.example.shoppingcart.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
//import com.bumptech.glide.Glide;
import com.example.shoppingcart.Add_cart_Activity;
import com.example.shoppingcart.CartOrderCheckActivity;
import com.example.shoppingcart.R;
import com.example.shoppingcart.utils.adapter.RecyclerAdapter;
import com.example.shoppingcart.utils.model.Order;
import com.example.shoppingcart.utils.model.Product_Cart;
import com.example.shoppingcart.utils.model.Products_item;
import com.example.shoppingcart.utils.model.Rating;
//import com.example.shoppingcart.viewmodel.CartViewModel;
import com.example.shoppingcart.viewmodel.CartViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    ListView listView;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ImageView imageView;
    private int cart_quantity = 0;
    private CartViewModel viewModel;
    List<Products_item> list = new ArrayList<>();
    ImageButton imageButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        listView = findViewById(R.id.list_view);
        imageButton  = findViewById(R.id.btn_bag);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(RecyclerActivity.this, 2));
        setAdapter(list);
        recyclerAdapter = new RecyclerAdapter(RecyclerActivity.this, list);

        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        viewModel.getAllCartItems().observe(this, new Observer<List<Product_Cart>>() {
            @Override
            public void onChanged(List<Product_Cart> product_carts) {
                int quantity = 0;
                for (Product_Cart product_cart : product_carts) {
                    quantity += product_cart.getQuantity();
                }
                cart_quantity = quantity;
                invalidateMenu();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecyclerActivity.this, CartOrderCheckActivity.class);
                startActivity(intent);
            }
        });



        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = " https://fakestoreapi.com/products";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //  Log.d(TAG, "onResponse: "+response.toString());
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Products_item>>() {
                        }.getType();
                        List<Products_item> userList = gson.fromJson(response.toString(), type);
                        setAdapter(userList);
                        for (int i = 0; i < userList.size(); i++) {
                            Products_item products = userList.get(i);
                            Rating rating = products.getRating();


                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RecyclerActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_badge,menu);
        MenuItem menuItem = menu.findItem(R.id.cartFragment);
        View actionView = menuItem.getActionView();
        TextView cartBadgeTextview = actionView.findViewById(R.id.tv_cartBadge);
        cartBadgeTextview.setText(String.valueOf(cart_quantity));


        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RecyclerActivity.this, Add_cart_Activity.class);
                startActivity(intent);
            }
        });
        return  true;
    }





    public void setAdapter(List<Products_item> products){
        recyclerAdapter = new RecyclerAdapter(RecyclerActivity.this,products);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
    }




}
