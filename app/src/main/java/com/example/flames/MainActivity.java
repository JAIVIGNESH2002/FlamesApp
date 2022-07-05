package com.example.flames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button getStarted;
    EditText userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName=findViewById(R.id.userName);
        getStarted = findViewById(R.id.getStarted);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(userName.getText().toString())) {
                    if(userName.getText().toString().contains(" ")){
                        userName.setError("Space is not allowed!");
                    }else {
                        Intent intent = new Intent(MainActivity.this, startActivity.class);
                        intent.putExtra("userName", userName.getText().toString());
                        startActivity(intent);
                    }
                }
                else{
                    userName.setError("Please enter your name!");
                }
            }
        });

    }
}