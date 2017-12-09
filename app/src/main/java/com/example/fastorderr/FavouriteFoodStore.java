package com.example.fastorderr;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.fastorderr.Model.FoodStoreItem2;
import com.example.fastorderr.adapter.FavoriteFoodStoreListAdapter;
import com.example.fastorderr.db.PhoneDb;

import java.util.ArrayList;
import java.util.Locale;

public class FavouriteFoodStore extends AppCompatActivity {
    private ListView mListView3;
    private PhoneDb mHelper;
    private SQLiteDatabase mDb;
    public static ArrayList<FoodStoreItem2> aList3 = new ArrayList<>();
    private FavoriteFoodStoreListAdapter mAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_food_store);
        mHelper = new PhoneDb(this);
        mDb = mHelper.getReadableDatabase();

        loadDataFromDb();
      //  mAdapter3.notifyDataSetChanged();
        mAdapter3 = new FavoriteFoodStoreListAdapter(
                this,
                R.layout.item3,
                aList3
        );
        mListView3 = (ListView) findViewById(R.id.listView3);
        mListView3.setAdapter(mAdapter3);
        getSupportActionBar().setTitle(
                String.format(
                        Locale.getDefault(),
                        "%s",
                        "Favourite"
                )
        );
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                loadDataFromDb();
                mAdapter3.notifyDataSetChanged();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();


        switch (itemId) {
            case R.id.action_add:
                addFoodStore();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addFoodStore() {
        Intent intent = new Intent(FavouriteFoodStore.this, AddFoodStoreActivity.class);
        startActivity(intent);
    }

    private void loadDataFromDb() {
        Cursor cursor = mDb.query(
                PhoneDb.TABLE_NAME_FAV,
                null,
                null,
                null,
                null,
                null,
                null
        );

        aList3.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(PhoneDb.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(PhoneDb.COL_TITLE));
            String number = cursor.getString(cursor.getColumnIndex(PhoneDb.COL_NUMBER));
            String picture = cursor.getString(cursor.getColumnIndex(PhoneDb.COL_PICTURE));

            FoodStoreItem2 item = new FoodStoreItem2(id, title, number, picture);
            aList3.add(item);
        }
    }
}


