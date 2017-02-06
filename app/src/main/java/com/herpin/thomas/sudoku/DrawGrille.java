package com.herpin.thomas.sudoku;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawGrille extends View implements View.OnTouchListener {

    final static int Case = 9;

    int num = 0;
    int[][] grille = null;

    public DrawGrille(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        this.setOnTouchListener(this);
    }

    public void create(int[][] grille){
        this.grille = grille;
    }

    public boolean onTouch(View v, MotionEvent event) {
        int x =(int)event.getX();
        int y =(int)event.getY();
        int xTop;
        int yTop;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for(int i=1;i<=Case;i++){
                    if(x >= getWidth()/10*i-50 && x <=  getWidth()/10*i+50 && y >=  getWidth()+50 && y <=  getWidth()+150){
                        num = i;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (num != 0){
                    xTop = x / (getWidth() /Case);
                    yTop = y / (getWidth() /Case);
                    if (xTop <Case && yTop <Case){
                        grille[xTop][yTop] = num;
                    }
                    invalidate();
                    num = 0;
                }
                break;
        }
        this.invalidate();
        return true;
    }

    protected void onDraw(Canvas canvas) {
        Paint draw = new Paint();
        draw.setColor(Color.GRAY);
        for(int i = 0; i<= Case; i++){
            if(i % 3 == 0){
                draw.setStrokeWidth(5);
            }
            else{
                draw.setStrokeWidth(2);
            }
            canvas.drawLine(getWidth() / Case *i, 0, getWidth() / 9 * i, getWidth(), draw);
            canvas.drawLine(0, getWidth()/ Case *i, getWidth(), getWidth() / 9 * i, draw);
        }
        Paint rectangle = new Paint();
        rectangle.setStyle(Paint.Style.STROKE);
        draw.setStrokeWidth(1);
        Paint paintString = new Paint();
        paintString.setTextSize(60);
        for (int i = 1; i<= Case; i++){
            paintString.setColor(Color.BLACK);
            canvas.drawRect( getWidth() / 10 *i - 50, getWidth() + 50,  getWidth() / 10 * i + 50, getWidth()+ 150, rectangle);
            canvas.drawText(String.valueOf(i), getWidth() / 10 *i - 25, getWidth() + 125, paintString);
        }

        for (int i = 0; i < Case; i++){
            for (int j = 0; j < Case; j++){
                if(grille[i][j] != 0){
                    paintString.setColor(Color.BLUE);
                    canvas.drawText(String.valueOf(grille[i][j]), getWidth() / 9 *i +65, getWidth() / 9 *j +85, paintString);
                }
            }
        }
    }
}
