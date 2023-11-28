package com.example.shoppingcart.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppingcart.utils.model.Order;
import com.example.shoppingcart.utils.model.Product_Cart;
//import com.example.shoppingcart.utils.model.Products_item;

import java.util.List;

@Dao
public interface CartDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCartItem(Product_Cart product_cart);


    @Query("SELECT * FROM product_cart")
    LiveData<List<Product_Cart>> getAllCartItems();


    @Delete
    void deleteCartItem(List<Product_Cart> products);

    @Query("UPDATE product_cart SET quantity=:quantity WHERE id=:id")
    void updateQuantity(int id, int quantity);

    @Query("UPDATE product_cart SET totalItemPrice=:totalItemPrice WHERE id=:id")
    void updatePrice(int id, double totalItemPrice);

    @Query("DELETE FROM product_cart WHERE id= :id")
    void deleteById(int id);

    @Query("Delete from product_cart")
    void deleteAllCartItems();




}
