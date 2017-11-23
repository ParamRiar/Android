//Submitted by
//Jaspreet kaur student_id:C0709466
//Paramjeet kaur student _id:C0710778
//Koushal Puliyala student_id:C0709212



package com.example.macstudent.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        String url = "https://api.darksky.net/forecast/9d1dd93eab7022d1ff0d58806e40e8e7/59.911491,10.757933";
        JsonObjectRequest jsObjRequest =
                new JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Check that you went to the correct website
                                // by printing out the response
                                Log.d("", response.toString());

                                // Uncomment this code when you are ready to parse



                try {
                    JSONObject Currently = response.getJSONObject("currently");
                    long time = Currently.getLong("time");
                   // String d2 = Long.toString(time);
                     Date d= new Date(time*1000);

                    SimpleDateFormat d1 = new SimpleDateFormat("h:mma");
                    String formdate =d1.format(d);
                    Log.d("prabh", formdate);
                    TextView time2= (TextView) findViewById(R.id.timelabel);
                    time2.setText("At "  + formdate + " THE TEMPERATURE IS:");

                    double temp = Currently.getDouble("temperature");
                    double  ftemp= (temp - 32)* 5/9 ;
                    String cesl= String.format("%.0f", ftemp);
                    TextView temp3 =(TextView) findViewById(R.id.templabel);
                    temp3.setText(cesl + "\u00b0" + "C");

double hum =Currently.getDouble("humidity")*100;
                    TextView hum1 =(TextView) findViewById(R.id.humlabel);
                    hum1.setText(Double.toString(hum) + "%" );


                    double rain =Currently.getDouble("precipProbability")*100;
                    TextView rain1 =(TextView) findViewById(R.id.rainlabel);
                    rain1.setText(Double.toString(rain) + "%" );

                    String summary = Currently.getString("summary");
                    TextView summary1 = (TextView)findViewById(R.id.summarylabel);
                    summary1.setText(summary);

String icon = Currently.getString("icon");
                    ImageView img=(ImageView)findViewById(R.id.imageicon);

       if(icon.equals("clear-day"))
       {

          img.setImageResource(R.drawable.clear_day);
       }
else if  (icon.equals("clear-night"))
                    {

         img .setImageResource(R.drawable.clear_night);
                    }
       else if  (icon.equals("rain"))
       {

           img .setImageResource(R.drawable.rain);
       }

       else if  (icon.equals("snow"))
       {

           img .setImageResource(R.drawable.snow);
       }
       else if  (icon.equals("sleet"))
       {

           img .setImageResource(R.drawable.sleet);
       }
       else if  (icon.equals("wind"))
       {

           img .setImageResource(R.drawable.wind);
       }
       else if  (icon.equals("cloudy"))
       {

           img .setImageResource(R.drawable.cloudy);
       }
       else if  (icon.equals("partly-cloudy-night"))
       {

           img .setImageResource(R.drawable.cloudy_night);
       }

       else if  (icon.equals("partly-cloudy-day"))
       {

           img .setImageResource(R.drawable.partly_cloudy);
       }
       else{
           Log.d("Debug","Weather icon unknown");
       }
                }
                catch (JSONException e) {
                    Log.d("", "JSON Error occurred");
                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.e("" , error.toString());
                    }
                });
        mRequestQueue.add(jsObjRequest);

    }
}
