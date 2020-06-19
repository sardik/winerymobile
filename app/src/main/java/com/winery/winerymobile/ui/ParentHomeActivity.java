package com.winery.winerymobile.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.dbhelper.SessionManagement;

import java.util.HashMap;

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
    private Fragment chatFragment = null;
    private Fragment messageFragment = null;
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
                        if (chatFragment == null)
                            snackBar();
                        else
                            snackBar();
                        break;
                    case R.id.navigation_4:
                        if (messageFragment == null)
                            fragment = new ListMessageRecontest();
//                            snackBar();
                        else
                            fragment = messageFragment;
//                            snackBar();
                        break;
                    case R.id.navigation_5:
                        if (profileFragment == null)
                            fragment = new AccountFragment();
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

    public void snackBar(){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.fragment), "Dalam tahap pengembangan", Snackbar.LENGTH_SHORT)
                .setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finishAffinity();
    }
}
