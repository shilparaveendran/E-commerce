package com.example.shoppingcart.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shoppingcart.Add_cart_Activity;
import com.example.shoppingcart.CartOrderCheckActivity;
import com.example.shoppingcart.MainActivity;
import com.example.shoppingcart.R;
import com.example.shoppingcart.SplashActivity;
import com.example.shoppingcart.databinding.FragmentHomeBinding;
import com.example.shoppingcart.utils.adapter.RecyclerAdapter;
import com.example.shoppingcart.utils.model.Product_Cart;
import com.example.shoppingcart.utils.model.Products_item;
import com.example.shoppingcart.utils.model.Rating;
import com.example.shoppingcart.viewmodel.CartViewModel;
import com.example.shoppingcart.views.RecyclerActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    private int cart_quantity = 0;
    private CartViewModel viewModel;
    List<Products_item> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root. findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        setAdapter(list);
        recyclerAdapter = new RecyclerAdapter(getActivity(), list);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
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
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);

        final TextView textView = binding.textHome;
     //   homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    public void setAdapter(List<Products_item> products){
        recyclerAdapter = new RecyclerAdapter(getActivity(),products);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}