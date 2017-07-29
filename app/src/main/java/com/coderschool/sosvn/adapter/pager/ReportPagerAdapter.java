package com.coderschool.sosvn.adapter.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.coderschool.sosvn.fragment.ReportSeverityFragment;
import com.coderschool.sosvn.fragment.ReportSumaryFragment;
import com.coderschool.sosvn.fragment.ReportWhatIsHappenedFragment;
import com.coderschool.sosvn.fragment.ReportWhereItIsFragment;

public class ReportPagerAdapter extends FragmentStatePagerAdapter {

    //    private final Random random = new Random();
    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public ReportPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount = tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
//        Bundle bundle = new Bundle();
//        bundle.putInt("moviePos", moviePos);
        switch (position) {
            case 0:
                // set Fragmentclass Arguments
                ReportSeverityFragment tab1 = new ReportSeverityFragment();
//                tab1.setArguments(bundle);
                return tab1;
            case 1:
                // set Fragmentclass Arguments
                ReportWhatIsHappenedFragment tab2 = new ReportWhatIsHappenedFragment();
//                tab2.setArguments(bundle);
                return tab2;
            case 2:
                // set Fragmentclass Arguments
                ReportWhereItIsFragment tab3 = new ReportWhereItIsFragment();
//                tab2.setArguments(bundle);
                return tab3;
            case 3:
                // set Fragmentclass Arguments
                ReportSumaryFragment tab4 = new ReportSumaryFragment();
//                tab2.setArguments(bundle);
                return tab4;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
//    @Override public Object instantiateItem(ViewGroup view, int position) {
//        TextView textView = new TextView(view.getContext());
//        textView.setText(String.valueOf(position + 1));
//        textView.setBackgroundColor(0xff000000 | random.nextInt(0x00ffffff));
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextColor(Color.WHITE);
//        textView.setTextSize(48);
//        view.addView(textView, ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT);
//        return textView;
//    }

}