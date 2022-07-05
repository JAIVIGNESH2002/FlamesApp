package com.example.flames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class startActivity extends AppCompatActivity {
    private EditText name1;
    private EditText name2;
    private Button flameUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        name1 = findViewById(R.id.name1);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        if(userName!=null) {
            name1.setText(userName);
            name1.setEnabled(false);
        }
        flameUp=findViewById(R.id.flameUp);
        flameUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name2 = findViewById(R.id.name2);
                if (name2.getText().toString().contains(" ")) {
                    name2.setError("Space is not allowed");
                } else {
                    String s1 = name1.getText().toString().toLowerCase();
                    String s2 = name2.getText().toString().toLowerCase();
                    if (s1.equals(s2)) {
                        Toast.makeText(startActivity.this, "Names cannot be identical!", Toast.LENGTH_SHORT).show();
                    } else if (s2.isEmpty()) {
                        Toast.makeText(startActivity.this, "Name field is empty!", Toast.LENGTH_SHORT).show();
                        name2.setError("Required field");

                    } else {
                        int count = s1.concat(s2).length();
                        StringBuilder shortStr;
                        StringBuilder longStr;
                        FirebaseDatabase.getInstance().getReference().child(s1).push().child("name").setValue(s2);
                        if (s1.length() != s2.length()) {
                            if (s1.length() > s2.length()) {
                                longStr = new StringBuilder(s1);
                                shortStr = new StringBuilder(s2);
                            } else {
                                longStr = new StringBuilder(s2);
                                shortStr = new StringBuilder(s1);
                            }

                        } else {
                            shortStr = new StringBuilder(s1);
                            longStr = new StringBuilder(s2);
                        }
                        for (int i = 0; i < shortStr.length(); i++) {
                            for (int j = 0; j < longStr.length(); j++) {
                                if (shortStr.charAt(i) == longStr.charAt(j)) {
                                    count -= 2;
                                    longStr.deleteCharAt(j);
                                    break;
                                }
                            }
                        }
                        StringBuilder flames = new StringBuilder("FLAMES");
                        int temp = 0;
                        int iterator = 0;
                        while (flames.length() != 1) {
                            if (count - 1 - iterator > 0) {
                                temp = count - 1 - iterator;
                            } else {
                                temp = count + flames.substring(0, temp).length() - 1;
                            }
                            while (temp >= flames.length()) {
                                temp = temp % flames.length();
                            }
                            iterator = flames.substring(temp, flames.length()).length() - 1;
                            flames.deleteCharAt(temp);
                        }
                        Intent n = new Intent(startActivity.this, relationShip.class);
                        n.putExtra("relation", flames.toString());
                        n.putExtra("n1", s1);
                        n.putExtra("n2", s2);
                        startActivity(n);
                    }
                }
            }
        });
    }
}