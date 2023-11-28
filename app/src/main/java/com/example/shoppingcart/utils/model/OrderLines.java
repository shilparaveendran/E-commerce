package com.example.shoppingcart.utils.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;



@Entity(tableName = "OrderLines")
//        foreignKeys = {@ForeignKey(entity = Order.class,
//        parentColumns = " id ",
//        childColumns = "RefId",
//        onDelete = ForeignKey.CASCADE

public class OrderLines {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id ;
    @ColumnInfo(name = "title")
    public String title ;
    @ColumnInfo(name = "image")
    public  String image;
    @ColumnInfo(name = "price")
    public double price;
    @ColumnInfo(name = "quantity")
    public int quantity;
    @ColumnInfo(name = "totalIemPrice")
    public  double totalIemPrice;
    @ColumnInfo(name = "RefId")
    public  int RefId;

    public OrderLines() {
    }

    public OrderLines(int id, String title, String image, double price, int quantity, double totalIemPrice, int refId) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.totalIemPrice = totalIemPrice;
        RefId = refId;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalIemPrice() {
        return totalIemPrice;
    }

    public void setTotalIemPrice(double totalIemPrice) {
        this.totalIemPrice = totalIemPrice;
    }

    public int getRefId() {
        return RefId;
    }

    public void setRefId(int refId) {
        RefId = refId;
    }
}
