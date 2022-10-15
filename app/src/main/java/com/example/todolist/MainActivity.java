package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.net.vcn.VcnConfig;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText item;
Button add;
ListView listView;
ArrayList<String> itemList = new ArrayList<>();
ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = findViewById(R.id.editText);
        add = findViewById(R.id.button);
        listView = findViewById(R.id.list);
        itemList = filehelper.readData(this);

        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,
                android.R.id.text1,itemList);
        listView.setAdapter(arrayAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = item.getText().toString();
                itemList.add(itemName);
                item.setText(" ");
                filehelper.writeData(itemList,getApplicationContext());
                arrayAdapter.notifyDataSetChanged();
            }
        });


listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Delete this item");
        alert.setMessage("Do you want to delete this item ? ");
        alert.setCancelable(false);
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();



            }
        });
alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        itemList.remove(i);
        arrayAdapter.notifyDataSetChanged();
        filehelper.writeData(itemList, getApplicationContext());

    }
});
AlertDialog alertDialog = alert.create();
alertDialog.show();

    }
});

    }
}