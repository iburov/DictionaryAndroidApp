package com.ivanburov.dictionaryandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ivanburov.dictionaryandroidapp.adapter.RecyclerViewAdapter;
import com.ivanburov.dictionaryandroidapp.data.WordBank;
import com.ivanburov.dictionaryandroidapp.data.WordListAsyncResponse;
import com.ivanburov.dictionaryandroidapp.model.Word;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);

        final String URL = "https://my-json-server.typicode.com/cgerard321/dictionary/dictionary";
        final ArrayList<Word> wordArrayList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0; i<response.length(); i++) {
                            try {
                                Word word = new Word();
                                word.setWord(response.getJSONObject(i).getString("word"));
                                word.setPos(response.getJSONObject(i).getString("pos"));
                                word.setDefinition(response.getJSONObject(i).getString("definition"));
                                word.setFrench(response.getJSONObject(i).getString("french"));
                                wordArrayList.add(word);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), wordArrayList);
                        recyclerView.setAdapter(recyclerViewAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("JSONArray Error", "onErrorResponse: " + error );
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
    }
}
