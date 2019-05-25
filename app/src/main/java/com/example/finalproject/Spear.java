package com.example.finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Spear {
    int x,y;
    int speed;
    Bitmap spear;
    public Spear(Context context){

       spear = BitmapFactory.decodeResource(context.getResources(),R.drawable.spear) ;
       x = ViewClass.width /2 - getSpearWidth()/2;
       y = ViewClass.heigh - getSpearHeight()/2;

       speed = 50;

    }


    public int getSpearWidth(){

        return spear.getWidth();

    }
    public int getSpearHeight(){

        return spear.getHeight();

    }




}
