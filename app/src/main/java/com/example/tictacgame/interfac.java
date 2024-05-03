package com.example.tictacgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class interfac extends AppCompatActivity {

    private ImageView aiBtn,frindBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfac);
        aiBtn=findViewById(R.id.ihm1);
        frindBtn=findViewById(R.id.ihm2);


        frindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(interfac.this,Players.class);
                startActivity(intent);
            }
        });

        aiBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent intent=new Intent(interfac.this,ai.class);
                startActivity(intent);
            }
        });


    }
}