package com.example.shoppingcart.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppingcart.utils.model.OrderLines;

import java.util.List;
@Dao
public interface OrderLinesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrderLinesItem(OrderLines orderLines);

    @Query("SELECT * FROM OrderLines WHERE Refid =:Refid ")
    List<OrderLines> getAllOrderLinesItems(int Refid);


}
