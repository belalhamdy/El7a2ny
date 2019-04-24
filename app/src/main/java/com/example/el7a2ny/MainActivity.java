package com.example.el7a2ny;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Class fragmentClass;
    SNavigationDrawer sNavigationDrawer;
    public static Fragment fragment;


    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        sNavigationDrawer = findViewById(R.id.navigationDrawer);

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("العلاج",R.drawable.elag));
        menuItems.add(new MenuItem("الوقاية",R.drawable.wekaya));
        menuItems.add(new MenuItem("الإعدادات",R.drawable.settings));
        menuItems.add(new MenuItem("عن التطبيق",R.drawable.about));

        sNavigationDrawer.setMenuItemList(menuItems);

        // here changes the starting frag
        sNavigationDrawer.setAppbarTitleTV("العلاج");
        fragmentClass =  elaag.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {

                switch (position){
                    case 0:
                       fragmentClass = elaag.class;
                       break;
                    case 1:
                        fragmentClass = wekaya.class;
                        break;
                    case 2:
                        fragmentClass = settings.class;
                        break;
                    case 3:
                        fragmentClass = about.class;
                        break;
                }

                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
