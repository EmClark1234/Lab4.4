package com.example.lab44;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    //making arrayList which holds list items called elements
    private ArrayList<todo> elements = new ArrayList<>();

    private MyListAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get ListView from xml file
        ListView myListView = findViewById(R.id.myListView);
        //connect ListView and adapter
        myListView.setAdapter(myAdapter = new MyListAdapter());
        //get xml elements
        EditText myEditText = findViewById(R.id.myEditText);
        Switch urgentSwitch = findViewById(R.id.urgentSwitch);

        Button addBtn = findViewById(R.id.addButton);

        //add event listener to button
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get Text from user from editText xml element
                String userInput = myEditText.getText().toString();
                //instantiate new todo object called listItem
                todo listItem = new todo();
                //set listItem to the user's input in the editText
                listItem.setTodoText(userInput);
                //set the switch to isUrgent
                listItem.setUrgency(urgentSwitch.isChecked());

                //add listItem to elements Array
                elements.add(listItem);

                //now reset editText's text to an empty string
                myEditText.setText("");

                //MyListAdapter.notifyDatasetChanged();
                myAdapter.notifyDataSetChanged();

            }
        });


        myListView.setOnItemLongClickListener((p, b, pos, id) -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setTitle("Do you want to delete this?");

            alertDialogBuilder.setMessage("The selected row is " + elements.get(pos).todoText);

            alertDialogBuilder.setPositiveButton("Yes", (click, arg) -> {
                elements.remove(elements.get(pos));
                myAdapter.notifyDataSetChanged();
            });

            alertDialogBuilder.setNegativeButton("No", null).show();

            alertDialogBuilder.create().show();
            return true;
        });

    }
    private class MyListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return elements.size();
        }

        @Override
        //put todo object here and not "object"
        public todo getItem(int position) {
            return elements.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getView(int position, View old, ViewGroup parent) {
            View newView = old;
            LayoutInflater inflater = getLayoutInflater();

            if(newView == null){
                newView = inflater.inflate(R.layout.activity_todo, parent, false);
            }


            TextView myTextView = newView.findViewById(R.id.myTextView);
            myTextView.setText(getItem(position).todoText);

            //Checking if item is urgent or not and coloring listItem depending on urgency
            if (getItem(position).isUrgent()) {
                myTextView.setBackgroundColor(Color.RED);
                myTextView.setTextColor(Color.WHITE);
            } else if(getItem(position).notUrgent()){
                myTextView.setBackgroundColor(Color.WHITE);
                myTextView.setTextColor(Color.GRAY);
            }


            return newView;
        }
}}