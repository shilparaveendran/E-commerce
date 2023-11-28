package com.example.shoppingcart.utils.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Rating implements Parcelable {
    @SerializedName("rate")
    Double rate;
    @SerializedName("count")
    int count;

    protected Rating(Parcel in) {
        if (in.readByte() == 0) {
            rate = null;
        } else {
            rate = in.readDouble();
        }
        count = in.readInt();
    }

    public static final Creator<Rating> CREATOR = new Creator<Rating>() {
        @Override
        public Rating createFromParcel(Parcel in) {
            return new Rating(in);
        }

        @Override
        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (rate == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(rate);
        }
        parcel.writeInt(count);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "rate=" + rate +
                ", count=" + count +
                '}';
    }
}
