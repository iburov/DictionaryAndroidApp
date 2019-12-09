package com.ivanburov.dictionaryandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ivanburov.dictionaryandroidapp.model.Word;

public class DefineActivity extends AppCompatActivity {
    private TextView wordTV;
    private TextView posTV;
    private TextView defTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define);

        wordTV = findViewById(R.id.wordTV);
        posTV = findViewById(R.id.posTV);
        defTV = findViewById(R.id.defTV);

        Bundle bundle = getIntent().getExtras();
        String wordAsString = bundle.getString("WordAsString");
        Gson gson = new Gson();
        Word word = gson.fromJson(wordAsString, Word.class);

        wordTV.setText(word.getWord());
        posTV.setText(word.getPos());
        defTV.setText("Definition: " + word.getDefinition());
    }
}
