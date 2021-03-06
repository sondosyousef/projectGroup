package com.example.projectgroup;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

public class section extends AppCompatActivity {

    List<GetDataAdapter> arrayList;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    String GET_JSON_DATA_HTTP_URL = "http://10.0.2.2:84/HeritageSection/info1.php";
    String JSON_IMAGE_TITLE_NAME = "image_title";
    String JSON_DESCRIPTION = "description";
    String JSON_IMAGE_URL = "image_url";
    String JSON_IMAGE1 = "imge1";
    String JSON_IMAGE2 = "imge2";
    String JSON_text1 = "text1";
    String JSON_text2 = "text2";

    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_section);
        Button back = findViewById(R.id.back_home);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHomePage();
            }
        });

        arrayList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);


        JSON_DATA_WEB_CALL();


    }

    private void backHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void JSON_DATA_WEB_CALL(){

        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            GetDataAdapter GetDataAdapter2 = new GetDataAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);


                GetDataAdapter2.setImageTitleNamee(json.getString(JSON_IMAGE_TITLE_NAME));
                GetDataAdapter2.setDescription(json.getString(JSON_DESCRIPTION));
                GetDataAdapter2.setImageServerUrl(json.getString(JSON_IMAGE_URL));
                GetDataAdapter2.setImg1(json.getString(JSON_IMAGE1));
                GetDataAdapter2.setImg2(json.getString(JSON_IMAGE2));
                GetDataAdapter2.setText1(json.getString(JSON_text1));
                GetDataAdapter2.setText2(json.getString(JSON_text2));



            } catch (JSONException e) {

                e.printStackTrace();
            }
            arrayList.add(GetDataAdapter2);

        }

        recyclerViewadapter = new RecycleViewAdapter(arrayList, this);

        recyclerView.setAdapter(recyclerViewadapter);


    }
}