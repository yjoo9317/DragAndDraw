package com.youngjoo.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samsung on 2017. 11. 14..
 */

public class BoxDrawingView extends View {
    private static final String TAG = "BoxDrawingView";
    private static final String BOX_LIST = "BoxList";
    private static final String SUPER_STATE = "SuperState";

    private Box mCurrentBox;
    private List<Box> mBoxList = new ArrayList<>();

    private Paint mBoxPaint;
    private Paint mBackgroundPaint;
    private Paint mDefaultPaint;

    public BoxDrawingView(Context context){
        this(context, null);
    }

    public BoxDrawingView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);

        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);

        mDefaultPaint = new Paint();
        mDefaultPaint.setColor(Color.BLACK);
    }
    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawPaint(mBackgroundPaint);

        for(Box box : mBoxList){
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);

            //canvas.drawRect(new RectF(left, top, right, bottom), mBoxPaint);
            canvas.drawRect(left, top, right, bottom, mBoxPaint);
            //Log.i(TAG, "Drawing at "+left+", "+ top+", "+right+", "+bottom+")");
        }
    }

    @Override
    protected  void onRestoreInstanceState(Parcelable state){
        Log.i(TAG, "onRestoredInstanceState");
        if(state instanceof Bundle){
            Bundle bundle = (Bundle) state;
            mBoxList = (ArrayList<Box>)bundle.getSerializable(BOX_LIST);
            state = bundle.getParcelable(SUPER_STATE);
        }
        if(mBoxList != null){
            Log.i(TAG, "Restored Boxlist count: "+mBoxList.size());
        }
        invalidate();
        super.onRestoreInstanceState(state);
    }

    @Override
    protected Parcelable onSaveInstanceState(){
        Log.i(TAG, "onSaveInstanceState");
        Bundle bundle = new Bundle();
        bundle.putParcelable(SUPER_STATE, super.onSaveInstanceState());

        bundle.putSerializable(BOX_LIST,(Serializable)mBoxList);
        return bundle;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        PointF current = new PointF(event.getX(), event.getY());
        String action = "";
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                action = "Action_Down";
                mCurrentBox = new Box(current);
                mBoxList.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_MOVE :
                action = "Action_Move";
                if(mCurrentBox != null) {
                    mCurrentBox.setCurrent(current);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP :
                action = "Action_Up";
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL :
                action = "Action_Cancel";
                mCurrentBox = null;
                break;
        }
        Log.i(TAG, action+" at "+ "("+current.x+", "+current.y+")");
        return true;
    }
}
