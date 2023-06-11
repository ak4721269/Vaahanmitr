package com.example.vaahanmitr;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity  {
    public static final String EXTRA_NUMBER = "com.example.vaahanmitr";
    private static final String TAG = "UsingThingspeakAPI";
    private static final String THINGSPEAK_CHANNEL_ID = "743888";
    private static final String THINGSPEAK_API_KEY = "YSL1JPERVFIOGE48"; //GARBAGE KEY
    private static final String THINGSPEAK_API_KEY_STRING = "YSL1JPERVFIOGE48";
    private static final String THINGSPEAK_FIELD1 = "field1";
    private static final String THINGSPEAK_FIELD2 = "field2";
    private static final String THINGSPEAK_UPDATE_URL = "https://api.thingspeak.com/update?";
    private static final String THINGSPEAK_CHANNEL_URL = "https://api.thingspeak.com/channels/";
    private static final String THINGSPEAK_FEEDS_LAST = "/feeds/last?";
    TextView t1,t2,t3,t4;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView)findViewById(R.id.textView2);
        t2=(TextView)findViewById(R.id.textView3);
        t3=(TextView)findViewById(R.id.textView4);
        t4=(TextView)findViewById(R.id.textView5);
        b1=(Button) findViewById(R.id.button);
        b2=(Button) findViewById(R.id.button2);
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new FetchThingspeakTask().execute();
                }
                catch(Exception e){
                    Log.e("ERROR", e.getMessage(), e);
                }
            }
        });
    }
    class FetchThingspeakTask extends AsyncTask<Void, Void, String> {
        protected void onPreExecute() {
            t2.setText("Fetching Data from Server.Please Wait...");
        }
        protected String doInBackground(Void... urls) {
            try {
                URL url = new URL(THINGSPEAK_CHANNEL_URL + THINGSPEAK_CHANNEL_ID +
                        THINGSPEAK_FEEDS_LAST + THINGSPEAK_API_KEY_STRING + "=" +
                        THINGSPEAK_API_KEY + "");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }
        protected void onPostExecute(String response) {
            if(response == null) {
                Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                JSONObject channel = (JSONObject) new JSONTokener(response).nextValue();
                data.v1 = channel.getInt(THINGSPEAK_FIELD1);
                data.v2 = channel.getDouble(THINGSPEAK_FIELD2);
                if(data.v1>=90) {
                    t1.setText("Carbon monoxide emission : " + Integer.toString(data.v1) + " ppm");
                    t3.setText("Engine exhaust temperature : " + Double.toString(data.v2) + " °C");
                    if(data.v1<= 900 && data.v2<=150) {
                        t4.setText("Vehicle's engine is in good condition");
                    }
                    else
                    {
                        t4.setText("Vehicle's engine is not in good condition");
                    }

                    t2.setText("");
                } else
                    t1.setText("NO VALUES");

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    public void openActivity()
    {
        int carb = data.v1;
        double temp = data.v2;
        Intent intent = new Intent(this,advanceActivity.class);
        intent.putExtra("carb",data.v1);
        intent.putExtra("v2",data.v2);
        startActivity(intent);

    }
}