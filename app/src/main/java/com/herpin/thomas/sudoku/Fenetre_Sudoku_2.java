package com.herpin.thomas.sudoku;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Fenetre_Sudoku_2 extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenetre_sudoku_2);

        final ListView liste = (ListView) findViewById(R.id.LaListView);
        Integer level = this.getIntent().getExtras().getInt("level");
        final ArrayList<vGrille> grilles = new ArrayList();
        AssetManager assetManager = getAssets();

        try {
            final InputStream input;
            input = assetManager.open("grilleslevels/" + level + ".txt");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();

            // byte buffer into a string
            String text = new String(buffer);
            String[] levelList = text.split("\n");
            Integer i = 1;
            for (String col: levelList){
                vGrille grid = new vGrille(level, i, 0, col);
                grilles.add(grid);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        MyAdapter adapter = new MyAdapter(this, grilles);
        liste.setAdapter(adapter);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final vGrille grille = grilles.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(Fenetre_Sudoku_2.this);
                builder.setTitle("Informations");
                builder.setMessage("Number: " + grille.getNumero() + " with " + grille.getPourcentage() + "%");
                builder.setCancelable(false);
                builder.setNegativeButton("Continuer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Fenetre_Sudoku_2.this, Fenetre_Sudoku_3.class);
                        intent.putExtra("grille", grille.getGrille());
                        startActivity(intent);
                    }
                });
                builder.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

}
    class vGrille {

        public int getNumero() {
            return numero;
        }

        public void setNumero(int num) {
            this.numero = num;
        }

        public int getNiveau() {
            return niveau;
        }

        public void setNiveau(int lvl) {
            this.niveau = lvl;
        }

        public int getPourcentage() {
            return pourcentage;
        }

        public void setPourcentage(int done) {
            this.pourcentage = done;
        }

        public String getGrille() {
            return grille;
        }

        public void setGrille(String grille) {
            this.grille = grille;
        }

        private int numero;
        private int niveau;
        private int pourcentage;
        private String grille;

        public vGrille(int num, int lvl, int done, String grille){

            this.numero = num;
            this.niveau = lvl;
            this.pourcentage = done;
            this.grille = grille;
        }
    }
