package com.example.shoppingcart.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppingcart.utils.model.Order;
import com.example.shoppingcart.utils.model.Product_Cart;

import java.util.List;

@Dao
public interface OrderDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrderItem(Order order);

    @Query("SELECT * FROM `Order`")
    LiveData<List<Order>> getAllOrderedItems();


    @Query("UPDATE `order` SET uuid =:uuid WHERE id =:id")
    void updateUuid(int id, String uuid);

    @Query("SELECT * FROM `order` WHERE uuid = :uuid")
    Order getOrder( String uuid );
}
