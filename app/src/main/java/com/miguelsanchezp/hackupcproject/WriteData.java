package com.miguelsanchezp.hackupcproject;

import android.content.Context;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteData {
    public static void WriteIntoFile (Data data) {
        try{
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter (MainActivity.getContext().openFileOutput("data.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data.getEcoFriendlySecs());
            outputStreamWriter.write("&");
            outputStreamWriter.write(data.getPrivateSecs());
            outputStreamWriter.write("&");
            outputStreamWriter.write(data.getPublicSecs());
            outputStreamWriter.write("&");
            outputStreamWriter.write(String.valueOf(data.getEcoFriendlyDistance()));
            outputStreamWriter.write("&");
            outputStreamWriter.write(String.valueOf(data.getPrivateDistance()));
            outputStreamWriter.write("&");
            outputStreamWriter.write(String.valueOf(data.getPublicDistance()));

        }catch (FileNotFoundException e) {
            Toast.makeText(MainActivity.getContext(), "File wasn't found", Toast.LENGTH_LONG).show();
        }catch (IOException e) {
            Toast.makeText(MainActivity.getContext(), "IOException", Toast.LENGTH_LONG).show();
        }
    }
}
