package com.example.shoppingcart.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.shoppingcart.dao.CartDAO;
import com.example.shoppingcart.dao.OrderDAO;
import com.example.shoppingcart.dao.OrderLinesDAO;
import com.example.shoppingcart.utils.model.Order;
//import com.example.shoppingcart.utils.model.OrderLines;
import com.example.shoppingcart.utils.model.OrderLines;
import com.example.shoppingcart.utils.model.Product_Cart;

@Database(entities  = {Product_Cart.class, Order.class, OrderLines.class}, version = 1,exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {

    public  abstract CartDAO cartDAO();
    public abstract OrderDAO orderDAO();
    public abstract OrderLinesDAO orderLinesDAO();
    private static CartDatabase instance;

    public static synchronized CartDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext()
                    , CartDatabase.class,"ProductDatabase")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }
        return instance;
    }
}
