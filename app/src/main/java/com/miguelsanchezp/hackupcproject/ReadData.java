package com.miguelsanchezp.hackupcproject;

import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadData {
    public static Data ReadFromFile () {
        Data data = new Data();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(MainActivity.getContext().openFileInput("stuff.txt"));
            int character = inputStreamReader.read();
            StringBuilder sb = new StringBuilder();
            while (character!= -1) {
                sb.append((char) character);
                character=inputStreamReader.read();
            }
            inputStreamReader.close();
            String text = sb.toString();
            String[] terms = text.split("&");
            data.setEcoFriendlySecs(Integer.parseInt(terms[0]));
            data.setPrivateSecs(Integer.parseInt(terms[1]));
            data.setPublicSecs(Integer.parseInt(terms[2]));
            data.setEcoFriendlyDistance(Double.parseDouble(terms[3]));
            data.setPrivateDistance(Double.parseDouble(terms[4]));
            data.setPublicDistance(Double.parseDouble(terms[5]));
            data.setOthers();
            return data;
        }catch (FileNotFoundException e) {
            Toast.makeText(MainActivity.getContext(), "The file couldn't be found", Toast.LENGTH_LONG).show();
        }catch (IOException e) {
            Toast.makeText(MainActivity.getContext(), "There was a IOException", Toast.LENGTH_LONG).show();
        }
        return data;
    }
}
