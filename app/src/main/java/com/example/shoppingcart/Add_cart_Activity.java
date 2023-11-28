package com.example.shoppingcart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingcart.utils.adapter.CartAdapter;
import com.example.shoppingcart.utils.adapter.RecyclerAdapter;
import com.example.shoppingcart.utils.model.Order;
import com.example.shoppingcart.utils.model.OrderLines;
import com.example.shoppingcart.utils.model.Product_Cart;
import com.example.shoppingcart.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Add_cart_Activity extends AppCompatActivity implements CartAdapter.CartClickedListeners {
    private CartAdapter cartAdapter;
    private CardView cardView;
    RecyclerView recyclerView;
    TextView TotalPriceTv, textView;
    Button place_order_btn;
    CartViewModel cartViewModel;
    List<Product_Cart> product_carts = new ArrayList<>();
    private ActionMode myActMode;
    List<Product_Cart> product_cartList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_cart);

        getSupportActionBar().setTitle("My Orders");
        cardView = findViewById(R.id.cartActivityCardView);
        cartAdapter = new CartAdapter(this, Add_cart_Activity.this);
        textView = findViewById(R.id.textView_2);
        TotalPriceTv = findViewById(R.id.cartActivityTotalPriceTv);
        place_order_btn = findViewById(R.id.btn_place_order);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getAllCartItems().observe(this, new Observer<List<Product_Cart>>() {
            @Override
            public void onChanged(List<Product_Cart> product_cart) {
                double price = 0;
                cartAdapter.setProduct_cartList(product_cart);
                product_carts = product_cart;
                for (int i = 0; i < product_carts.size(); i++) {
                    price = price + product_carts.get(i).getTotalItemPrice();
                }
                TotalPriceTv.setText(String.valueOf(price));
            }
        });


        place_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (product_carts != null && product_carts.size() > 0) {
                    Order order = new Order();
                    String uuid = UUID.randomUUID().toString();
                    order.setUuid(uuid);
                    cartViewModel.insertOrderItem(order, new TaskCompleteCallBack() {
                        @Override
                        public void onComplete() {
                            Order order1 = cartViewModel.getOrder(uuid);
                            int orderId = order1.getId();
                            for (int i = 0; i < product_carts.size(); i++) {
                                OrderLines orderLines = new OrderLines();
                                orderLines.setRefId(orderId);
                                orderLines.setTitle(product_carts.get(i).getTitle());
                                orderLines.setImage(product_carts.get(i).getImage());
                                orderLines.setPrice(product_carts.get(i).getPrice());
                                orderLines.setQuantity(product_carts.get(i).getQuantity());
                                orderLines.setTotalIemPrice(product_carts.get(i).getTotalItemPrice());

                                cartViewModel.insertOrderLines(orderLines);
                            }
                            cartViewModel.deleteAllCartItems();
                        }
                    });

                    textView.setVisibility(View.INVISIBLE);
                    place_order_btn.setVisibility(View.INVISIBLE);
                    TotalPriceTv.setVisibility(View.INVISIBLE);
                    cardView.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(Add_cart_Activity.this, "Empty Cart", Toast.LENGTH_SHORT).show();

            }
            }
        });
    }


    @Override
    public void onPlusClicked(Product_Cart product_cart) {
        int quantity = product_cart.getQuantity() + 1;
        cartViewModel.updateQuantity(product_cart.getId(), quantity);
        cartViewModel.updatePrice(product_cart.getId(), quantity * product_cart.getPrice());
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMinusClicked(Product_Cart product_cart) {
        int quantity = product_cart.getQuantity() - 1;
        if (quantity != 0) {
            cartViewModel.updateQuantity(product_cart.getId(), quantity);
            cartViewModel.updatePrice(product_cart.getId(), quantity * product_cart.getPrice());
            cartAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSelection(List<Product_Cart> product_cartList) {
       this. product_cartList = product_cartList;
        if(myActMode != null){
            return;
        }
        myActMode=startSupportActionMode(myActModeCallback);
    }

    private ActionMode.Callback myActModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.delete, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.delete) {
                    cartViewModel.deleteCartItem(product_cartList);
                    Toast.makeText(Add_cart_Activity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
            }

            return false;
        }
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            myActMode=null;
            if(product_cartList != null && product_cartList.size()>0){
            cartAdapter.setSelectionList();
            }
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cartViewModel.getAllCartItems().removeObservers(this);
    }
}