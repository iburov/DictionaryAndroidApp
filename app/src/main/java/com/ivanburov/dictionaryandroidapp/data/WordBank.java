package com.ivanburov.dictionaryandroidapp.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ivanburov.dictionaryandroidapp.controller.AppController;
import com.ivanburov.dictionaryandroidapp.model.Word;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class WordBank {
    private final String URL = "https://my-json-server.typicode.com/cgerard321/dictionary/dictionary";
    ArrayList<Word> wordArrayList = new ArrayList<>();

    public ArrayList<Word> getWords(final WordListAsyncResponse callback) {
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0; i<response.length(); i++) {
                            try {
                                Word word = new Word();
                                word.setWord(response.getJSONArray(i).getString(0));
                                word.setPos(response.getJSONArray(i).getString(1));
                                word.setDefinition(response.getJSONArray(i).getString(2));
                                word.setFrench(response.getJSONArray(i).getString(3));
                                wordArrayList.add(word);
                                Log.d("JSONObject", "onResponse: " + word.getWord());
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.d("JSONObject catch", "onResponse: " + e);
                            }
                            if(callback != null) {
                                callback.processFinished(wordArrayList);
                                Log.d("callback", "onResponse: callback");
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("JSONArray Error", "onErrorResponse: " + error );
                    }
                }
        );

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return wordArrayList;
    }
}
