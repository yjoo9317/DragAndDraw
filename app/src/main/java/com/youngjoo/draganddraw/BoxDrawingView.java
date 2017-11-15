package com.youngjoo.draganddraw;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by samsung on 2017. 11. 14..
 */

public class BoxDrawingView extends View {
    private static final String TAG = "BoxDrawingView";

    public BoxDrawingView(Context context){
        this(context, null);
    }

    public BoxDrawingView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        PointF current = new PointF(event.getX(), event.getY());
        String action = "";
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                action = "Action_Down";
                break;
            case MotionEvent.ACTION_MOVE :
                action = "Action_Move";
                break;
            case MotionEvent.ACTION_UP :
                action = "Action_Up";
                break;
            case MotionEvent.ACTION_CANCEL :
                action = "Action_Cancel";
                break;
        }
        Log.i(TAG, action+" at "+ "("+current.x+", "+current.y+")");
        return true;
    }
}
