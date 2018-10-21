package com.batch2.minhaz.batchcontact;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<Contacts> {

    Context mCtx;
    int listLayoutRes;
    ArrayList<Contacts> contactsList;

    public Adapter(Context context, int resource, ArrayList<Contacts> contactsList) {
        super(context, resource);
        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.contactsList = contactsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);

        final Contacts employee = contactsList.get(position);


        TextView textViewName = view.findViewById(R.id.name);
        TextView textViewPhone = view.findViewById(R.id.phone);
        TextView textViewEmail = view.findViewById(R.id.email);
        TextView textViewAddress = view.findViewById(R.id.address);


        textViewName.setText(employee.getName());
        textViewPhone.setText(employee.getPhone());
        textViewEmail.setText(String.valueOf(employee.getEmail()));
        textViewAddress.setText(employee.getAddress());


        Button buttonCall = view.findViewById(R.id.call);
        Button buttonMessage = view.findViewById(R.id.message);
        Button buttonWeb = view.findViewById(R.id.website);

        return view;
    }


}
