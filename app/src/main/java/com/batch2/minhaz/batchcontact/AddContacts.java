package com.batch2.minhaz.batchcontact;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class AddContacts extends AppCompatActivity {
    private static final int ADDCONTACTS = 577;
    TextView eName;
    TextView ePhone;
    TextView eEmail;
    TextView eAddress;
    Button bSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        eName = findViewById(R.id.entryName);
        ePhone = findViewById(R.id.entryPhone);
        eEmail = findViewById(R.id.entryEmail);
        eAddress = findViewById(R.id.entryAddress);

        bSave = findViewById(R.id.btnSave);


        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = eName.getText().toString();
                String phone = ePhone.getText().toString();
                String email = eEmail.getText().toString();
                String address = eAddress.getText().toString();

                long res = (new DBManager(getApplicationContext())).addContact(name, phone, email, address);
                if (res != -1) {
                    setResult(Activity.RESULT_OK, null);
                    finish();
                } else {
                    setResult(Activity.RESULT_CANCELED, null);
                    finish();
                }

            }
        });
    }


}
