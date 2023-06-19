package com.example.openhabcrud;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import org.openhab.rest.client.CRUD;
import org.openhab.android.rest.client.CRUD;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    private Button createButton;
    private Button readButton;
    private Button postUpdateButton;
    private Button sendCommandButton;
    private Button deleteButton;
    private TextView resultTextView;

    private CRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        createButton = findViewById(R.id.createButton);
        readButton = findViewById(R.id.readButton);
        postUpdateButton = findViewById(R.id.postUpdateButton);
        sendCommandButton = findViewById(R.id.sendCommandButton);
        deleteButton = findViewById(R.id.deleteButton);
        resultTextView = findViewById(R.id.resultTextView);

        // Set button click listeners
        createButton.setOnClickListener(v -> new CreateItemTask().execute());
        readButton.setOnClickListener(v -> new ReadItemTask().execute());
        postUpdateButton.setOnClickListener(v -> new PostUpdateTask().execute());
        sendCommandButton.setOnClickListener(v -> new SendCommandTask().execute());
        deleteButton.setOnClickListener(v -> new DeleteItemTask().execute());

        // Initialize CRUD client in the background
        new InitializeCRUDTask().execute();
    }

    private class InitializeCRUDTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String url = "http://<ip>:8080"; // Replace with your openHAB URL
            String username = "<username>"; // Replace with your openHAB username
            String password = "<password>"; // Replace with your openHAB password
            crud = new CRUD(url, username, password);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class CreateItemTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            // Replace with your desired item type and name
            String itemType = "Switch";
            String itemName = "MyItem";
            String state = "OFF";
            return crud.createItem(itemType, itemName, state);
        }

        @Override
        protected void onPostExecute(String result) {
            resultTextView.setText(result);
        }
    }

    private class ReadItemTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            // Replace with your item name
            String itemName = "MyItem";
            return crud.readItem(itemName);
        }

        @Override
        protected void onPostExecute(String result) {
            resultTextView.setText(result);
        }
    }

    private class PostUpdateTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            // Replace with your item name and state
            String itemName = "MyItem";
            String state = "OFF";
            return crud.postUpdate(itemName, state);
        }

        @Override
        protected void onPostExecute(String result) {
            resultTextView.setText(result);
        }
    }

    private class SendCommandTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            // Replace with your item name and command
            String itemName = "MyItem";
            String command = "ON";
            return crud.sendCommand(itemName, command);
        }

        @Override
        protected void onPostExecute(String result) {
            resultTextView.setText(result);
        }
    }

    private class DeleteItemTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            // Replace with your item name
            String itemName = "MyItem";
            return crud.deleteItem(itemName);
        }

        @Override
        protected void onPostExecute(String result) {
            resultTextView.setText(result);
        }
    }
}