package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        Button button8=findViewById(R.id.button8X7);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "8";
                Intent intent = new Intent(Accueil.this, OpenGLES30Activity.class);
                intent.putExtra("message", message);
                startActivity(intent);
            }
        });

        Button button12=findViewById(R.id.button12X7);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "12";
                Intent intent = new Intent(Accueil.this, OpenGLES30Activity.class);
                intent.putExtra("message", message);
                startActivity(intent);
            }
        });


        Button button16=findViewById(R.id.button16X7);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "16";
                Intent intent = new Intent(Accueil.this, OpenGLES30Activity.class);
                intent.putExtra("message", message);
                startActivity(intent);
            }
        });



    }



}




