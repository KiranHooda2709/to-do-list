package com.example.todolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class filehelper
{ public static final String FILENAME = "listinfo.dat";
    public static void writeData(ArrayList<String> item , Context context) {

        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, context.MODE_PRIVATE);
            try {
                ObjectOutputStream oas= new ObjectOutputStream(fos);
                oas.writeObject(item);
                oas.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        {
        }
    }
public static ArrayList<String> readData(Context context){
        ArrayList<String> itemList = null;
    try {
        FileInputStream fis = context.openFileInput(FILENAME);
        try {
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                itemList = (ArrayList<String>) ois.readObject();
            } catch (ClassNotFoundException e) {
                itemList = new ArrayList<>();

                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
 return itemList;

}

}



    

















    


