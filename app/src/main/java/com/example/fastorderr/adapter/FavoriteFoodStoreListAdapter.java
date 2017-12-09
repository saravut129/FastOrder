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
 * Created by Friday on 12/9/2017.
 */

public class FavoriteFoodStoreListAdapter extends ArrayAdapter<FoodStoreItem> {

    private Context mContext3;
    private int mLayoutResId3;
    private ArrayList<FoodStoreItem> mFoodStoreList3;

    public FavoriteFoodStoreListAdapter(Context context, int resource, ArrayList<FoodStoreItem> objects){
        super(context, resource, objects);

        this.mContext3 = context;
        this.mLayoutResId3 = resource;
        this.mFoodStoreList3 = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext3);

        View v = convertView;
        if(v == null){
            v = inflater.inflate(mLayoutResId3, null);
        }

        ImageView iv1 = v.findViewById(R.id.imageView1);
        TextView tv1 = v.findViewById(R.id.textView4);
        TextView tv2 = v.findViewById(R.id.textView5);

        FoodStoreItem foodStoreItem = mFoodStoreList3.get(position);


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

        AssetManager am = mContext3.getAssets();
        try {
            InputStream stream = am.open(pictureFileName);
            Drawable drawable = Drawable.createFromStream(stream, null);
            iv1.setImageDrawable(drawable);

        } catch (IOException e) {
            e.printStackTrace();

            File pictureFile = new File(mContext3.getFilesDir(), pictureFileName);
            Drawable drawable = Drawable.createFromPath(pictureFile.getAbsolutePath());
            iv1.setImageDrawable(drawable);
        }



        return v;
    }
}
