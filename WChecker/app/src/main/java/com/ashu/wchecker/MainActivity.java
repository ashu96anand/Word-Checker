package com.ashu.wchecker;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ashu.wchecker.model.Dictionary;
import com.ashu.wchecker.dict.Trie;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "MainActivity";
    private Dictionary dictionary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadDictionary();
        Button searchButton = (Button) findViewById(R.id.searchButton);
        final EditText editText = (EditText) findViewById(R.id.editText);
        final TextView textView = (TextView) findViewById(R.id.headingText);
        final ListView wordList = (ListView) findViewById(R.id.wordList);
        final Context context = this;
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToSearch = editText.getText().toString();
                if (dictionary.containsWord(textToSearch)) {
                    textView.setText("Word found in the dictionary! Here's a list of words with similar prefix.");
                } else {
                    textView.setText("Word not found! Did you mean?");
                }
                List<String> matchingWordList = dictionary.getMatchingWords(textToSearch, 1000);
                if (matchingWordList != null) {
                    ArrayAdapter<String> wordListAdapter = new ArrayAdapter<String>(context, R.layout.list_item_layout, matchingWordList);
                    wordList.setAdapter(wordListAdapter);
                }
            }
        });
    }

    private void loadDictionary() {
        // create instance of dictionary i.e. class that implements Dictionary interface first.
        dictionary = new Trie();
        // Second, read words from the file web2.txt and add them to the dictionary.
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.web2)));
            String word = reader.readLine();
            while (word != null) {
                dictionary.addWord(word.toLowerCase());
                word = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
