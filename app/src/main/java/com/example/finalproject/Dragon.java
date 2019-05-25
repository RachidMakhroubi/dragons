package com.example.finalproject;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Dragon {

    Bitmap dragon[] = new Bitmap[7]; // create an array type bitmap to hold the images
    int dragon_x; // keep track of the dragon coordinate x
    int dragon_y; // keep track of the dragon coordinate y
    int speed; //speed of the dragon;
    int dragonImage; // the frame of the image of the dragon (we have from 0 to 8)
    Random random; // to generate random number. This number will be added the positions of the dragon

    public Dragon(Context context) {

        dragon[0]= BitmapFactory.decodeResource(context.getResources(),R.drawable.frame_0);
        dragon[1]= BitmapFactory.decodeResource(context.getResources(),R.drawable.frame_1);
        dragon[2]= BitmapFactory.decodeResource(context.getResources(),R.drawable.frame_2);
        dragon[3]= BitmapFactory.decodeResource(context.getResources(),R.drawable.frame_3);
        dragon[4]= BitmapFactory.decodeResource(context.getResources(),R.drawable.frame_4);
        dragon[5]= BitmapFactory.decodeResource(context.getResources(),R.drawable.frame_5);
        dragon[6]= BitmapFactory.decodeResource(context.getResources(),R.drawable.frame_6);

        random = new Random();
        reset();


    }


    public Bitmap getBitmap(){ return dragon[dragonImage];}

    public int getWidth(){ return dragon[0].getWidth();}

    public int getHeight(){ return dragon[0].getHeight();}

    public void reset(){

        dragon_x = ViewClass.width + random.nextInt(1200);
        dragon_y = random.nextInt(400);
        speed = 8 + random.nextInt(13);
        dragonImage = 0;

    }

}
