package com.miguelsanchezp.hackupcproject;

import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

class WriteData {
    private static final String TAG = "WriteData";

    static void WriteIntoFile (Data data) {
        File file = new File(  "/data/data/com.miguelsanchezp.hackupcproject/files/data.txt");
        if (file.exists()) {
            try {
                File fileDef = new File ("/data/data/com.miguelsanchezp.hackupcproject/files/", "data.txt");
                FileOutputStream fileOutputStream = new FileOutputStream(fileDef);
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
            File file1 = new File ("/data/data/com.miguelsanchezp.hackupcproject/files/", "data.txt");
            try {
                file1.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file1);
                fileOutputStream.write("0&0&0&0&0&0".getBytes());
                fileOutputStream.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void exportScore (double score) {
        File file = new File ("/data/data/com.miguelsanchezp.hackupcproject/files/scores.txt");
        if (file.exists()) {
            File file1 = new File("/data/data/com.miguelsanchezp.hackupcproject/files/", "scores.txt");
            String prev = ReadData.getVal ("/data/data/com.miguelsanchezp.hackupcproject/files/", "scores.txt");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file1);
                if (prev!=null) {
                    fileOutputStream.write(prev.getBytes());
                }
                fileOutputStream.write(String.valueOf(score).getBytes());
                fileOutputStream.write("-".getBytes());
                fileOutputStream.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                file.createNewFile();
            }catch(IOException e) {
                e.printStackTrace();
            }

        }
    }

    static void exportMovement (double mov) {
        File file = new File ("/data/data/com.miguelsanchezp.hackupcproject/files/movement.txt");
        if (file.exists()) {
            String Movement = ReadData.getVal("/data/data/com.miguelsanchezp.hackupcproject/files/", "movement.txt");
            if (Movement != null) {
                double movement = Double.parseDouble(Movement);
                movement+=mov;
                try {
                    File file1 = new File ("/data/data/com.miguelsanchezp.hackupcproject/files/", "movement.txt");
                    FileOutputStream fileOutputStream = new FileOutputStream(file1);
                    fileOutputStream.write(String.valueOf(movement).getBytes());
                    fileOutputStream.close();
                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            try {
                File file1 = new File ("/data/data/com.miguelsanchezp.hackupc.project/files/", "movement.txt");
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(String.valueOf(0).getBytes());
                fileOutputStream.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
