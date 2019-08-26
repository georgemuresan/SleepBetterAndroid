package com.uos.admin.sleepbetter;


import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.arch.persistence.room.Room;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Calendar;

public class AllPages extends AppCompatActivity {
    static Class nextclass = AllPages.class;


    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_main_menu);


        toolbar = findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);


        viewPager = (ViewPager) findViewById(R.id.viewPager_id);

        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());

        adapter.addSection(new Menu(), "Menu");
        adapter.addSection(new Factors(), "Experiments");
        adapter.addSection(new Data(), "Data");
        adapter.addSection(new GoalDiary(), "Goal Diary");
        adapter.addSection(new CalendarPage(), "Calendar");
        adapter.addSection(new Update(), "Questionnaire");

        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout_id);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.imageedit_32_4626940004);
        tabLayout.getTabAt(1).setIcon(R.drawable.experiments);
        tabLayout.getTabAt(2).setIcon(R.drawable.data);
        tabLayout.getTabAt(3).setIcon(R.drawable.diaryic);
        tabLayout.getTabAt(4).setIcon(R.drawable.calendar);
        tabLayout.getTabAt(5).setIcon(R.drawable.pen);

    }


}