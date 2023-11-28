package com.example.shoppingcart.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingcart.CartOrderCheckActivity;
import com.example.shoppingcart.OnItemClick;
import com.example.shoppingcart.OrderLinesActivity;
import com.example.shoppingcart.R;
import com.example.shoppingcart.databinding.FragmentOrdersBinding;
import com.example.shoppingcart.databinding.FragmentOrdersBinding;
import com.example.shoppingcart.utils.adapter.OrderAdapter;
import com.example.shoppingcart.utils.model.Order;
import com.example.shoppingcart.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment  implements OnItemClick{
    private OrderAdapter orderAdapter;
    RecyclerView recyclerView;
    CartViewModel cartViewModel;
    TextView textView;
    List<Order> orders = new ArrayList<>();
    private FragmentOrdersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        OrderViewModel notificationsViewModel =
//                new ViewModelProvider(this).get(OrderViewModel.class);

        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        textView = root.findViewById(R.id.tv_uuid);
        recyclerView = root.findViewById(R.id.order_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        setAdapter(orders);


        cartViewModel.getAllOrderedItems().observe(getActivity(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {

                orderAdapter.setOrders(orders);
            }
        });
//        final TextView textView = binding.textNotifications;
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    @Override
    public void onItemClick(Order order){
        Intent intent = new Intent(getActivity(), OrderLinesActivity.class);
        startActivity(intent);
    }
    private void setAdapter(List<Order> orders) {
        orderAdapter = new OrderAdapter(getActivity(),orders, (OnItemClick) this, cartViewModel);
        recyclerView.setAdapter(orderAdapter);
        orderAdapter.notifyDataSetChanged();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}