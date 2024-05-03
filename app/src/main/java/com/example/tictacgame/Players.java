package com.example.tictacgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class Players extends AppCompatActivity {
    private EditText name1,name2;
    private RadioButton rdX,rdO;
    private ImageView imgX,imgO;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        name1=findViewById(R.id.nameplyr1);
        name2=findViewById(R.id.nameplyr2);
        rdX=findViewById(R.id.x);
        imgX=findViewById(R.id.imgx);
        rdO=findViewById(R.id.o);
        imgO=findViewById(R.id.imgo);
        btnStart=findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nam1 = name1.getText().toString();
                String nam2 = name2.getText().toString();

                if (nam1.isEmpty() || nam2.isEmpty()) {
                    Toast.makeText(Players.this, "Please Enter your name", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(Players.this, game.class);
                    intent.putExtra("player1", nam1);
                    intent.putExtra("player2", nam2);
                    if (rdX.isChecked()){
                        intent.putExtra("choixpl1","x");
                    }else {
                        intent.putExtra("choixpl1","o");
                    }
                    startActivity(intent);
                }
            }
        });

    }
    public void check(View view){
        int ID=view.getId();
        if (ID==R.id.x){
            setChecked(rdX);
            setInChecked(rdO);
            imgX.setBackgroundResource(R.drawable.bgx);
            imgO.setBackground(null);
        }else {
            setChecked(rdO);
            setInChecked(rdX);
            imgX.setBackground(null);
            imgO.setBackgroundResource(R.drawable.pgo);
        }
    }
    private void setChecked(View view) {
        if (view instanceof RadioButton) {
            RadioButton checkBox = (RadioButton) view;
            checkBox.setChecked(true);
        }
    }
    private void setInChecked(View view) {
        if (view instanceof RadioButton) {
            RadioButton checkBox = (RadioButton) view;
            checkBox.setChecked(false);
        }
    }

}