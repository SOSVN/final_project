package com.coderschool.sosvn.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.util.DrawBitmap;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportWhereItIsFragment extends Fragment {

    @BindView(R.id.iv_part_of_body)
    ImageView ivPartOfBody;

    private static Bitmap bm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_where_it_is, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //set default value for user
        bm = BitmapFactory.decodeResource(getResources(), R.mipmap.human_body_man_filled);

        ivPartOfBody.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                float posX = event.getX();
                float posY = event.getY();
                drawCircleAt(posX, posY);
                return false;
            }
        });
    }

    public Bitmap takeScreenShot(LinearLayout v) {
        v.setDrawingCacheEnabled(true);
        v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());

        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false); // clear drawing cache

        return b;
    }

    public void drawCircleAt(float posX, float posY) {
        ivPartOfBody.buildDrawingCache();
        bm = ivPartOfBody.getDrawingCache();

        int colorAtTouch = bm.getPixel((int) posX, (int) posY);

        String targetColorAsString = "#" + Integer.toHexString(ContextCompat.getColor(getActivity(), R.color.body));
        int targetColor = Color.parseColor(targetColorAsString);
        if (colorAtTouch == targetColor) {
            Canvas canvas = new Canvas(bm);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.RED);
            canvas.drawCircle(posX, posY, 25, paint);
            ivPartOfBody.setImageBitmap(bm);
        }
    }

    public static Bitmap getWhere() {
        return bm;
    }
}