package com.herpin.thomas.sudoku;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;

public class Fenetre_Sudoku_3 extends Activity {

    int[][] grille = new int[9][9];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenetre__sudoku_3);

        startChronometre(findViewById(R.id.chronometre));

        String Grille = (String) this.getIntent().getExtras().get("grille");
        int compteur = 0;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                grille[j][i] = Integer.parseInt(""+Grille.charAt(compteur));
                compteur++;

            }
        }

        DrawGrille draw = (DrawGrille) findViewById(R.id.draw);

        draw.create(grille);
    }
    public void startChronometre(View view){
        ((Chronometer)findViewById(R.id.chronometre)).start();
    }
}
