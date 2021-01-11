package com.example.minimo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences mySharedPreferences;
    String username;

    public String getUsername(View v){
        EditText usernameContainer;
        usernameContainer = (EditText)findViewById(R.id.editTextTextPersonName);
        return usernameContainer.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySharedPreferences = getSharedPreferences("mySharedPreferences", MODE_PRIVATE);

        if(mySharedPreferences.getAll().size()!=0){
            username = (String)mySharedPreferences.getAll().get("Username");
            Toast.makeText(getApplicationContext(), "You are seeing user: " + username, Toast.LENGTH_SHORT).show();
            openInfoActivity();
        }
    }

    public void onBuscarClick(View v){
        username = getUsername(v);
        SharedPreferences.Editor editor = getSharedPreferences("mySharedPreferences", MODE_PRIVATE).edit();
        editor.putString("Username", username);
        editor.apply();
        openInfoActivity();
    }

    public void openInfoActivity(){
        Intent infoActivity = new Intent(this, InfoActivity.class);
        startActivity(infoActivity);
    }
}