package com.example.mohseenmukaddam.levelup.animation;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by mohseenmukaddam on 10/28/16.
 */

public class CharacterAnimated {
    private Bitmap bitmap;      // the animation sequence
    private Rect sourceRect;    // the rectangle to be drawn from the animation
    private int frameNr;        // number of frames in animation
    private int currentFrame;   // the current frame
    private long frameTicker;   // the time of the last frame update
    private int framePeriod;    // milliseconds between each frame (1000/fps)

    private int spriteWidth;    // the width of the sprite to calculate
    private int spriteHeight;   // the height of the sprite
    private int x;              // the X coordinate of the object (top left of the image)
    private int y;              // the Y coordinate of the object (top left of the image)

    public CharacterAnimated( Bitmap bitmap, int x, int y, int width, int height, int fps, int frameCount ){
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.currentFrame = 0;
        this.frameNr = frameCount;
        this.spriteHeight = bitmap.getHeight();
        this.spriteWidth = bitmap.getWidth() / frameCount;
        this.sourceRect = new Rect( 0, 0, this.spriteWidth, this.spriteHeight );
        this.framePeriod = 1000 / fps;
        this.frameTicker = 01;
    }

    public void Update( long time ){
        if( time > this.frameTicker + this.framePeriod ){
            this.frameTicker = time;
            this.currentFrame += 1;
            if( this.currentFrame >= this.frameNr )
                this.currentFrame = 0;
        }

        this.sourceRect.left = this.currentFrame * this.spriteWidth;
        this.sourceRect.right = this.sourceRect.left + this.spriteWidth;
    }
}
