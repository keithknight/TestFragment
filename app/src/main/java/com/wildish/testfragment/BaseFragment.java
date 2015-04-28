package com.wildish.testfragment;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;

/**
 */
public class BaseFragment extends Fragment {
    private static final String TAG = BaseFragment.class.getSimpleName();
    protected TestFragmentHelper helper;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            helper = (TestFragmentHelper) activity;
        } catch (Exception e) {
            Log.e(TAG, "强制转换异常。");
        }
    }
}
