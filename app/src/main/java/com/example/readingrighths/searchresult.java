package com.example.readingrighths;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class searchresult extends AppCompatActivity {
    RecyclerView recyclerView;
    List<food> meal;
    String url;
    mAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        recyclerView=findViewById(R.id.rv);
        String search=getIntent().getStringExtra("search");
        url="https://www.themealdb.com/api/json/v1/1/search.php?s="+search;
        meal=new ArrayList<>();
        extractmeal();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter=new mAdapter(getApplicationContext(), meal);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

    }

    public void extractmeal(){
        final ProgressDialog pd=new ProgressDialog(searchresult.this);
        pd.setTitle("Loading");
        pd.show();

        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonArrayRequest=new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray= (JSONArray) response.get("meals");
                    Log.d("url ye h", jsonArray.toString());
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject fd = jsonArray.getJSONObject(i);
                        food f = new food();
                        f.setName(fd.getString("strMeal").toString());
                        f.setImageUrl(fd.getString("strMealThumb").toString());
                        f.setCategory(fd.getString("strCategory").toString());
                        f.setInstructions(fd.getString("strInstructions").toString());
                        meal.add(f);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("json error",e.getMessage());
                }
                pd.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley",error.getMessage());
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
}