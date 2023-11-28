package com.example.shoppingcart.utils.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingcart.R;
import com.example.shoppingcart.utils.model.Product_Cart;
import com.example.shoppingcart.utils.model.Products_item;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    Context context;
    List<Product_Cart> product_cartList;
    private CartClickedListeners cartClickedListeners;
    boolean isSelectMode = false;
    ArrayList<Product_Cart> selectedItems = new ArrayList<>();

    public  CartAdapter(CartClickedListeners cartClickedListeners,Context context){
        this.context = context;
        this.cartClickedListeners = cartClickedListeners;
    }

    public void setProduct_cartList(List<Product_Cart> product_cartList){
        this.product_cartList=product_cartList;
        notifyDataSetChanged();
    }

    public void setSelectionList( ){
        selectedItems = new ArrayList<>();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product_Cart product_cart = product_cartList.get(position);
        Glide.with(context)
                .load(product_cart.getImage())
                .into(holder.imageView);
        holder.name.setText(product_cart.getTitle());
        holder.price.setText(product_cart.getTotalItemPrice() + " ");
        holder.quantity.setText(product_cart.getQuantity() + " ");
        if(!selectedItems.contains(product_cartList.get(holder.getAdapterPosition()))){
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }else{
            holder.itemView.setBackgroundColor(Color.CYAN);
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public boolean onLongClick(View view) {
               isSelectMode = true;
                if(selectedItems.contains(product_cartList.get(holder.getAdapterPosition()))){
                    holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                    selectedItems.remove(product_cartList.get(holder.getAdapterPosition()));
                }else{
                    holder.itemView.setBackgroundColor(Color.CYAN);
                    selectedItems.add(product_cartList.get(holder.getAdapterPosition()));
                }
                cartClickedListeners.onSelection(selectedItems);
                if(selectedItems.size()==0)
                    isSelectMode = false;
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                if(isSelectMode){
                    if(selectedItems.contains(product_cartList.get(holder.getAdapterPosition()))){
                        holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                        selectedItems.remove(product_cartList.get(holder.getAdapterPosition()));
                    }else{
                        holder.itemView.setBackgroundColor(Color.CYAN);
                        selectedItems.add(product_cartList.get(holder.getAdapterPosition()));
                    }
                    if(selectedItems.size()==0)
                        isSelectMode = false;
                    cartClickedListeners.onSelection(selectedItems);
                }

            }
        });

//        holder.imageButton_dlt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cartClickedListeners.onSelection(product_cartList);
//            }
//        });
        holder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onPlusClicked(product_cart);
            }
        });
        holder.decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onMinusClicked(product_cart);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(product_cartList == null){
            return  0;
        }else{
            return product_cartList.size();
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,quantity;
        ImageView imageView;
        ImageButton imageButton_dlt,increment, decrement;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            price = itemView.findViewById(R.id.tv_price);
            imageView =itemView.findViewById(R.id.img_product);
            increment = itemView.findViewById(R.id.incrbtn);
            decrement= itemView.findViewById(R.id.decrbtn);
            imageButton_dlt = itemView.findViewById(R.id.delete);
            quantity=itemView.findViewById(R.id.count);



        }
    }
    public interface CartClickedListeners{
        //void onDeleteClicked(Product_Cart product_cart);
        void onPlusClicked(Product_Cart product_cart);
        void onMinusClicked(Product_Cart product_cart);
        void onSelection(List<Product_Cart>product_cartList);
    }
}
