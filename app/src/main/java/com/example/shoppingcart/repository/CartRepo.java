package com.example.shoppingcart.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.shoppingcart.TaskCompleteCallBack;
import com.example.shoppingcart.dao.CartDAO;
import com.example.shoppingcart.dao.OrderDAO;
import com.example.shoppingcart.dao.OrderLinesDAO;
import com.example.shoppingcart.database.CartDatabase;
import com.example.shoppingcart.utils.model.Order;
import com.example.shoppingcart.utils.model.OrderLines;
import com.example.shoppingcart.utils.model.Product_Cart;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CartRepo {

    private CartDAO cartDAO;
    private LiveData<List<Product_Cart>> allCartItemsLiveData;
    private Executor executor = Executors.newSingleThreadExecutor();



    public LiveData<List<Product_Cart>> getAllCartItemsLiveData() {
        return allCartItemsLiveData;
    }

    public CartRepo(Application application){
        cartDAO = CartDatabase.getInstance(application).cartDAO();
        allCartItemsLiveData= cartDAO.getAllCartItems();
        orderDAO = CartDatabase.getInstance(application).orderDAO();
        allOrderedItemsLiveData= orderDAO.getAllOrderedItems();
        orderLinesDAO = CartDatabase.getInstance(application).orderLinesDAO();
        //allOrderedItemsLiveData=orderLinesDAO.getAllOrderLinesItems();
    }
    public  void insertCartItem(Product_Cart products){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.insertCartItem(products);
            }
        });
    }

    public void deleteCartItem(List<Product_Cart>products){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.deleteCartItem(products);
            }
        });
    }

    public  void updateQuantity(int id, int quantity){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.updateQuantity(id, quantity);
            }
        });
    }
    public void updatePrice(int id, double price){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.updatePrice(id, price);
            }
        });
    }
    public  void  deleteAllCartItems(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.deleteAllCartItems();
            }
        });
    }


private OrderDAO orderDAO;
    private LiveData<List<Order>> allOrderedItemsLiveData;

    public LiveData<List<Order>> getAllOrderedItemsLiveData(){
        return allOrderedItemsLiveData;
    }

    public  void insertOrderItem(Order order, TaskCompleteCallBack taskCompleteCallBack){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                orderDAO.insertOrderItem(order);
                taskCompleteCallBack.onComplete();
            }
        });
    }
    public  void updateUuid(int id, String uuid){
        executor.execute(new Runnable() {

            public void run() {
                orderDAO.updateUuid(id, uuid);
            }
        });
    }


    private OrderLinesDAO orderLinesDAO;
    private  LiveData<List<OrderLines>> allOrderLinesLiveData;

    public  LiveData<List<OrderLines>> getAllOrderLinesLiveData(){
        return allOrderLinesLiveData;
    }

    public  void insertOrderLinesItem( OrderLines orderLines){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                orderLinesDAO.insertOrderLinesItem(orderLines);
            }
        });
    }

    public Order getOrder(String uuId){
        return orderDAO.getOrder(uuId);

    }public List<OrderLines> getOrderLines(int Refid){
        return orderLinesDAO.getAllOrderLinesItems(Refid);
    }

    public  void insertCartItem(OrderLines orderLines){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                orderLinesDAO.insertOrderLinesItem(orderLines);
            }
        });
    }



}
