package com.app.iqbaladinur.opacsuka;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.iqbaladinur.opacsuka.Fragment.AboutFragment;
import com.app.iqbaladinur.opacsuka.Fragment.SavedDataFragment;
import com.app.iqbaladinur.opacsuka.Fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {

    EditText SearchBar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (findViewById(R.id.content) != null){
                        SearchFragment search_fragment = new SearchFragment();
                        FragmentTransaction ft = getFragmentManager ().beginTransaction();
                        ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_right);
                        ft.replace(R.id.content, search_fragment).commit();

                        LayoutInflater mInflater = LayoutInflater.from(MainActivity.this);
                        ActionBar mActionBar = getSupportActionBar();
                        mActionBar.setDisplayShowHomeEnabled(false);
                        mActionBar.setDisplayShowTitleEnabled(false);
                        View mCustomView = mInflater.inflate(R.layout.my_action_bar, null);
                        TextView title = (TextView) mCustomView.findViewById(R.id.title_text);
                        mActionBar.setCustomView(mCustomView);
                        mActionBar.setDisplayShowCustomEnabled(true);
                        title.setText("OPAC");
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if (findViewById(R.id.content) != null){
                        SavedDataFragment data_fragment = new SavedDataFragment();
                        FragmentTransaction ft = getFragmentManager ().beginTransaction();
                        ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_right);
                        ft.replace(R.id.content, data_fragment).commit();

                        LayoutInflater mInflater = LayoutInflater.from(MainActivity.this);
                        ActionBar mActionBar = getSupportActionBar();
                        mActionBar.setDisplayShowHomeEnabled(false);
                        mActionBar.setDisplayShowTitleEnabled(false);
                        View mCustomView = mInflater.inflate(R.layout.my_action_bar, null);
                        TextView title = (TextView) mCustomView.findViewById(R.id.title_text);
                        mActionBar.setCustomView(mCustomView);
                        mActionBar.setDisplayShowCustomEnabled(true);
                        title.setText("Saved List");
                    }
                    return true;
                case R.id.navigation_notifications:
                    AboutFragment about_fragment = new AboutFragment();
                    FragmentTransaction ft = getFragmentManager ().beginTransaction();
                    ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_right);
                    ft.replace(R.id.content, about_fragment).commit();

                    LayoutInflater mInflater = LayoutInflater.from(MainActivity.this);
                    ActionBar mActionBar = getSupportActionBar();
                    mActionBar.setDisplayShowHomeEnabled(false);
                    mActionBar.setDisplayShowTitleEnabled(false);
                    View mCustomView = mInflater.inflate(R.layout.my_action_bar, null);
                    TextView title = (TextView) mCustomView.findViewById(R.id.title_text);
                    mActionBar.setCustomView(mCustomView);
                    mActionBar.setDisplayShowCustomEnabled(true);
                    title.setText("About");
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.my_action_bar, null);
        TextView title = (TextView) mCustomView.findViewById(R.id.title_text);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        title.setText("OPAC");

        if (findViewById(R.id.content) != null){
            SearchFragment search_fragment = new SearchFragment();
            FragmentTransaction ft = getFragmentManager ().beginTransaction();
            ft.replace(R.id.content, search_fragment).commit();
        }
    }

}
