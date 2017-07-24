package com.coderschool.sosvn.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.util.DrawBitmap;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportWhereItIsFragment extends Fragment {

    @BindView(R.id.iv_part_of_body)
    ImageView ivPartOfBody;

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
//                final FrameLayout relativeLayout = view.findViewById(R.id.testttt1);
        final DrawBitmap drawBitmap = DrawBitmap.getInstance(getContext());
//        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        final ImageView test = view.findViewById(R.id.ivtestttt);

        ivPartOfBody.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {

                float posX = event.getX();
                float posY = event.getY();

                drawBitmap.drawCircleAt(posX, posY);
                ivPartOfBody.setImageBitmap(drawBitmap.getMutableBitmap());

                return false;
            }
        });

//        Button button = view.findViewById(R.id.btttestttt);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bitmap photo = drawBitmap.takeScreenShot(relativeLayout);
//                photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                test.setImageBitmap(photo);
//
//            }
//        });

    }
}