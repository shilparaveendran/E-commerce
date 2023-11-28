package com.example.shoppingcart.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shoppingcart.Add_cart_Activity;
import com.example.shoppingcart.R;
import com.example.shoppingcart.utils.adapter.RecyclerAdapter;
import com.example.shoppingcart.utils.model.Product_Cart;
import com.example.shoppingcart.utils.model.Products_item;
import com.example.shoppingcart.viewmodel.CartViewModel;
//import com.example.shoppingcart.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class Product_details_Activity extends AppCompatActivity {
    private CartViewModel viewModel;
    private List<Product_Cart>product_cartList=new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    int quantity;
    TextView value;
    @SuppressLint("MissingInflatedId")
    private static final String TAG = "Product_details_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        product_cartList=new ArrayList<Product_Cart>();
        TextView products= findViewById(R.id.products);
        TextView textView1 = findViewById(R.id.textView2);
        ImageView imageView= findViewById(R.id.imageView2);
        Button button_add=findViewById(R.id.add_cart);
        Button button_go=findViewById(R.id.go_cart);
        TextView lbl=findViewById(R.id.lbl);
        getSupportActionBar().setTitle("Product Details");
        viewModel= new ViewModelProvider(this ).get(CartViewModel.class);
        viewModel.getAllCartItems().observe(this, new Observer<List<Product_Cart>>() {
            @Override
            public void onChanged(List<Product_Cart> product_carts) {
                product_cartList.addAll(product_carts);
            }
        });

        Bundle bundle=getIntent().getExtras();
        String mTitle=bundle.getString("title");
        Double mPrice=bundle.getDouble("price");
        String mDescription=bundle.getString("description");
        String mCategory=bundle.getString("category");
        String mImage=bundle.getString("image");
        String mCount=bundle.getString("count");
        String mRate=bundle.getString("rate");
        textView1.setText(mCount +"\n"+ mRate);

        Products_item product = bundle.getParcelable("product");

        Glide.with(this).load(mImage).into(imageView);
        String product_details = mTitle+"\n"+mPrice+"\n"+mDescription+"\n"+mCategory;
        products.setText(product_details);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product_Cart product_cart=new Product_Cart();
                product_cart.setId(product.getId());
                product_cart.setTitle(product.getTitle());
                product_cart.setPrice(product.getPrice());
                product_cart.setImage(product.getImage());
                    final int[] quantity ={1};
                    final int[] id = new int[1];
                    if(!product_cartList.isEmpty()){
                        for(int i=0;i<product_cartList.size();i++){
                            if(product_cart.getTitle().equals(product_cartList.get(i).getTitle())) {
                                quantity[0] = product_cartList.get(i).getQuantity();
                                quantity[0]++;
                                id[0] = product_cartList.get(i).getId();
                            }
                        }
                    }
                    if(quantity[0]==1){
                        product_cart.setQuantity(quantity[0]);
                        product_cart.setTotalItemPrice(quantity[0]*product_cart.getPrice());
                        viewModel.insertCartItem(product_cart);
                    }else{
                        viewModel.updateQuantity(id[0],quantity[0]);
                        viewModel.updatePrice(id[0],quantity[0]*product_cart.getPrice());
                    }


            }
        });


        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Product_details_Activity.this, Add_cart_Activity.class);
                startActivity(intent);
            }
        });
        viewModel.getAllCartItems().observe(this, new Observer<List<Product_Cart>>() {
            @Override
            public void onChanged(List<Product_Cart> product_carts) {
                product_cartList.addAll(product_carts);
            }
        });

    }

}