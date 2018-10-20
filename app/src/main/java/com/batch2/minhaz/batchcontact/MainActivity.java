package com.batch2.minhaz.batchcontact;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewConfiguration;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String DATABASE_NAME = "database.sqlite";

    List<Contacts> contactsList;
    SQLiteDatabase mDatabase;
    ListView contactListView;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactListView = findViewById(R.id.contactListView);
        contactsList = new ArrayList<>();

        mDatabase = openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE,null);
        showContactsFromDatabase();

    }

    private void showContactsFromDatabase() {

        Cursor cursorContacts = mDatabase.rawQuery("SELECT * FROM contacts",null);

        if(cursorContacts.moveToFirst()){
            do {
             contactsList.add(new Contacts(
                     cursorContacts.getInt(0),
                     cursorContacts.getString(1),
                     cursorContacts.getString(2),
                     cursorContacts.getString(3),
                     cursorContacts.getString(4),
                     cursorContacts.getString(5),
                     cursorContacts.getString(6)
             ));
            }while (cursorContacts.moveToNext());
        }

        cursorContacts.close();

        adapter = new Adapter(this,R.layout.view_contact,contactsList,mDatabase);

        contactListView.setAdapter(adapter);

    }
}

