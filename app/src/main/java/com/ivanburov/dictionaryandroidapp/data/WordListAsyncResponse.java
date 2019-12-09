package com.ivanburov.dictionaryandroidapp.data;

import com.ivanburov.dictionaryandroidapp.model.Word;
import com.ivanburov.dictionaryandroidapp.model.Word;

import java.util.ArrayList;

public interface WordListAsyncResponse {
    void processFinished(ArrayList<Word> wordArrayList);
}
