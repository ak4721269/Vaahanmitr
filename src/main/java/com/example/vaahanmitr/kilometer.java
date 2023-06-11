package com.example.vaahanmitr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class kilometer extends AppCompatActivity {
    EditText e1;
    TextView te1;
    Button B, b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kilometer);
        e1 = findViewById(R.id.editText1);
        te1 = findViewById(R.id.textView8);
        B = findViewById(R.id.button);


        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                data.kilometers = Integer.parseInt(e1.getText().toString());


                if (data.kilometers <= 10000) {
                    Intent i = new Intent(kilometer.this, timeservice.class);
                    startActivity(i);

                }
                 else
                    {
                        te1.setText("Recommended replacement of Brake shoe  and "
                                + "tyres check and "
                                + "tubes, wires check");
                        e1.setText("");

                    }
                }





        });



    }




}




