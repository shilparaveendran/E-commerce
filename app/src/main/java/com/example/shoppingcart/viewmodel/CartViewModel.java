package com.example.shoppingcart.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shoppingcart.TaskCompleteCallBack;
import com.example.shoppingcart.repository.CartRepo;
import com.example.shoppingcart.utils.model.Order;
import com.example.shoppingcart.utils.model.OrderLines;
import com.example.shoppingcart.utils.model.Product_Cart;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private CartRepo cartRepo;


    public CartViewModel(@NonNull Application application) {
        super(application);
        cartRepo=new CartRepo(application);
    }
    public LiveData<List<Product_Cart>> getAllCartItems(){
        return  cartRepo.getAllCartItemsLiveData();
    }

    public void insertCartItem(Product_Cart products){
        cartRepo.insertCartItem(products);
    }
    public  void updateQuantity(int id, int quantity){
        cartRepo.updateQuantity(id, quantity);
    }
    public  void updatePrice(int id, double price){
        cartRepo.updatePrice(id, price);
    }
    public void deleteCartItem(List<Product_Cart>products){
        cartRepo.deleteCartItem(products);
    }
    public void deleteAllCartItems(){
        cartRepo.deleteAllCartItems();
    }


    public LiveData<List<Order>> getAllOrderedItems(){
        return cartRepo.getAllOrderedItemsLiveData();
    }
    public void insertOrderItem(Order order, TaskCompleteCallBack taskCompleteCallBack){
        cartRepo.insertOrderItem(order,taskCompleteCallBack);
    }
    public  void updateUuid(int id, String uuid){
        cartRepo.updateUuid(id, uuid);
    }

    public LiveData<List<OrderLines>> getAllOrderLinesItems(){
      return  cartRepo.getAllOrderLinesLiveData();
    }
    public Order getOrder(String uuId){
        return  cartRepo.getOrder(uuId);
    }
    public void insertOrderLines(OrderLines orderLines){
        cartRepo.insertOrderLinesItem(orderLines);
    }
    public List<OrderLines> getOrderLines(int Refid){
        return cartRepo.getOrderLines(Refid);
    }
}
