package com.example.vaahanmitr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class advanceActivity extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    EditText e1;
    TextView te1;
    Button B, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance);


        e1 = findViewById(R.id.editText1);
        te1 = findViewById(R.id.textView8);
        B = findViewById(R.id.button);
        b = findViewById(R.id.call);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent callIntent = new Intent(Intent.ACTION_CALL);
                // callIntent.setData(Uri.parse("tel: 9654355140"));
                //if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                //  return;
                //  }

                if (ContextCompat.checkSelfPermission(advanceActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(advanceActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                } else {
                    String dial = "tel:9654355140";
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }
            }
        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                data.rpm = Integer.parseInt(e1.getText().toString());


                if (data.rpm < 10000) {
                    Intent i = new Intent(advanceActivity.this, happy.class);
                    startActivity(i);

                }


            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if (ContextCompat.checkSelfPermission(advanceActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(advanceActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                } else {
                    String dial = "tel:9654355140";
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }
            }
            else
            {
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }
}

