package com.example.finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bat extends Dragon {
Bitmap[] bat = new Bitmap[4];
    public Bat(Context context) {
        super(context);

       bat[0] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bat_0);
       bat[1] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bat_1);
       bat[2] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bat_2);
       bat[3] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bat_3);


        reset();

    }

    @Override
    public Bitmap getBitmap() {
        return bat[dragonImage];
    }

    @Override
    public int getWidth() {
         return dragon[0].getWidth();
    }

    @Override
    public int getHeight() {
        return dragon[0].getHeight();
    }

    @Override
    public void reset() {
      dragon_x = -(200 + random.nextInt(1500));
      dragon_y = random.nextInt(200);
      speed = 20+ random.nextInt(21);
    }
}
