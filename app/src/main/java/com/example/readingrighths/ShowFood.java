package com.example.readingrighths;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class ShowFood extends AppCompatActivity {
    TextView tv_name,tv_cat,tv_inst;
    ImageView iv_food;
    Button add_fav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food);
        tv_cat=findViewById(R.id.food_cat);
        tv_inst=findViewById(R.id.food_inst);
        add_fav=findViewById(R.id.fav);
        tv_name=findViewById(R.id.food_name);
        iv_food=findViewById(R.id.food_img);
        tv_name.setText(getIntent().getStringExtra("name").toString());
        tv_inst.setText(getIntent().getStringExtra("inst").toString());
        tv_cat.setText(getIntent().getStringExtra("cat").toString());
        Glide.with(this)
                .load(getIntent().getStringExtra("image").toString())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(iv_food);

        add_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_fav.setVisibility(View.INVISIBLE);
            }
        });
    }
}