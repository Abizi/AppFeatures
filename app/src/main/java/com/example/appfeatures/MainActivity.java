package com.example.appfeatures;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appfeatures.TicTacToe.TicTacToe;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnTicTacToe(View view) {
        Intent intent = new Intent(this, TicTacToe.class);
        startActivity(intent);
    }
}
