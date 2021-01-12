package com.example.readingrighths;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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

public class first extends AppCompatActivity {
    EditText et_search;
    TextView tv_name,tv_cat,tv_inst;
    Button bt_search;
    ImageView iv_food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        et_search=findViewById(R.id.searchbar);
        bt_search=findViewById(R.id.searchbutton);
        tv_cat=findViewById(R.id.food_cat);
        tv_inst=findViewById(R.id.food_inst);
        tv_name=findViewById(R.id.food_name);
        iv_food=findViewById(R.id.food_img);
        showrandom();

        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_search.getText().toString().length() == 0){
                    Toast.makeText(first.this, "search string too short", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i= new Intent(first.this, searchresult.class);
                    i.putExtra("search",et_search.getText().toString());
                    startActivity(i);
                }
            }
        });
    }
    public void showrandom(){
        final ProgressDialog pd=new ProgressDialog(first.this);
        pd.setTitle("Loading");
        pd.show();

        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonArrayRequest=new JsonObjectRequest(Request.Method.GET,
                "https://www.themealdb.com/api/json/v1/1/random.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray= (JSONArray) response.get("meals");
                    JSONObject fd = jsonArray.getJSONObject(0);
                    food f = new food();
                    f.setName(fd.getString("strMeal").toString());
                    f.setImageUrl(fd.getString("strMealThumb").toString());
                    f.setCategory(fd.getString("strCategory").toString());
                    f.setInstructions(fd.getString("strInstructions").toString());

                    tv_cat.setText(f.getCategory());
                    tv_name.setText(f.getName());
                    tv_inst.setText(f.getInstructions());
                    Glide.with(getApplicationContext())
                            .load(f.getImageUrl())
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    pd.dismiss();
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    pd.dismiss();
                                    return false;
                                }
                            })
                            .into(iv_food);
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