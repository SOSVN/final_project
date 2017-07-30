package com.coderschool.sosvn.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.card.SliderAdapter;
import com.coderschool.sosvn.factory.TextViewFactory;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentMainFragment extends Fragment {

    private final int[] pics = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5};
    private final int[] descriptions = {R.string.text1, R.string.text2, R.string.text3, R.string.text4, R.string.text5};
    private final String[] countries = {"Hanh Phuc International Hospital"
            , "Tu Du hospital"
            , "Oncology Hospital Ho Chi Minh City"
            , "Columbia Asia International Clinic - Saigon"
            , "International Neurosurgery Hospital"};
    private final String[] places = {"97, Nguyen Thi Minh Khai Street, Ben Nghe Ward, District 1, Hồ Chí Minh"
            , "284 Cống Quỳnh, Phạm Ngũ Lão, Hồ Chí Minh, Phạm Ngũ Lão Quận 1 Hồ Chí Minh"
            , "3 Nơ Trang Long, phường 7, Bình Thạnh, Hồ Chí Minh"
            , "8, Alexandre de Rhodes, Bến Nghé, Quận 1, Bến Nghé"
            , "65 Lũy Bán Bích, Tân Thới Hoà, Tân Phú, Hồ Chí Minh"};
    private final String[] temperatures = {"4.4", "4.0", "3.7", "3.0", "3.7"};
    private final String[] times = {"7:00-18:00", "8:00-16:00", "7:00-18:00"};
    private SliderAdapter sliderAdapter;
    private CardSliderLayoutManager layoutManger;

    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.rv_hospitals)
    RecyclerView rvHospitals;
    @BindView(R.id.ts_rating)
    TextSwitcher ratingSwitcher;
    @BindView(R.id.ts_address)
    TextSwitcher addressSwitcher;
    @BindView(R.id.ts_clock)
    TextSwitcher clockSwitcher;
    @BindView(R.id.tv_title_hospital)
    TextView tvTitleHospital;
    @BindView(R.id.tv_title_hospital_switch)
    TextView tvTitleHospitalSwitch;

    private int countryOffset1;
    private int countryOffset2;
    private long countryAnimDuration;
    private int currentPosition;

    // This event fires 2nd, before views are created for the fragment
    // The onCreate method is called when the Fragment instance is being created, or re-created.
    // Use onCreate for any standard setup that does not require the activity to be fully created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sliderAdapter = new SliderAdapter(pics, 5, new OnCardClickListener());
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_main, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    // This event is triggered soon after onCreateView().
    // onViewCreated() is only called if the view returned from onCreateView() is non-null.
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Phong test
        initRecyclerView();
        initCountryText();
        initSwitchers();
        //end
    }

    // This method is called after the parent Activity's onCreate() method has completed.
    // Accessing the view hierarchy of the parent activity must be done in the onActivityCreated.
    // At this point, it is safe to search for activity View objects by their ID, for example.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private class OnCardClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            final CardSliderLayoutManager lm = (CardSliderLayoutManager) rvHospitals.getLayoutManager();

            if (lm.isSmoothScrolling()) {
                return;
            }

            final int activeCardPosition = lm.getActiveCardPosition();
            if (activeCardPosition == RecyclerView.NO_POSITION) {
                return;
            }

            final int clickedPosition = rvHospitals.getChildAdapterPosition(view);
            if (clickedPosition == activeCardPosition) {
                // Start activity item detail
            } else if (clickedPosition > activeCardPosition) {
                rvHospitals.smoothScrollToPosition(clickedPosition);
                onActiveCardChange(clickedPosition);
            }
        }
    }

    private void initRecyclerView() {
        rvHospitals.setAdapter(sliderAdapter);
        rvHospitals.setHasFixedSize(true);

        rvHospitals.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    onActiveCardChange();
                }
            }
        });

        layoutManger = (CardSliderLayoutManager) rvHospitals.getLayoutManager();

        new CardSnapHelper().attachToRecyclerView(rvHospitals);
    }

    private void initCountryText() {
        countryAnimDuration = 350;
        countryOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
        countryOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);

        tvTitleHospital.setX(countryOffset1);
        tvTitleHospitalSwitch.setX(countryOffset2);
        tvTitleHospital.setText(countries[0]);
        tvTitleHospitalSwitch.setAlpha(0f);
    }

    private void initSwitchers() {
        ratingSwitcher.setFactory(new TextViewFactory(R.style.RatingTextView, true, getContext()));
        ratingSwitcher.setCurrentText(temperatures[0]);

        addressSwitcher.setFactory(new TextViewFactory(R.style.AddressTextView, false, getContext()));
        addressSwitcher.setCurrentText(places[0]);

        clockSwitcher.setFactory(new TextViewFactory(R.style.ClockTextView, false, getContext()));
        clockSwitcher.setCurrentText(times[0]);
    }

    private void onActiveCardChange() {
        final int pos = layoutManger.getActiveCardPosition();
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return;
        }

        onActiveCardChange(pos);
    }

    private void onActiveCardChange(int pos) {
        int animH[] = new int[]{R.anim.slide_in_right, R.anim.slide_out_left};
        int animV[] = new int[]{R.anim.slide_in_top, R.anim.slide_out_bottom};

        final boolean left2right = pos < currentPosition;
        if (left2right) {
            animH[0] = R.anim.slide_in_left;
            animH[1] = R.anim.slide_out_right;

            animV[0] = R.anim.slide_in_bottom;
            animV[1] = R.anim.slide_out_top;
        }

        setCountryText(countries[pos % countries.length], left2right);

        ratingSwitcher.setInAnimation(getContext(), animH[0]);
        ratingSwitcher.setOutAnimation(getContext(), animH[1]);
        ratingSwitcher.setText(temperatures[pos % temperatures.length]);

        addressSwitcher.setInAnimation(getContext(), animV[0]);
        addressSwitcher.setOutAnimation(getContext(), animV[1]);
        addressSwitcher.setText(places[pos % places.length]);

        clockSwitcher.setInAnimation(getContext(), animV[0]);
        clockSwitcher.setOutAnimation(getContext(), animV[1]);
        clockSwitcher.setText(times[pos % times.length]);

        currentPosition = pos;
    }

    private void setCountryText(String text, boolean left2right) {
        final TextView invisibleText;
        final TextView visibleText;
        if (tvTitleHospital.getAlpha() > tvTitleHospitalSwitch.getAlpha()) {
            visibleText = tvTitleHospital;
            invisibleText = tvTitleHospitalSwitch;
        } else {
            visibleText = tvTitleHospitalSwitch;
            invisibleText = tvTitleHospital;
        }

        final int vOffset;
        if (left2right) {
            invisibleText.setX(0);
            vOffset = countryOffset2;
        } else {
            invisibleText.setX(countryOffset2);
            vOffset = 0;
        }

        invisibleText.setText(text);

        final ObjectAnimator iAlpha = ObjectAnimator.ofFloat(invisibleText, "alpha", 1f);
        final ObjectAnimator vAlpha = ObjectAnimator.ofFloat(visibleText, "alpha", 0f);
        final ObjectAnimator iX = ObjectAnimator.ofFloat(invisibleText, "x", countryOffset1);
        final ObjectAnimator vX = ObjectAnimator.ofFloat(visibleText, "x", vOffset);

        final AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(iAlpha, vAlpha, iX, vX);
        animSet.setDuration(countryAnimDuration);
        animSet.start();
    }

//    private void initBackground() {
//        Bitmap image = BitmapFactory.decodeResource(getResources(), pics[0]);
//        BlurImage.blur(getContext(), image);
//        ivBg.setImageBitmap(image);
//    }
//
//    private void setBackground() {
//        new MyAsyncTask().execute();
//    }
//
//    private class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {
//
//        protected Bitmap doInBackground(String... strings) {
//            Bitmap image = BitmapFactory.decodeResource(getResources(), pics[currentPosition]);
//            BlurImage.blur(getContext(), image);
//            return image;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            ivBg.setImageBitmap(result);
//        }
//    }
}