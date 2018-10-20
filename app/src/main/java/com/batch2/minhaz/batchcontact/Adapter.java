package com.batch2.minhaz.batchcontact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.List;

public class Adapter extends ArrayAdapter<Contacts> {

    Context mCtx;
    int listLayoutRes;
    List<Contacts> contactsList;
    SQLiteDatabase mDatabase;

    public Adapter(Context context, int resource, Context mCtx, int listLayoutRes, List<Contacts> contactsList, SQLiteDatabase mDatabase) {
        super(context, resource);
        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.contactsList = contactsList;
        this.mDatabase = mDatabase;
    }

}
