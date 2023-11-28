package com.example.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.shoppingcart.ui.cart.CartFragment;
import com.example.shoppingcart.ui.home.HomeFragment;
import com.example.shoppingcart.ui.order.OrderFragment;
import com.example.shoppingcart.utils.model.Product_Cart;
import com.example.shoppingcart.viewmodel.CartViewModel;
import com.example.shoppingcart.views.RecyclerActivity;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.shoppingcart.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int cart_quantity = 0;
    private ActivityMainBinding binding;
    private CartViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_cart, R.id.navigation_orders)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        BadgeDrawable badge = navView.getOrCreateBadge( R.id.navigation_cart);
        viewModel.getAllCartItems().observe(this, new Observer<List<Product_Cart>>() {
            @Override
            public void onChanged(List<Product_Cart> product_carts) {
                int quantity = 0;
                for (Product_Cart product_cart : product_carts) {
                    quantity += product_cart.getQuantity();
                }
                cart_quantity = quantity;
                badge.setNumber(cart_quantity);
                invalidateMenu();
            }
        });

    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}