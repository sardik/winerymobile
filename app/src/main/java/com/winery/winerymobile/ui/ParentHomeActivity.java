package com.winery.winerymobile.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.winery.winerymobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParentHomeActivity extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.navigation)
    com.google.android.material.bottomnavigation.BottomNavigationView navigation;
    /** ButterKnife Code **/

    BottomNavigationView bottomNavigationView;
    private Fragment homeFragment = null;
    private Fragment historyFragment = null;
    private Fragment profileFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_home);
        ButterKnife.bind(this);

        bottomNavigationView = findViewById(R.id.navigation);
        initComponentNavigation();
        FragmentManager fragmentManager = getSupportFragmentManager();
        bottomNavigationView.getMenu().findItem(R.id.navigation_1).setChecked(true);
        fragmentManager.beginTransaction().replace(R.id.fragment, new HomeFragment()).commit();
    }

    private void initComponentNavigation() {
        // bottom navigation
        BottomNavigationView bn = bottomNavigationView ;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.navigation_1:

                        if (homeFragment == null)
                            fragment = new HomeFragment();
                        else
                            fragment = homeFragment;
                        break;
                    case R.id.navigation_2:
                        if (historyFragment == null)
                            fragment = new HistoryFragment();
                        else
                            fragment = historyFragment;
                        break;
                    case R.id.navigation_3:
                        Toast.makeText(getApplicationContext(),"Dalam pengembangan", Toast.LENGTH_LONG);
                        break;
                    case R.id.navigation_4:
                        Toast.makeText(getApplicationContext(),"Dalam pengembangan", Toast.LENGTH_LONG);
                        break;
                    case R.id.navigation_5:
                        if (profileFragment == null)
                            fragment = new ProfileFragment();
                        else
                            fragment = profileFragment;
                        break;
                }
                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment, fragment).commit();
                }
                return true;
            }
        });
    }
}
