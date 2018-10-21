package com.batch2.minhaz.batchcontact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Contacts> implements View.OnClickListener {
    private ArrayList<Contacts> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView name;
        TextView phone;
    }

    public Adapter(ArrayList<Contacts> data, Context context) {
        super(context, R.layout.list_view_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Contacts contact = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_view_item, parent, false);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final Intent i = new Intent(getContext(), ViewContact.class);
                    Bundle b = new Bundle();
                    ArrayList<String> arrList = new ArrayList<>();
                    arrList.add(contact.getName());
                    arrList.add(contact.getPhone());
                    arrList.add(contact.getEmail());
                    arrList.add(contact.getAddress());
                    arrList.add(Integer.toString(contact.getId()));
                    b.putStringArrayList("contact", arrList);
                    i.putExtras(b);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);

                }
            });

            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.phone = (TextView) convertView.findViewById(R.id.phone);
            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        viewHolder.name.setText(contact.getName());
        viewHolder.phone.setText(contact.getPhone());

        // Return the completed view to render on screen
        return convertView;
    }
}
