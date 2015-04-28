package com.wildish.testfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements TestFragmentHelper {
    private FragmentManager manager;

    private static final String FRAGMENT_HELLOWORLD = "FRAGMENT_HELLOWORLD";
    private static final String FRAGMENT_HAPPYWORLD = "FRAGMENT_HAPPYWORLD";
    private static final String FRAGMENT_MISERABLEWORLD = "FRAGMENT_MISERABLEWORLD";

    private String currentFrag;
    private String lastFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        manager = getFragmentManager();
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = manager.beginTransaction();
        currentFrag = FRAGMENT_HELLOWORLD;
        transaction.add(R.id.container, new HelloWorldFragment(), currentFrag)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }

    @Override
    public void goNextPageWithStack() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        currentFrag = FRAGMENT_HAPPYWORLD;
        transaction.replace(R.id.container, new HappyWorldFragment(), currentFrag);
        transaction.commit();
    }

    @Override
    public void goThirdPageWithStack() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        currentFrag = FRAGMENT_MISERABLEWORLD;
        transaction.replace(R.id.container, new MiserableWorldFragment(), currentFrag);
        transaction.commit();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class HelloWorldFragment extends BaseFragment {

        public HelloWorldFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ((TextView) rootView.findViewById(R.id.tvHelloWorld)).setText("第一页，你好世界");
            Button forward = (Button) rootView.findViewById(R.id.btnForward);
            forward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    helper.goNextPageWithStack();
                }
            });

            return rootView;
        }
    }

    public static class HappyWorldFragment extends BaseFragment {
        public HappyWorldFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ((TextView) rootView.findViewById(R.id.tvHelloWorld)).setText("第二页，幸福的世界");
            Button forward = (Button) rootView.findViewById(R.id.btnForward);
            forward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    helper.goThirdPageWithStack();
                }
            });

            return rootView;
        }
    }

    public static class MiserableWorldFragment extends BaseFragment {
        public MiserableWorldFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ((TextView) rootView.findViewById(R.id.tvHelloWorld)).setText("第三页，悲惨的世界");

            return rootView;
        }
    }
}
