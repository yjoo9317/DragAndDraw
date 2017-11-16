package com.youngjoo.draganddraw;

import android.graphics.PointF;

/**
 * Created by samsung on 2017. 11. 16..
 */

public class Box {

    PointF mOrigin;
    PointF mCurrent;

    public Box(PointF origin){
        mOrigin = origin;
        mCurrent = origin;
    }

    public PointF getCurrent() {
        return mCurrent;
    }

    public void setCurrent(PointF current) {
        mCurrent = current;
    }

    public PointF getOrigin() {
        return mOrigin;
    }
}
