package com.batch2.minhaz.batchcontact;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewContact extends AppCompatActivity {


    TextView eName;
    TextView ePhone;
    TextView eEmail;
    TextView eAddress;
    Button btnCall;
    Button btnMessage;
    Button btnDelete;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        eName = findViewById(R.id.name);
        ePhone = findViewById(R.id.phone);
        eEmail = findViewById(R.id.email);
        eAddress = findViewById(R.id.address);
        btnCall = findViewById(R.id.btnCall);
        btnMessage = findViewById(R.id.btnMessage);
        btnDelete = findViewById(R.id.btnDelete);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            ArrayList<String> arrList = b.getStringArrayList("contact");
            eName.setText(arrList.get(0));
            ePhone.setText(arrList.get(1));
            eEmail.setText(arrList.get(2));
            eAddress.setText(arrList.get(3));
            id = arrList.get(4);


        }


        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + ePhone.getText()));
                startActivity(callIntent);
            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", ePhone.getText().toString(), null)));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((new DBManager(getApplicationContext())).delContact(id)) {

                    Intent i = new Intent(ViewContact.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putString("Delete", "1");
                    i.putExtras(bundle);
                    Toast.makeText(ViewContact.this, "Contact deleted successfully", Toast.LENGTH_SHORT).show();
                    startActivity(i);


                } else {
                    Toast.makeText(ViewContact.this, "Failed to delete contact", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
