package com.example.appfeatures.TicTacToe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appfeatures.R;

public class TicTacToe extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "Tic_Tac_Toe";

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Point;
    private int player2Point;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        textViewPlayer1 = findViewById(R.id.txt_TTT_Player1Score);
        textViewPlayer2 = findViewById(R.id.txt_TTT_Player2Score);

        // This loop initializes the nine buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "btn_TTT_Box" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        // This initializes and sets the OnClick listener for the Reset Button
        Button resetButton = findViewById(R.id.btn_TTT_Reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    // Handles input for each button click
    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("O");
        }

        roundCount++;

        if (checkForWin()){
            if (player1Turn){
                player1Wins();
            } else {
                player2Wins();
            }
        } else if(roundCount == 9){
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkForWin(){
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals(""))
                return true;
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals(""))
                return true;
        }
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals(""))
            return true;

        return field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("");
    }

    private void player1Wins(){
        player1Point++;
        Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }

    private void player2Wins(){
        player2Point++;
        Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }

    private void draw(){
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePoints(){
        String score1 = " " + player1Point;
        String score2 = " " + player2Point;
        textViewPlayer1.setText(score1);
        textViewPlayer2.setText(score2);
    }

    private void resetBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void resetGame(){
        player1Point = 0;
        player2Point = 0;
        updatePoints();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Point", player1Point);
        outState.putInt("player2Point", player2Point);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Point = savedInstanceState.getInt("player1Point");
        player2Point = savedInstanceState.getInt("player2Point");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}
