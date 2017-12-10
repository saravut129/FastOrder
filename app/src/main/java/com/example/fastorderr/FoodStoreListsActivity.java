package com.example.fastorderr;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fastorderr.Model.FoodStoreItem;
import com.example.fastorderr.adapter.FoodStoreListAdapter;
import com.example.fastorderr.db.PhoneDb;

import java.util.ArrayList;
import java.util.Locale;

public class FoodStoreListsActivity extends AppCompatActivity {

    private ListView mListView2;
    private PhoneDb mHelper;
    private SQLiteDatabase mDb;
    public static ArrayList<FoodStoreItem> aList2 = new ArrayList<>();
    private FoodStoreListAdapter mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_store_lists);
        mHelper = new PhoneDb(this);
        mDb = mHelper.getReadableDatabase();

        loadDataFromDb();

        mAdapter2 = new FoodStoreListAdapter(
                this,
                R.layout.item2,
                aList2
        );
        mListView2 = (ListView) findViewById(R.id.listView2);
        mListView2.setAdapter(mAdapter2); //123456



        getSupportActionBar().setTitle(
                String.format(
                        Locale.getDefault(),
                        "%s",
                        "General"
                )
        );
        mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                FoodStoreItem item = aList2.get(position);
                String phoneNumber = item.number;

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);

            }
        });

    }

    private void loadDataFromDb() {
        Cursor cursor = mDb.query(
                PhoneDb.TABLE_NAME_REC,
                null,
                null,
                null,
                null,
                null,
                null
        );

        aList2.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(PhoneDb.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(PhoneDb.COL_TITLE));
            String number = cursor.getString(cursor.getColumnIndex(PhoneDb.COL_NUMBER));
            String picture = cursor.getString(cursor.getColumnIndex(PhoneDb.COL_PICTURE));

            FoodStoreItem item = new FoodStoreItem(id, title, number, picture);
            aList2.add(item);
        }
    }
}
