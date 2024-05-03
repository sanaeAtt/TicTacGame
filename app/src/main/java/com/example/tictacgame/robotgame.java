package com.example.tictacgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;

public class robotgame extends AppCompatActivity {
    private ImageView img1, img2;
    private int[] winningPosition;
    private int randomI,randomJ;
    private TextView nam1, nam2;
    private String player1Name, player2Name, choixPlayer1;

    private ImageView[][] buttons = new ImageView[3][3];
    private int round = 0;
    private int player1pnt = 0, player2pnt = 0;
    private TextView play1, play2;
    private Button btnRest;
    private int choixFirstPlayer, choixSecndPlayer;
    private Boolean player1 = true;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robotgame);
        Intent intent = getIntent();
        player1Name = ((Intent) intent).getStringExtra("player1");
        player2Name = intent.getStringExtra("player2");
        choixPlayer1 = intent.getStringExtra("choixpl1");
        img1 = findViewById(R.id.imgplyr1);
        img2 = findViewById(R.id.imgplyr2);
        nam1 = findViewById(R.id.nm1);
        nam2 = findViewById(R.id.nm2);
        play1 = findViewById(R.id.ptplyr1);
        play2 = findViewById(R.id.ptplyr2);
        btnRest = findViewById(R.id.btnReset);
        choix();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String s = "btn_" + i + j;
                int ID = getResources().getIdentifier(s, "id", getPackageName());
                buttons[i][j] = findViewById(ID);
            }
        }

        btnRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reseBtn();
            }
        });
    }

    public void onClick(View view) {


        if (((ImageView) view).getDrawable() == null) {
            if (player1) {
                ((ImageView) view).setImageResource(choixFirstPlayer);
                round++;

            }
            if (checkWiner()) {
                player1win();
            } else if (round == 9) {
                draw();
            } else {
                player1 = !player1;
                robot();
            }
        }
    }

    private void robot() {
            do {
                randomI = random.nextInt(3);
                randomJ = random.nextInt(3);
            } while (buttons[randomI][randomJ].getDrawable()!=null);
            buttons[randomI][randomJ].setImageResource(choixSecndPlayer);
            round++;
            if (checkWiner()) {
                player2win();
            } else if (round == 9) {
                draw();
            } else {
                player1 = !player1;
            }

    }


    private Boolean checkWiner() {
        Drawable[][] remplissage = new Drawable[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] != null) {
                    remplissage[i][j] = buttons[i][j].getDrawable();
                    if (remplissage[i][j] != null) {
                        BitmapDrawable drawable = (BitmapDrawable) remplissage[i][j];
                        Bitmap bitmap = drawable.getBitmap();
                    }
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (areDrawablesEqual(remplissage[i][0], remplissage[i][1], remplissage[i][2])) {
                winningPosition = new int[]{i, 0, i, 2};
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (areDrawablesEqual(remplissage[0][i], remplissage[1][i], remplissage[2][i])) {
                winningPosition = new int[]{0, i, 2, i};
                return true;
            }
        }
        if (areDrawablesEqual(remplissage[0][0], remplissage[1][1], remplissage[2][2])) {
            winningPosition = new int[]{0, 0, 2, 2};
            return true;
        }
        if (areDrawablesEqual(remplissage[0][2], remplissage[1][1], remplissage[2][0])) {
            winningPosition = new int[]{0, 2, 2, 0};
            return true;
        }
        return false;
    }


    private boolean areDrawablesEqual(Drawable drawable1, Drawable drawable2, Drawable drawable3) {
        if (drawable1 == null || drawable2 == null || drawable3 == null) {
            return false;
        }

        BitmapDrawable bitmapDrawable1 = (BitmapDrawable) drawable1;
        BitmapDrawable bitmapDrawable2 = (BitmapDrawable) drawable2;
        BitmapDrawable bitmapDrawable3 = (BitmapDrawable) drawable3;

        Bitmap bitmap1 = bitmapDrawable1.getBitmap();
        Bitmap bitmap2 = bitmapDrawable2.getBitmap();
        Bitmap bitmap3 = bitmapDrawable3.getBitmap();

        return bitmap1.sameAs(bitmap2) && bitmap1.sameAs(bitmap3);
    }


    private void player1win() {
        drawWinningLine();
        desactiverButtons();
        player1pnt++;
        Toast.makeText(this, "player 1 win ", Toast.LENGTH_LONG).show();
        updatetext();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                reset();
            }
        }, 3000);

    }

    private void player2win() {
        drawWinningLine();
        desactiverButtons();
        player2pnt++;
        Toast.makeText(this, "player 2 win ", Toast.LENGTH_LONG).show();
        updatetext();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                reset();
            }
        }, 3000);

    }

    private void draw() {
        Toast.makeText(this, "Ta3ADOUUUL ", Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                reset();
            }
        }, 3000);
    }

    private void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setImageDrawable(null);
            }
        }
        round = 0;
        player1 = true;
        resetButtonBackground();
        activerButtons();
    }

    private void updatetext() {
        YoYo.with(Techniques.Tada)
                .duration(300)
                .repeat(1)
                .onEnd(animator -> play1.setText(String.valueOf(player1pnt)))
                .playOn(play1);

        YoYo.with(Techniques.Tada)
                .duration(300)
                .repeat(1)
                .onEnd(animator -> play2.setText(String.valueOf(player2pnt)))
                .playOn(play2);

    }

    private void choix() {
        if ("x".equals(choixPlayer1)) {
            img1.setImageResource(R.drawable.x);
            img2.setImageResource(R.drawable.o);
            choixFirstPlayer = R.drawable.x;
            choixSecndPlayer = R.drawable.o;

        } else {
            img1.setImageResource(R.drawable.o);
            img2.setImageResource(R.drawable.x);
            choixSecndPlayer = R.drawable.x;
            choixFirstPlayer = R.drawable.o;
        }
        if (!"Player1".equals(player1Name) && nam1 != null) {
            nam1.setText("");
            nam1.setText(player1Name);
        }
        if (!"Player2".equals(player2Name) && nam2 != null) {
            nam2.setText("");
            nam2.setText(player2Name);
        }

    }

    private void reseBtn() {
        player1pnt = 0;
        player2pnt = 0;
        play1.setText("0");
        play2.setText("0");
        reset();

    }

    private void desactiverButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void activerButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(true);
            }
        }
    }
    private void drawWinningLine() {
        if (winningPosition != null && winningPosition.length == 4) {
            int startX = winningPosition[0];
            int startY = winningPosition[1];
            int endX = winningPosition[2];
            int endY = winningPosition[3];

            if (startX == endX) { // Ligne
                for (int j = startY; j <= endY; j++) {
                    buttons[startX][j].setColorFilter(getResources().getColor(android.R.color.black));
                }
            } else if (startY == endY) { // Colonne
                for (int i = startX; i <= endX; i++) {
                    buttons[i][startY].setColorFilter(getResources().getColor(android.R.color.black));
                }
            } else if (startX - startY == 0 && endX - endY == 0) { // Diagonale principale
                for (int i = startX, j = startY; i <= endX && j <= endY; i++, j++) {
                    buttons[i][j].setColorFilter(getResources().getColor(android.R.color.black));
                }
            } else if (startX + startY == 2 && endX + endY == 2) { // Diagonale secondaire
                for (int i = startX, j = startY; i <= endX && j >= endY; i++, j--) {
                    buttons[i][j].setColorFilter(getResources().getColor(android.R.color.black));
                }
            }
        }
    }
    private void resetButtonBackground() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].clearColorFilter();
            }
        }
    }
}