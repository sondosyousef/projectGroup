package com.example.projectgroup;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.GenericLifecycleObserver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class description extends AppCompatActivity {

    private static final String TAG ="Description" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();
        Button b = findViewById(R.id.btn_back);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGoBack();
            }
        });
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("image_Url") && getIntent().hasExtra("image_name")&&getIntent().hasExtra("image_dec")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String imageUrl = getIntent().getStringExtra("image_Url");
            String imageName = getIntent().getStringExtra("image_name");
            String desc = getIntent().getStringExtra("image_dec");

            setImage(imageUrl, imageName,desc);
        }
    }


    private void setImage(String imageUrl, String imageName,String dec){
        Log.d(TAG, "setImage: setting te image and name to widgets.");

        TextView name = findViewById(R.id.name);
        name.setText(imageName);

        ImageView image = findViewById(R.id.image);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
        TextView description = findViewById(R.id.description);
        description.setText(dec);

    }

    public void setGoBack(){
        Intent intent = new Intent(this ,section.class);
        startActivity(intent);
    }


    }



