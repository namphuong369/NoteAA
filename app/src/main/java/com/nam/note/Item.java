package com.nam.note;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private int id;
    private String content;

    public Item() {
    }

    public Item(String content) {
        this.content = content;
    }

    public Item(int id, String content) {
        this.id = id;
        this.content = content;
    }

    protected Item(Parcel in) {
        id = in.readInt();
        content = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(content);
    }
}
