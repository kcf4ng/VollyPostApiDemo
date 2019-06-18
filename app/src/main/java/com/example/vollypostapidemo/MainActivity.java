package com.example.vollypostapidemo;

import android.content.Context;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy l_policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(l_policy);
        HttpPostjson();
    }

    public void HttpPostjson() {
        Context mContext;
        RequestQueue mRequestQueue;
        StringRequest mStringRequest;
        String url = "http://192.168.0.162/IMOR/json/APP01-pushCheckIn.ashx";
        mContext = getApplicationContext();
        mRequestQueue = Volley.newRequestQueue(mContext);
        mStringRequest = new StringRequest(Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG,"請求結果："+response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,"onErrorResponse : call");
                    }
                }) {
            // 携带参数
            @Override
            protected HashMap<String, String> getParams()
                    throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("OP_NO1", "3345678");
                params.put("CHECKINUSER", "3345678");
//                JSONObject JSONObject = new JSONObject(params);
//                String MSG=JSONObject.toString();
//                HashMap<String, String> paramMSG= new HashMap<String, String>();
//                paramMSG.put("MSG", MSG);
                return params;
            }

//            @Override
//            public String getBodyContentType() {
//                return "application/x-www-form-urlencoded";
//            }
//
//            @Override
//            public Map<String, String> getHeaders(){
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/x-www-form-urlencoded");
//                return headers;
//            }

        };

        mRequestQueue.add(mStringRequest);
    }


//    public Map<String,String> getHeaders() throws AuthFailureError {
//        HashMap headers = new HashMap<String, String>();
//        headers.put("Content-Type", "application/x-www-form-urlencoded");
//        return headers;
//    }
}
