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

public class FoodStoreListAdapter extends ArrayAdapter<FoodStoreItem> {

    private Context mContext2;
    private int mLayoutResId2;
    private ArrayList<FoodStoreItem> mFoodStoreList2;

    public FoodStoreListAdapter(Context context, int resource, ArrayList<FoodStoreItem> objects){
        super(context, resource, objects);

        this.mContext2 = context;
        this.mLayoutResId2 = resource;
        this.mFoodStoreList2 = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext2);

        View v = convertView;
        if(v == null){
            v = inflater.inflate(mLayoutResId2, null);
        }

        ImageView iv1 = v.findViewById(R.id.imageView);
        TextView tv1 = v.findViewById(R.id.textView2);
        TextView tv2 = v.findViewById(R.id.textView3);

        FoodStoreItem foodStoreItem = mFoodStoreList2.get(position);


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

        AssetManager am = mContext2.getAssets();
        try {
            InputStream stream = am.open(pictureFileName);
            Drawable drawable = Drawable.createFromStream(stream, null);
            iv1.setImageDrawable(drawable);

        } catch (IOException e) {
            e.printStackTrace();

            File pictureFile = new File(mContext2.getFilesDir(), pictureFileName);
            Drawable drawable = Drawable.createFromPath(pictureFile.getAbsolutePath());
            iv1.setImageDrawable(drawable);
        }



        return v;
    }
}
