package com.example.shoppingcart.utils.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity(tableName =  "Product_Cart")
public class Product_Cart {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id ;
    @ColumnInfo(name = "title")
    public String title ;
    @ColumnInfo(name = "price")
    public double price;
    @ColumnInfo(name = "image")
    public  String image;
    @ColumnInfo(name = "quantity")
    public int quantity;
    @ColumnInfo(name = "totalItemPrice")
    public double totalItemPrice;





    public Product_Cart(int id, String title, double price, String image, int quantity) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.totalItemPrice=totalItemPrice;
    }

    public Product_Cart() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(double totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }
}
