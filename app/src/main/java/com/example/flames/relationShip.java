package com.example.flames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class relationShip extends AppCompatActivity {
    private ImageView rView;
    private TextView nameBanner;
    private Button shoutOut;
    private Uri imageUri;
    private String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relation_ship);
        shoutOut=findViewById(R.id.shoutOut);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/font_two.ttf");
        rView = findViewById(R.id.rView);
        nameBanner = findViewById(R.id.nameBanner);
        nameBanner.setTypeface(tf);
        Intent n = getIntent();
        String r = n.getStringExtra("relation");
        String n1 = n.getStringExtra("n1");
        String n2 = n.getStringExtra("n2");
        Log.i("relation",r);
        if (!r.equals(0)) {
            String rPointer="";
            switch (r) {
                case "F":
                    rView.setImageResource(R.drawable.f_friends);
                    rPointer="Friendship";
                    break;
                case "L":
                    rView.setImageResource(R.drawable.f_love);
                    rPointer="Love";
                    break;
                case "A":
                    rView.setImageResource(R.drawable.f_affection);
                    rPointer="Affection";
                    break;
                case "M":
                    rView.setImageResource(R.drawable.f_marriage);
                    rPointer="Marriage";
                    break;
                case "E":
                    rView.setImageResource(R.drawable.f_enemy);
                    rPointer="Enemies";
                    break;
                case "S":
                    rView.setImageResource(R.drawable.f_sister);
                    rPointer="Sister/Brother";
                    break;
            }
            String link="https://drive.google.com/file/d/1HLFqAoXC94X43neA6Yp0DTA5Te4pYQX6/view?usp=sharing";
            message = n1+ " and "+ n2 +" share a bond of "+rPointer+"-->Find the bond with your crush on\n\n"+link;
            nameBanner.setText(n1+" and " +n2+ " share a " +"bond of ");
        }else{
            Toast.makeText(this, "No relation found!", Toast.LENGTH_SHORT).show();
        }
        shoutOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable drawable =(BitmapDrawable)rView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                String bitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"title","null");
                Intent intent = new Intent(Intent.ACTION_SEND);
                imageUri=Uri.parse(bitmapPath);
                intent.putExtra(Intent.EXTRA_STREAM,imageUri);
                intent.putExtra(Intent.EXTRA_TEXT,message);
                //intent.setType("text/plain");
                intent.setType("image/png");
                startActivity(Intent.createChooser(intent,"Send using.."));
            }
        });
    }
}
