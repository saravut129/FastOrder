package com.example.fastorderr.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fastorderr.Model.CategoryItem;
import com.example.fastorderr.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Admin on 2/12/2560.
 */

public class CategoryListAdapter extends ArrayAdapter<CategoryItem> {

    private Context mContext;
    private int mLayoutResId;
    private ArrayList<CategoryItem> mCategoryList;

    public CategoryListAdapter(Context context, int layoutResId, ArrayList<CategoryItem> mCategoryList){
        super(context, layoutResId, mCategoryList);

        this.mContext = context;
        this.mLayoutResId = layoutResId;
        this.mCategoryList = mCategoryList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(mLayoutResId, null);

        TextView tv = v.findViewById(R.id.textView);

        CategoryItem categoryItem = mCategoryList.get(position);

        tv.setText(
                String.format(
                        Locale.getDefault(),
                        "%s",
                        categoryItem.name
                )
        );
        return v;
    }
}
