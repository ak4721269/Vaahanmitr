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

public class timeservice extends AppCompatActivity {
    EditText e1;
    TextView te1;
    Button B, b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeservice);
        e1 = findViewById(R.id.editText1);
        te1 = findViewById(R.id.textView8);
        B = findViewById(R.id.button);


        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                data.time = Integer.parseInt(e1.getText().toString());


                if (data.time <= 105) {
                    Intent i = new Intent(timeservice.this, good.class);
                    startActivity(i);
                }
                else if(data.time > 105 && data.time<=202 ){
                 //   Intent r = new Intent(timeservice.this,refuel.class);
                    e1.setText("");
                    te1.setText("Refuel oil");
                }
                else if(data.time > 202)
                {
                    e1.setText("");
                    te1.setText("Service due");
                }


            }
        });



    }




}



