package com.batch2.minhaz.batchcontact;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ArrayList<Contacts> contactsList;
    ListView contactListView;
    Adapter adapter;
    private static final int ADDCONTACTS = 577;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("Delete") != null) {
                Toast.makeText(this, "Contact deleted successfully", Toast.LENGTH_SHORT);
            }
        }

        contactListView = findViewById(R.id.contactListView);
        contactsList = (new DBManager(this)).getContacts();

        adapter = new Adapter(contactsList, getApplicationContext());

        contactListView.setAdapter(adapter);

        floatingActionButton = findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddContacts.class);
                startActivityForResult(i, ADDCONTACTS);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADDCONTACTS) {
            if (resultCode == Activity.RESULT_OK) {
                contactListView.setAdapter(null);
                contactsList = (new DBManager(this)).getContacts();
                adapter = new Adapter(contactsList, getApplicationContext());
                contactListView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(), "Contact added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Failed to add contact", Toast.LENGTH_SHORT).show();
            }


        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder ealert = new AlertDialog.Builder(MainActivity.this);
        ealert.setTitle("Exit Application");
        ealert.setMessage("Are you sure want to close the application?");
        ealert.setCancelable(true);
        ealert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        ealert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = ealert.create();
        alertDialog.show();
    }
}

