package com.example.fastorderr.Model;

/**
 * Created by Admin on 2/12/2560.
 */

public class FoodStoreItem {
    public final int id;
    public final String thaiName;
    public final String picture;
    public final String number;

    public FoodStoreItem(int id, String thaiName, String number, String picture) {
        this.id = id;
        this.thaiName = thaiName;
        this.number = number;
        this.picture = picture;
    }
}
