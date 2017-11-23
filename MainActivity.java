package com.example.macstudent.currencyconversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }
public void searchbtnpreesed(View v){
    String url = "https://api.fixer.io/latest?base=CAD&symbols=INR";
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
                    JSONObject rates = response.getJSONObject("rates");
                    double INR = rates.getDouble("INR");
                  //  Log.d("Prabh",Double.toString(INR));
                    EditText value =(EditText)findViewById(R.id.valueEntered);
                    String x = value.getText().toString();
                    double x1 = Double.parseDouble(x);
                    double amountEntered =  x1;
                    double cad = INR * amountEntered;
                    //Log.d("Prabh",Double.toString(cad));
                    TextView result =(TextView) findViewById(R.id.label);
                    result.setText(Double.toString(cad));

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
