package com.miguelsanchezp.hackupcproject;

import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadData {
    public static Data ReadFromFile () {
        Data data = new Data();
        File file1 = new File ("/data/data/com.miguelsanchezp.hackupcproject/files/data.txt");
        if (file1.exists()) {
            try {
//            InputStreamReader inputStreamReader = new InputStreamReader(MainActivity.getContext().openFileInput("data.txt"));
                File file = new File("/data/data/com.miguelsanchezp.hackupcproject/files/", "data.txt");
                FileInputStream fileInputStream = new FileInputStream(file);
                int character = fileInputStream.read();
                StringBuilder sb = new StringBuilder();
                while (character != -1) {
                    sb.append((char) character);
                    character = fileInputStream.read();
                }
                fileInputStream.close();
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
            } catch (FileNotFoundException e) {
                Toast.makeText(MainActivity.getContext(), "The file couldn't be found", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(MainActivity.getContext(), "There was a IOException", Toast.LENGTH_LONG).show();
            }
        }
        return data;
    }

    public static String getVal (String path) {
        File file = new File (path);
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                StringBuilder sb = new StringBuilder();
                int character = fileInputStream.read();
                while (character != -1) {
                    sb.append((char) character);
                    character = fileInputStream.read();
                }
                fileInputStream.close();
                return sb.toString();
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
