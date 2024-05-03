package com.example.tictacgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ai extends AppCompatActivity {

    private EditText name;
    private TextView med,hard;
    private RadioButton rdX,rdO,rdmed,rdhard;
    private ImageView imgX,imgO;
    private Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai);

        String name2="ROBOT";
        med=findViewById(R.id.editText);
        rdmed=findViewById(R.id.med);
        rdhard=findViewById(R.id.hard);
        hard=findViewById(R.id.editText1);
        name=findViewById(R.id.nameplyr1);
        rdX=findViewById(R.id.x);
        imgX=findViewById(R.id.imgx);
        rdO=findViewById(R.id.o);
        imgO=findViewById(R.id.imgo);
        btnStart=findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nam1 = name.getText().toString();

                if (nam1.isEmpty()) {
                    Toast.makeText(ai.this, "Please Enter your name", Toast.LENGTH_LONG).show();
                } else {
                    if (rdmed.isChecked()) {
                        Intent intent = new Intent(ai.this, robotgame.class);
                        intent.putExtra("player1", nam1);
                        intent.putExtra("player2", name2);
                        if (rdX.isChecked()) {
                            intent.putExtra("choixpl1", "x");
                        } else {
                            intent.putExtra("choixpl1", "o");
                        }
                        startActivity(intent);
                    }
                    if (rdhard.isChecked()){
                        Intent intent = new Intent(ai.this, robotHard.class);
                        intent.putExtra("player1", nam1);
                        intent.putExtra("player2", name2);
                        if (rdX.isChecked()) {
                            intent.putExtra("choixpl1", "x");
                        } else {
                            intent.putExtra("choixpl1", "o");
                        }
                        startActivity(intent);
                    }
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

    public void checkHardest(View view){
        int ID=view.getId();
        if (ID==R.id.med){
            setChecked(rdmed);
            setInChecked(rdhard);
            med.setBackgroundResource(R.drawable.bgplayername);
            med.setTextColor(ContextCompat.getColor(this,R.color.DarckBleu));
            hard.setBackgroundResource(R.drawable.bg_hard_med);
            hard.setTextColor(ContextCompat.getColor(this, R.color.lateBleu));
        }else {
            setChecked(rdhard);
            setInChecked(rdmed);
            med.setBackgroundResource(R.drawable.bg_hard_med);
            med.setTextColor(ContextCompat.getColor(this,R.color.lateBleu));
            hard.setBackgroundResource(R.drawable.bgplayername);
            hard.setTextColor(ContextCompat.getColor(this,R.color.DarckBleu));

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