package com.herpin.thomas.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Fenetre_Sudoku_1 extends Activity{

    Activity context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenetre__sudoku_1);
        Button Niveau1 = (Button) findViewById(R.id.Lvl1);
        Button Niveau2 = (Button) findViewById(R.id.Lvl2);
        Button Niveau3 = (Button) findViewById(R.id.Lvl3);

        Niveau1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Fenetre_Sudoku_2.class);
                Bundle extras = new Bundle();
                extras.putInt("level", 1);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        Niveau2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Fenetre_Sudoku_2.class);
                Bundle extras = new Bundle();
                extras.putInt("level", 2);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        Niveau3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Fenetre_Sudoku_2.class);
                Bundle extras = new Bundle();
                extras.putInt("level", 3);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}
