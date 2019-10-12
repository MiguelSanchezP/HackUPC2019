package com.miguelsanchezp.hackupcproject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class WriteData {
    private static final String TAG = "WriteData";

    public static void WriteIntoFile (Data data) {
        File file = new File(  "/data/data/com.miguelsanchezp.hackupcproject/files/data.txt");
        if (file.exists()) {
            Log.d(TAG, "WriteIntoFile: File exists");
            try {
                File fileDef = new File ("/data/data/com.miguelsanchezp.hackupcproject/files/", "data.txt");
                FileOutputStream fileOutputStream = new FileOutputStream(fileDef);
//                OutputStreamWriter outputStreamWriter = new OutputStreamWriter("/data/data/com.miguelsanchezp.hackupcproject/files/data.txt", Context.MODE_PRIVATE);
//                OutputStreamWriter outputStreamWriter = new OutputStreamWriter()
                fileOutputStream.write(String.valueOf(data.getEcoFriendlySecs()).getBytes());
                fileOutputStream.write("&".getBytes());
                fileOutputStream.write(String.valueOf(data.getPrivateSecs()).getBytes());
                fileOutputStream.write("&".getBytes());
                fileOutputStream.write(String.valueOf(data.getPublicSecs()).getBytes());
                fileOutputStream.write("&".getBytes());
                fileOutputStream.write(String.valueOf(data.getEcoFriendlyDistance()).getBytes());
                fileOutputStream.write("&".getBytes());
                fileOutputStream.write(String.valueOf(data.getPrivateDistance()).getBytes());
                fileOutputStream.write("&".getBytes());
                fileOutputStream.write(String.valueOf(data.getPublicDistance()).getBytes());
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                Toast.makeText(MainActivity.getContext(), "File wasn't found", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(MainActivity.getContext(), "IOException", Toast.LENGTH_LONG).show();
            }
        }else{
            Log.d(TAG, "WriteIntoFile: File doesn't exist");
            File file1 = new File ("/data/data/com.miguelsanchezp.hackupcproject/files/", "data.txt");
            try {
                Log.d(TAG, "WriteIntoFile: Creating File");
                file1.createNewFile();
            }catch (IOException e) {
                Log.d(TAG, "WriteIntoFile: Error creating File");
                e.printStackTrace();
//                Toast.makeText(MainActivity.getContext(), "Impossible to create a file", Toast.LENGTH_LONG).show();
            }
//            Toast.makeText(MainActivity.getContext(), "File has to be created", Toast.LENGTH_LONG).show();
        }
    }
}
