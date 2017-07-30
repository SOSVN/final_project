package com.coderschool.sosvn.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class ViewCustom extends FrameLayout {
    private float radius;
    private Path path = new Path();
    private RectF rect = new RectF();

    public ViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.radius = attrs.getAttributeFloatValue(null, "corner_radius", 0f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // compute the path
        path.reset();
        rect.set(0, 0, w, h);
        path.addRoundRect(rect, radius, radius, Path.Direction.CW);
        path.close();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int save = canvas.save();
        canvas.clipPath(path);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(save);
    }
}