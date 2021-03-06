package com.example.fastorderr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fastorderr.Model.CategoryItem;
import com.example.fastorderr.adapter.CategoryListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private CategoryListAdapter mAdapter;

    public  ArrayList<CategoryItem> foodStoreLists = new ArrayList<>();
    private ArrayList<CategoryItem> mCategory = foodStoreLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);

        if (savedInstanceState == null) {
            mCategory.add(new CategoryItem("ร้านอาหารแนะนำ"));
            mCategory.add(new CategoryItem("ร้านอาหารตามสั่ง"));
            mCategory.add(new CategoryItem("ร้านอาหารฟาสฟู๊ด"));
            mCategory.add(new CategoryItem("ร้านชาบู/หมูกระทะ"));
            mCategory.add(new CategoryItem("ร้านโปรดของฉัน"));
        }

        mAdapter = new CategoryListAdapter(
                this,
                R.layout.item1,
                mCategory
        );

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CategoryItem ci = mCategory.get(i);
                Toast.makeText(MainActivity.this, ci.name, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                if(i == 0) {
                     intent = new Intent(MainActivity.this, FoodStoreListsActivity.class);
                }else if (i==1){
                     intent = new Intent(MainActivity.this,TamSungActivity.class);
                }else if (i==2){
                    intent = new Intent(MainActivity.this,FastFoodActivity.class);
                }else if (i==3){
                    intent = new Intent(MainActivity.this,ChaBuActivity.class);
                }else if (i==4){
                    intent = new Intent(MainActivity.this,FavouriteFoodStore.class);
                }

                intent.putExtra("pos", i);
                startActivity(intent);
            }
        });
    }


}
