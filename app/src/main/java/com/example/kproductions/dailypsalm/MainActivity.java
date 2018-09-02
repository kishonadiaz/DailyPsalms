package com.example.kproductions.dailypsalm;

import android.Manifest;
import android.app.AlarmManager;
import android.app.FragmentManager;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kproductions.dailypsalm.notification.MyMediaPlayer;
import com.example.kproductions.dailypsalm.notification.NotificationReceiver;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends FragmentActivity implements TabLayout.OnTabSelectedListener,PsalmOTheDay.OnFragmentInteractionListener,
UserPref.OnFragmentInteractionListener{

    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
    private static final int DAILY_REMINDER_REQUEST_CODE = 42881;
    private TabLayout tabLayout;
    private TextView content;
    private static boolean morningornight = true;
    private static boolean checkstate;
    static int seekbarProg = 25;
    AlarmManager alarmManager;
    PendingIntent broadcast;
    MyTextToSpeech myTextToSpeech;

    View popupView;
    PopupWindow popupWindow;
    Button closebtn;

    private FragmentManager fragmentManager;
    private ViewPager viewPager;
    String[] pslams = {Psalms.psalm_one,Psalms.psalm_two,Psalms.psalm_three,Psalms.psalm_four,
            Psalms.psalm_five,Psalms.psalm_six,Psalms.psalm_seven,Psalms.psalm_eight,
            Psalms.psalm_nine,Psalms.psalm_ten,Psalms.psalm_eleven,Psalms.psalm_twelve,
            Psalms.psalm_thriteen,Psalms.psalm_fourteen,Psalms.psalm_fifteen,Psalms.psalm_sixteen,
            Psalms.psalm_seventeen,Psalms.psalm_eighteen,Psalms.psalm_nineteen,
            Psalms.psalm_twenty,Psalms.psalm_twentyOne,Psalms.psalm_twentyTwo,
            Psalms.psalm_twentyThree,Psalms.psalm_twentyFour,Psalms.psalm_twentyFive,
            Psalms.psalm_twentySix,Psalms.psalm_twentySeven,Psalms.psalm_twentyEight,
            Psalms.psalm_twentyNine,Psalms.psalm_thirty,Psalms.psalm_thirtyOne,
            Psalms.psalm_thirtyTwo,Psalms.psalm_thirtyThree,Psalms.psalm_thirtyFour,
            Psalms.psalm_thirtyFive,Psalms.psalm_thirtySix,Psalms.psalm_thirtySeven,
            Psalms.psalm_thirtyEight,Psalms.psalm_thirtyNine,Psalms.psalm_forty,
            Psalms.psalm_fortyOne,Psalms.psalm_fortyTwo,Psalms.psalm_fortyThree,
            Psalms.psalm_fortyFour,Psalms.psalm_fortyFive,Psalms.psalm_fortySix,
            Psalms.psalm_fortySeven,Psalms.psalm_fortyEight,Psalms.psalm_fortyNine,
            Psalms.psalm_fifty,Psalms.psalm_fiftyOne,Psalms.psalm_fiftyTwo,
            Psalms.psalm_fiftyThree,Psalms.psalm_fiftyFour,Psalms.psalm_fiftyFive,
            Psalms.psalm_fiftySix,Psalms.psalm_fiftySeven,Psalms.psalm_fiftyEight,
            Psalms.psalm_fiftyNine,Psalms.psalm_sixty,Psalms.psalm_sixtyOne,
            Psalms.psalm_sixtyTwo,Psalms.psalm_sixtyThree,Psalms.psalm_sixtyFour,
            Psalms.psalm_sixtyFive,Psalms.psalm_sixtySix,Psalms.psalm_sixtySeven,
            Psalms.psalm_sixtyEight,Psalms.psalm_sixtyNine,Psalms.psalm_seventy,
            Psalms.psalm_seventyOne,Psalms.psalm_seventyTwo,Psalms.psalm_seventyThree,
            Psalms.psalm_seventyFour,Psalms.psalm_seventyFive,Psalms.psalm_seventySix,
            Psalms.psalm_seventySeven,Psalms.psalm_seventyEight,Psalms.psalm_seventyNine,
            Psalms.psalm_eighty,Psalms.psalm_eightyOne,Psalms.psalm_eightyTwo,
            Psalms.psalm_eightyThree,Psalms.psalm_eightyFour,Psalms.psalm_eightyFive,
            Psalms.psalm_eightySix,Psalms.psalm_eightySeven,Psalms.psalm_eightyEight,
            Psalms.psalm_eightyNine,Psalms.psalm_ninty,Psalms.psalm_nintyOne,
            Psalms.psalm_nintyTwo,Psalms.psalm_nintyThree,Psalms.psalm_nintyFour,
            Psalms.psalm_nintyFive,Psalms.psalm_nintySix,Psalms.psalm_nintySeven,
            Psalms.psalm_nintyEight,Psalms.psalm_nintyNine,Psalms.psalm_oneHundred,
            Psalms.psalm_oneHundredOne,Psalms.psalm_oneHundredTwo,
            Psalms.psalm_oneHundredThree,Psalms.psalm_oneHundredFour,
            Psalms.psalm_oneHundredFive,Psalms.psalm_oneHundredSix,
            Psalms.psalm_oneHundredSeven,Psalms.psalm_oneHundredEight,
            Psalms.psalm_oneHundredNine,Psalms.psalm_oneHundredTen,
            Psalms.psalm_oneHundredEleven,Psalms.psalm_oneHundredTwelve,
            Psalms.psalm_oneHundredThirteen,Psalms.psalm_oneHundredFourteen,
            Psalms.psalm_oneHundredFivteen,Psalms.psalm_oneHundredSixteen,
            Psalms.psalm_oneHundredSeventeen,Psalms.psalm_oneHundredEightTeen,
            Psalms.psalm_oneHundredNinteen,Psalms.psalm_oneHundredTwenty,
            Psalms.psalm_oneHundredTwentyOne,Psalms.psalm_oneHundredTwentyTwo,
            Psalms.psalm_oneHundredTwentyThree,Psalms.psalm_oneHundredTwentyFour,
            Psalms.psalm_oneHundredTwentyFive,Psalms.psalm_oneHundredTwentySix,
            Psalms.psalm_oneHundredTwentySeven,Psalms.psalm_oneHundredTwentyEight,
            Psalms.psalm_oneHundredTwentyNine,Psalms.psalm_oneHundredThirty,
            Psalms.psalm_oneHundredThirtyOne,Psalms.psalm_oneHundredThirtyTwo,
            Psalms.psalm_oneHundredThirtyThree,
            Psalms.psalm_oneHundredThirtyFour,Psalms.psalm_oneHundredThirtyFive,
            Psalms.psalm_oneHundredThirtySix,Psalms.psalm_oneHundredThirtySeven,
            Psalms.psalm_oneHundredThirtyEight,Psalms.psalm_oneHundredThirtyNine,
            Psalms.psalm_oneHundredFourty,Psalms.psalm_oneHundredFourtyOne,
            Psalms.psalm_oneHundredFourtyTwo,Psalms.psalm_oneHundredFourtyThree,
            Psalms.psalm_oneHundredFourtyFour,
            Psalms.psalm_oneHundredFourtyFive,Psalms.psalm_oneHundredFourtySix,
            Psalms.psalm_oneHundredFourtySeven,Psalms.psalm_oneHundredFourtyEight,
            Psalms.psalm_oneHundredFourtyNine,Psalms.psalm_oneHundredFifty};
    int[] pslamsRaw = {R.raw.psalm_one,R.raw.psalm_two,R.raw.psalm_three,R.raw.psalm_four,
            R.raw.psalm_five,R.raw.psalm_six,R.raw.psalm_seven,R.raw.psalm_eight,
            R.raw.psalm_nine,R.raw.psalm_ten,R.raw.psalm_eleven,R.raw.psalm_twelve,
            R.raw.psalm_thirteen,R.raw.psalm_fourteen,R.raw.psalm_fifteen,R.raw.psalm_sixteen,
            R.raw.psalm_seventeen,R.raw.psalm_eightteen,R.raw.psalm_nineteen,
            R.raw.psalm_twenty,R.raw.psalm_twentyone,R.raw.psalm_twentytwo,
            R.raw.psalm_twentythree,R.raw.psalm_twentyfour,R.raw.psalm_twentyfive,
            R.raw.psalm_twentysix,R.raw.psalm_twentyseven,R.raw.psalm_twentyeight,
            R.raw.psalm_twentynine,R.raw.psalm_thirty,R.raw.psalm_thirtyone,
            R.raw.psalm_thirtytwo,R.raw.psalm_thirtythree,R.raw.psalm_thirtyfour,
            R.raw.psalm_thirtyfive,R.raw.psalm_thirtysix,R.raw.psalm_thirtyseven,
            R.raw.psalm_thirtyeight,R.raw.psalm_thirtynine,R.raw.psalm_forty,
            R.raw.psalm_fortyone,R.raw.psalm_fortytwo,R.raw.psalm_fortythree,
            R.raw.psalm_fortyfour,R.raw.psalm_fortyfive,R.raw.psalm_fortysix,
            R.raw.psalm_fortyseven,R.raw.psalm_fortyeight,R.raw.psalm_fortynine,
            R.raw.psalm_fifty,R.raw.psalm_fiftyone,R.raw.psalm_fiftytwo,
            R.raw.psalm_fiftythree,R.raw.psalm_fiftyfour,R.raw.psalm_fiftyfive,
            R.raw.psalm_fiftysix,R.raw.psalm_fiftyseven,R.raw.psalm_fiftyeight,
            R.raw.psalm_fiftynine,R.raw.psalm_sixty,R.raw.psalm_sixtyone,
            R.raw.psalm_sixtytwo,R.raw.psalm_sixtythree,R.raw.psalm_sixtyfour,
            R.raw.psalm_sixtyfive,R.raw.psalm_sixtysix,R.raw.psalm_sixtyseven,
            R.raw.psalm_sixtyeight,R.raw.psalm_sixtynine,R.raw.psalm_seventy,
            R.raw.psalm_seventyone,R.raw.psalm_seventytwo,R.raw.psalm_seventythree,
            R.raw.psalm_seventyfour,R.raw.psalm_seventyfive,R.raw.psalm_seventysix,
            R.raw.psalm_seventyseven,R.raw.psalm_seventyeight,R.raw.psalm_seventynine,
            R.raw.psalm_eighty,R.raw.psalm_eightyone,R.raw.psalm_eightytwo,
            R.raw.psalm_eightythree,R.raw.psalm_eightyfour,R.raw.psalm_eightyfive,
            R.raw.psalm_eightysix,R.raw.psalm_eightyseven,R.raw.psalm_eightyeight,
            R.raw.psalm_eightynine,R.raw.psalm_ninty,R.raw.psalm_nintyone,
            R.raw.psalm_nintytwo,R.raw.psalm_nintythree,R.raw.psalm_nintyfour,
            R.raw.psalm_nintyfive,R.raw.psalm_nintysix,R.raw.psalm_nintyseven,
            R.raw.psalm_nintyeight,R.raw.psalm_nintynine,R.raw.psalm_onehundred,
            R.raw.psalm_onehundredone,R.raw.psalm_onehundredtwo,
            R.raw.psalm_onehundredthree,R.raw.psalm_onehundredfour,
            R.raw.psalm_onehundredfive,R.raw.psalm_onehundredsix,
            R.raw.psalm_onehundredseven,R.raw.psalm_onehundredeight,
            R.raw.psalm_onehundrednine,R.raw.psalm_onehundredten,
            R.raw.psalm_onehundredeleven,R.raw.psalm_onehundredtwelve,
            R.raw.psalm_onehundredthirteen,R.raw.psalm_onehundredfourteen,
            R.raw.psalm_onehundredfifteen,R.raw.psalm_onehundredsixteen,
            R.raw.psalm_onehundredseventeen,R.raw.psalm_onehundredeighteen,
            R.raw.psalm_onehundrednineteen,R.raw.psalm_onehundredtwenty,
            R.raw.psalm_onehundredtwentyone,R.raw.psalm_onehundredtwentytwo,
            R.raw.psalm_onehundredtwentythree,R.raw.psalm_onehundredtwentyfour,
            R.raw.psalm_onehundredtwentyfive,R.raw.psalm_onehundredtwentysix,
            R.raw.psalm_onehundredtwentyseven,R.raw.psalm_onehundredtwentyeight,
            R.raw.psalm_onehundredtwentynine,R.raw.psalm_onehundredthirty,
            R.raw.psalm_onehundredthirtyone,R.raw.psalm_onehundredthirtytwo,
            R.raw.psalm_onehundredthirtythree,
            R.raw.psalm_onehundredthirtyfour,R.raw.psalm_onehundredthirtyfive,
            R.raw.psalm_onehundredthirtysix,R.raw.psalm_onehundredthirtyseven,
            R.raw.psalm_onehundredthirtyeight,R.raw.psalm_onehundredthirtynine,
            R.raw.psalm_onehundredfourty,R.raw.psalm_onehundredfourtyone,
            R.raw.psalm_onehundredfourtytwo,R.raw.psalm_onehundredfourtythree,
            R.raw.psalm_onehundredfourtyfour,
            R.raw.psalm_onehundredfourtyfive,R.raw.psalm_onehundredfourtysix,
            R.raw.psalm_onehundredfourtyseven,R.raw.psalm_onehundredfourtyeight,
            R.raw.psalm_onehundredfourtynine,R.raw.psalm_onehundredfifty};



    private PagerAdapter adapter;
    private NotificationReceiver notificationReceiver;
    AdView mAdview;
    InterstitialAd interstitialAd;


    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");

        mAdview = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("905C153A2F072F3629B3A982E9655E70").build();
        mAdview.loadAd(adRequest);

        interstitialAd = new InterstitialAd(this);


        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());


        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);


        myTextToSpeech = new MyTextToSpeech(this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                myTextToSpeech.onInit(status);
            }
        });
        startActivityForResult(myTextToSpeech.getInent(),myTextToSpeech.MY_DATA_CHECK_CODE);




        popupView = inflater.inflate(R.layout.popup,null);

        int width = RelativeLayout.LayoutParams.MATCH_PARENT;
        int height = RelativeLayout.LayoutParams.MATCH_PARENT;

        boolean focusable = true;
        popupWindow = new PopupWindow(popupView,width,height,focusable);



        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addTab(tabLayout.newTab());
//        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager =(ViewPager)findViewById(R.id.pager);

        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        //viewPager.arrowScroll(View.FOCUS_RIGHT);

        tabLayout.setOnTabSelectedListener(this);

        tabLayout.setupWithViewPager(viewPager);

        notificationReceiver = new NotificationReceiver();

        isReadStoragePermissionGranted();
        isWriteStoragePermissionGranted();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)){
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:"+ getPackageName()));
            startActivityForResult(intent,CODE_DRAW_OVER_OTHER_APP_PERMISSION);
        }
        //Toast.makeText(this, morningornight+"", Toast.LENGTH_SHORT).show();

//        AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner(this);
//
//
//
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

//        Intent notificationIntent = new Intent(this, notificationReceiver.getClass());
//        broadcast = PendingIntent.getBroadcast(this, 100,
//                notificationIntent,PendingIntent.FLAG_CANCEL_CURRENT);
//
//        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//        Toast.makeText(this, ""+hour, Toast.LENGTH_LONG).show();
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY,10);
//
//        //calendar.set(Calendar.DAY_OF_WEEK, Calendar.DATE);
//        //calendar.add(Calendar.DATE, Calendar.DAY_OF_WEEK);
//
//
//        //alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, broadcast);
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),broadcast);



    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if(alarmManager != null || broadcast != null){
//            alarmManager.cancel(broadcast);
//        }
        if(closebtn != null){
            closebtn.performClick();
        }

        if(myTextToSpeech != null){
            myTextToSpeech.ttsStop();
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "OnResume", Toast.LENGTH_SHORT).show();
//        if(alarmManager != null || broadcast != null){
//            alarmManager.cancel(broadcast);
//        }

        if(closebtn != null){
            closebtn.performClick();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkstate = morningornight;
        morningornight = checkstate;
        Toast.makeText(this, "OnResume", Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(Build.VERSION.SDK_INT > 5 && keyCode == KeyEvent.KEYCODE_BACK &&
                event.getRepeatCount() == 0){
            Toast.makeText(this, "here", Toast.LENGTH_SHORT).show();


        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(myTextToSpeech != null){
            myTextToSpeech.ttsStop();
        }
        tsuper.onBackPressed();
    }

    private static boolean paused = false;
    @Override
    public void onPsalmInteraction(PsalmOTheDay psalmOTheDay, View view,int position,String message) {


//        MediaPlayer player = MediaPlayer.create(MainActivity.this,R.raw.psalms_one);
//        player.start();




        popupWindow.showAtLocation(view, Gravity.CENTER,0,0);


        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Toast.makeText(MainActivity.this, "heres", Toast.LENGTH_SHORT).show();
                if(myTextToSpeech != null){
                    myTextToSpeech.ttsStop();
                }
            }
        });
        ArrayList<String> titles = new ArrayList<>();


        ArrayList<String> pslamsArray = new ArrayList<>();




        closebtn = popupView.findViewById(R.id.closebtn);

        final Button pause_play = popupView.findViewById(R.id.pause_play);

        content = popupView.findViewById(R.id.content);

        content.setMovementMethod(new ScrollingMovementMethod());

        content.setText(pslams[position]);

        final MediaPlayer player = MediaPlayer.create(MainActivity.this,pslamsRaw[position]);
        player.start();




        //myTextToSpeech.ttsSpeed(.8f);
        //myTextToSpeech.setPitch(.3f);

        //myTextToSpeech.speakWords(pslams[position]);

        //content.setBackgroundColor(Color.argb((float)0.6,(float)255,(float) 255,(float) 255));

        //  pref page seekbar
        //        content.setTextSize(seekbarProg);




        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                player.stop();
                if(myTextToSpeech != null){
                    myTextToSpeech.ttsStop();
                }

                if(interstitialAd.isLoaded()){
                    interstitialAd.show();
                }
            }
        });

        pause_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!paused){
                    player.pause();
                    pause_play.setBackground(getDrawable(R.drawable.ic_pause));
                    paused = true;
                }else{
                    player.start();

                    pause_play.setBackground(getDrawable(R.drawable.ic_play1));
                    paused = false;
                }
            }
        });











    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            //Check if the permission is granted or not.
            if (resultCode == RESULT_OK) {
                //initializeView();
                Toast.makeText(this,
                        "Here",
                        Toast.LENGTH_SHORT).show();
            } else { //Permission is not available
                Toast.makeText(this,
                        "Draw over other app permission not available. Closing the application",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void sendNotification(View view){

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground);

        Intent intent = new Intent(this, FloatingViewService.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);

        Uri alarmsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this,"001")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("My notification")
                        .setContentText("Hello World")
                        .setLargeIcon(bm)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setLights(Color.parseColor("PURPLE"),1000,1500)
                        .setVibrate(new long[]{1000,1000,1000,1000,1000})
                        .setSound(alarmsound)
                        .setPriority(NotificationCompat.PRIORITY_MAX);
        finish();
        NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(this);


        assert mNotificationManager != null;
        mNotificationManager.notify(001,mBuilder.build());
    }



    @Override
    public void onUserPrefInteraction(Button btnone, Button btntwo, SeekBar seekBar) {

        btnone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                morningornight = true;
                Toast.makeText(MainActivity.this, morningornight+"", Toast.LENGTH_SHORT).show();

//                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//
//                Intent notificationIntent = new Intent(getBaseContext(), notificationReceiver.getClass());
//                PendingIntent broadcast = PendingIntent.getBroadcast(getBaseContext(), 100,
//                        notificationIntent,PendingIntent.FLAG_CANCEL_CURRENT);
//
//                int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//                Toast.makeText(getBaseContext(), ""+hour, Toast.LENGTH_LONG).show();
//
//                Calendar calendar = Calendar.getInstance();
//                if(morningornight) {
//                    if(hour == 9){
//                        calendar.add(hour, 1);
//                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), broadcast);
//                    }
//                }
//                alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),broadcast);
            }
        });

        btntwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                morningornight = false;
                Toast.makeText(MainActivity.this, morningornight+"", Toast.LENGTH_SHORT).show();


                int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                Calendar calendar = Calendar.getInstance();
                if(!morningornight) {
                    if(hour == 1) {
//                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//
//                        Intent notificationIntent = new Intent(getBaseContext(), notificationReceiver.getClass());
//                        PendingIntent broadcast = PendingIntent.getBroadcast(getBaseContext(), 100,
//                                notificationIntent,PendingIntent.FLAG_CANCEL_CURRENT);
//
//
//                        Toast.makeText(getBaseContext(), ""+hour, Toast.LENGTH_LONG).show();
//                        calendar.add(Calendar.HOUR_OF_DAY, 1);
//                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), broadcast);
                    }
                }

            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(content != null) {
                    Toast.makeText(MainActivity.this, progress+"", Toast.LENGTH_SHORT).show();
                    content.setTextSize(progress);
                    seekbarProg = progress;
                    seekBar.setProgress(seekbarProg);

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBar.setProgress(seekbarProg);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                writeToFile(String.valueOf(seekbarProg),"seekbar.txt",getApplicationContext());


            }
        });
    }

    //source https://stackoverflow.com/questions/14376807/how-to-read-write-string-from-a-file-in-android
    private void writeToFile(String data,String fileName , Context context){

        try {

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName,
                    Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }catch (IOException e){

        }

    }
    //source https://stackoverflow.com/questions/14376807/how-to-read-write-string-from-a-file-in-android
    private String readFromFile(String filename,Context context){
        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(filename);
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }catch (FileNotFoundException e){

        }catch (IOException e){

        }
        return ret;
    }
    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("write","Permission is granted2");
                return true;
            } else {

                Log.v("write","Permission is revoked2");
                requestPermissions( new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("write","Permission is granted2");
            return true;
        }
    }

    public  boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("read","Permission is granted1");
                return true;
            } else {

                Log.v("read","Permission is revoked1");
                requestPermissions( new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("read","Permission is granted1");
            return true;
        }
    }

    private class AsyncTaskRunner extends AsyncTask<Context,Class<?>,String>{

        private Context context;


        public AsyncTaskRunner(Context context) {
            this.context = context;

        }

        @Override
        protected void onProgressUpdate(Class<?>... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Context... contexts) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent notificationIntent = new Intent(context, notificationReceiver.getClass());
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 100,
                    notificationIntent,PendingIntent.FLAG_CANCEL_CURRENT);

            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            Toast.makeText(context, ""+hour, Toast.LENGTH_LONG).show();

            Calendar calendar = Calendar.getInstance();
            if(morningornight){

                if(hour == 9) {
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, 9);
                    calendar.set(Calendar.MINUTE, 20);
                    //calendar.add(Calendar.HOUR_OF_DAY, 1);
                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, broadcast);
                }

            }
            else
            {


                alarmManager.cancel(broadcast);
                calendar.clear();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, 18);
                calendar.set(Calendar.MINUTE, 30);

                //calendar.add(Calendar.HOUR_OF_DAY, 1);
                alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HALF_HOUR, broadcast);

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        public void init(){

        }
    }
}
