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

public class happy extends AppCompatActivity {
    EditText e1;
    TextView te1;
    Button T, t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy);
        e1 = findViewById(R.id.editText1);
        te1 = findViewById(R.id.textView8);
        T = findViewById(R.id.button);



        T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                data.mileage = Integer.parseInt(e1.getText().toString());


                if (data.mileage > 25) {
                    Intent i = new Intent(happy.this, kilometer.class);
                    startActivity(i);
                }
                else{
                    te1.setText("Get your vehicle's battery condition and relay checked");
                }



            }
        });


    }
}
