package com.smartd90eventscamera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CameraMask extends View
{
    private static Paint paint = new Paint();
    private static Paint paintmarco = new Paint();
    private static Paint paintcontrol = new Paint();
    private static Paint painttext = new Paint();
    private static int width, height;
    private static float photoX1, photoX2, photoY1, photoY2;;

    public CameraMask(Context context, AttributeSet attr) {
        super(context, attr);
        setBackgroundColor(0x00ffffff);
        setWillNotCacheDrawing(false);
        onCanvasInitialization();
    }

    public void onCanvasInitialization() {

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        painttext.setStyle(Paint.Style.FILL);
        painttext.setColor(0xffffffff);
        painttext.setStrokeJoin(Paint.Join.ROUND);
        painttext.setStrokeCap(Paint.Cap.ROUND);
        painttext.setTextSize(h/30);
        painttext.setStrokeWidth(1);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xaf000000);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(2);

        paintcontrol.setStyle(Paint.Style.FILL);
        paintcontrol.setColor(0xff000);
        paintcontrol.setStrokeJoin(Paint.Join.ROUND);
        paintcontrol.setStrokeCap(Paint.Cap.ROUND);
        paintcontrol.setStrokeWidth(2);

        paintmarco.setStyle(Paint.Style.STROKE);
        paintmarco.setColor(0xdfffffff);
        paintmarco.setStrokeJoin(Paint.Join.ROUND);
        paintmarco.setStrokeCap(Paint.Cap.ROUND);
        paintmarco.setStrokeWidth(2);

        photoX1=0;
        photoX2=0;
        photoY1 = (height-width)/2;
        photoY2 = photoY1+width;
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawRect(0, 0, width, photoY1, paint);
        canvas.drawRect(0, photoY2, width, height, paint);
        canvas.drawRect(0, photoY2, width, height, paintcontrol);

    }


}

