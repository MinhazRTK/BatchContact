package com.batch2.minhaz.batchcontact;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper{

    private static final String DBNAME = "database.sqlite";
    private static String DBLOCATION = "";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DBManager(Context context) {
        super(context, DBNAME, null, 4);
        this.mContext = context;
        DBLOCATION = context.getApplicationInfo().dataDir + "/databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



    public void OpenDatabase()
    {
        String dbPath = DBLOCATION + DBNAME;
        if(mDatabase != null && mDatabase.isOpen())
        {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);


    }
    public void CloseDatabase(){

        if(mDatabase != null)
        {
            mDatabase.close();
        }
    }

    public boolean CopyDB()
    {

        try {
            InputStream IS = this.mContext.getAssets().open(DBNAME);
            String OF = DBLOCATION + DBNAME;
            File f = new File(OF);
            if(f.exists())
            {
                Log.d("DatabaseHelper","Database already exists in the "+OF+" directory");
                f.delete();
                f.createNewFile();
            }
            else
            {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            OutputStream OS = new FileOutputStream(OF,true);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = IS.read(buff))>0)
            {
                OS.write(buff,0,length);
            }
            OS.flush();
            OS.close();
            Log.d("DatabaseHelper","Database copied successfully");
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.d("DatabaseHelper","Copy database failed with message: " + e.getMessage());
            return false;
        }
    }

    ArrayList<Contacts> getContacts(){
        OpenDatabase();
        ArrayList<Contacts> tmpStr = new ArrayList<>();


        Cursor cur = mDatabase.rawQuery("SELECT * FROM contacts" ,null);
        cur.moveToFirst();

        for(int i =0;i<cur.getCount();i++)
        {
            tmpStr.add(new Contacts(cur.getInt(0),cur.getString(1),cur.getString(2),cur.getString(3),cur.getString(4),cur.getString(5),cur.getString(6)));

            cur.moveToNext();
        }
        cur.close();

        CloseDatabase();
        return tmpStr;
    }

}
