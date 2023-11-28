package com.example.shoppingcart.utils.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "product_table")

public class Products_item implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id ;
    @SerializedName("title")
    String title ;
    @SerializedName("price")
    Double price;
    @SerializedName("description")
    String description ;
    @SerializedName("category")
    String category;
    @SerializedName("image")
    String image;

    @SerializedName("rating")
    @Ignore
    Rating rating;


    public Products_item() {
    }

    protected Products_item(Parcel in) {
        id = in.readInt();
        title = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
        description = in.readString();
        category = in.readString();
        image = in.readString();
        rating = in.readParcelable(Rating.class.getClassLoader());
    }

    public static final Creator<Products_item> CREATOR = new Creator<Products_item>() {
        @Override
        public Products_item createFromParcel(Parcel in) {
            return new Products_item(in);
        }

        @Override
        public Products_item[] newArray(int size) {
            return new Products_item[size];
        }
    };

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        if (price == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(price);
        }
        parcel.writeString(description);
        parcel.writeString(category);
        parcel.writeString(image);
        parcel.writeParcelable(rating,PARCELABLE_WRITE_RETURN_VALUE);
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                ", rating=" + rating +
                '}';
    }
}
