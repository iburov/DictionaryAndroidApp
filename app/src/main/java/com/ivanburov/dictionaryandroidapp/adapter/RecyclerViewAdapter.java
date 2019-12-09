package com.ivanburov.dictionaryandroidapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ivanburov.dictionaryandroidapp.DefineActivity;
import com.ivanburov.dictionaryandroidapp.FrenchActivity;
import com.ivanburov.dictionaryandroidapp.R;
import com.ivanburov.dictionaryandroidapp.model.Word;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Word> wordList;

    public RecyclerViewAdapter(Context context, ArrayList<Word> wordList) {
        this.context = context;
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Word word = wordList.get(position);
        Gson gson = new Gson();
        final String wordAsString = gson.toJson(word);

        holder.wordTV.setText(word.getWord());
        holder.defineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DefineActivity.class);
                intent.putExtra("WordAsString", wordAsString);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.frenchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FrenchActivity.class);
                intent.putExtra("WordAsString", wordAsString);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView wordTV;
        private Button defineBtn;
        private Button frenchBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wordTV = itemView.findViewById(R.id.list_item_word);
            defineBtn = itemView.findViewById(R.id.define_btn);
            frenchBtn = itemView.findViewById(R.id.french_btn);
        }

//        @Override
//        public void onClick(View view) {
//            int position = getAdapterPosition();
//            Word word = wordList.get(position);
//            Gson gson = new Gson();
//            String wordAsString = gson.toJson(word);
//
//            Intent intent = new Intent(context, DetailsActivity.class);
//            intent.putExtra("ContactAsString", contactAsString);
//            context.startActivity(intent);
//        }
    }
}
