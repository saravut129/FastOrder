package com.example.fastorderr.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fastorderr.Model.FoodStoreItem;
import com.example.fastorderr.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Admin on 4/12/2560.
 */

public class ChaBuAdapter extends ArrayAdapter<FoodStoreItem> {

    private Context mContext5;
    private int mLayoutResId5;
    private ArrayList<FoodStoreItem> mFoodStoreList5;

    public ChaBuAdapter(Context context, int resource, ArrayList<FoodStoreItem> objects){
        super(context, resource, objects);

        this.mContext5 = context;
        this.mLayoutResId5 = resource;
        this.mFoodStoreList5 = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext5);

        View v = convertView;
        if(v == null){
            v = inflater.inflate(mLayoutResId5, null);
        }

        ImageView iv1 = v.findViewById(R.id.imageView4);
        TextView tv1 = v.findViewById(R.id.textView10);
        TextView tv2 = v.findViewById(R.id.textView11);

        FoodStoreItem foodStoreItem = mFoodStoreList5.get(position);


        tv1.setText(
                String.format(
                        Locale.getDefault(),
                        "%s",
                        foodStoreItem.thaiName
                )
        );
        tv2.setText(
                String.format(
                        Locale.getDefault(),
                        "%s",
                        foodStoreItem.number
                )
        );

        String pictureFileName = foodStoreItem.picture;

        AssetManager am = mContext5.getAssets();
        try {
            InputStream stream = am.open(pictureFileName);
            Drawable drawable = Drawable.createFromStream(stream, null);
            iv1.setImageDrawable(drawable);

        } catch (IOException e) {
            e.printStackTrace();

            File pictureFile = new File(mContext5.getFilesDir(), pictureFileName);
            Drawable drawable = Drawable.createFromPath(pictureFile.getAbsolutePath());
            iv1.setImageDrawable(drawable);
        }



        return v;
    }
}