package com.example.okovacs.dictionary2;

import android.app.FragmentManager;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends ListActivity {
    ArrayList<Word> words = new ArrayList<>();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        words = loadWords();

        listView = getListView();
        final ArrayAdapter<Word> arrayAdapter = new ArrayAdapter<Word>(this,android.R.layout.simple_list_item_1,words);
        listView.setAdapter(arrayAdapter);

        SearchView searchView = (SearchView) findViewById(R.id.search);
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Word> array = new ArrayList<>();
                for(Word word: words){
                    if(word.getName().toLowerCase().startsWith(s.toLowerCase())){
                        array.add(word);
                    }
                }
                ArrayAdapter<Word> adapter= new ArrayAdapter<Word>(MainActivity.super.getApplication(),android.R.layout.simple_list_item_1,array);
                listView.setAdapter(adapter);
                return false;
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        TextView view = (TextView)v;
        String name = String.valueOf(view.getText());

        Word word = getWordByName(name);

        FragmentManager fragmentManager = getFragmentManager();
        PopupDialog popupDialog = new PopupDialog();
        Bundle args = new Bundle();
        args.putString("name", word.getName());
        args.putString("description",word.getDescription());
        popupDialog.setArguments(args);

        popupDialog.show(fragmentManager,word.getName());

    }

    private Word getWordByName(String name) {
        if(name!=null){
            for(Word word: words){
                if(name.equals(word.getName())){
                    return word;
                }
            }
        }
        return null;
    }

    private ArrayList<Word> loadWords() {
        ArrayList<Word> words = new ArrayList<>();
        try {
            InputStream is = this.getResources().openRawResource(R.raw.blockchainen);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while(true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] split = line.split("=");
                String name = split[0];
                String description = split[1];
                words.add(new Word(name,description));
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }
}
