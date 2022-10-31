package com.example.dulichnote.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.dulichnote.model.PlaceItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainVM extends ViewModel {

    private List<PlaceItem> listPlace;

    public void setListPlace(List<PlaceItem> listPlace) {
        this.listPlace = listPlace;
    }

    public List<PlaceItem> getListPlace() {
        return listPlace;
    }

    public void loadStory(InputStream inputStream) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String str =null;
        String name = null;
        String linkPhoto = null;
        StringBuilder desc = new StringBuilder();
        listPlace = new ArrayList<>();
        while ((str = bufferedReader.readLine()) != null) {
            if (str.isEmpty())
                continue;

            if (name == null) {
                name = str;
            }
            else if (linkPhoto==null){
                linkPhoto = str;
            }
            else if (!str.startsWith("0-0-0-0")) {
                desc.append(str).append("\n");

            } else {
                listPlace.add(new PlaceItem(name,  desc.toString(),linkPhoto));
                name = null;
                linkPhoto= null;
                desc = new StringBuilder();
            }
        }

        bufferedReader.close();
        Log.e("AAAAAAAAAA", "loadStory: "+listPlace.size() );
        Log.e("AAAAAAAAAA", "loadStory: "+listPlace.get(0).toString() );
    }
}
