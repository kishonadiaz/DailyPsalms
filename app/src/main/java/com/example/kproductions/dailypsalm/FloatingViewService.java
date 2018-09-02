package com.example.kproductions.dailypsalm;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.IBinder;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kproductions.dailypsalm.notification.NotificationReceiver;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by simplecast on 2/4/2018.
 * Code has example code from androidhive.
 * https://www.androidhive.info/2016/11/android-floating-widget-like-facebook-chat-head/
 */

public class FloatingViewService extends Service {
    private WindowManager mWindowManager;
    private View mFloatingView;
    ActivityManager activityManager;
    MotionEvent motionEvent;
    WindowManager.LayoutParams params;
    int favcounter = 0;
    boolean isfav = false;
    boolean islike = false;
    LinearLayout linearLayout;
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
            Psalms.psalm_oneHundredThirtyThree,Psalms.psalm_oneHundredThirtyThree,
            Psalms.psalm_oneHundredThirtyFour,Psalms.psalm_oneHundredThirtyFive,
            Psalms.psalm_oneHundredThirtySix,Psalms.psalm_oneHundredThirtySeven,
            Psalms.psalm_oneHundredThirtyEight,Psalms.psalm_oneHundredThirtyNine,
            Psalms.psalm_oneHundredFourty,Psalms.psalm_oneHundredFourtyOne,
            Psalms.psalm_oneHundredFourtyTwo,Psalms.psalm_oneHundredFourtyThree,
            Psalms.psalm_oneHundredFourtyThree,Psalms.psalm_oneHundredFourtyFour,
            Psalms.psalm_oneHundredFourtyFive,Psalms.psalm_oneHundredFourtySix,
            Psalms.psalm_oneHundredFourtySeven,Psalms.psalm_oneHundredFourtyEight,
            Psalms.psalm_oneHundredFourtyNine,Psalms.psalm_oneHundredFifty};;

    public FloatingViewService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i= 0; i < 300; i++){
                    Toast.makeText(getApplicationContext(), "d", Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
        Toast.makeText(this, "afaff", Toast.LENGTH_SHORT).show();
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        Random random = new Random();
        random.setSeed(149);

        //Inflate the floating view layout we created
        mFloatingView = LayoutInflater.from(this).inflate(R.layout.layout_floating_widget, null);

        //Add the view to the window.
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        //Specify the view position
        params.gravity = Gravity.TOP | Gravity.LEFT;        //Initially view will be added to top-left corner
        params.x = 90;
        params.y = 100;


        //Add the view to the window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        linearLayout = mFloatingView.findViewById(R.id.container);




        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView text = mFloatingView.findViewById(R.id.messageTxt);

        text.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));

        Toast.makeText(this, random+"rand", Toast.LENGTH_SHORT).show();
        text.setText(pslams[new Random().nextInt(pslams.length)]);

        linearLayout.setScrollContainer(true);
        text.setMovementMethod(new ScrollingMovementMethod());




        mWindowManager.addView(mFloatingView, params);






        //The root element of the collapsed view layout
        final View collapsedView = mFloatingView.findViewById(R.id.collapse_view);
        //The root element of the expanded view layout
        final View expandedView = mFloatingView.findViewById(R.id.expanded_container);

        final View rootrootcontainer = mFloatingView.findViewById(R.id.outerrootcontainer);


        //Set the close button
        final ImageView closeButtonCollapsed = (ImageView) mFloatingView.findViewById(R.id.close_btn);


        closeButtonCollapsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //close the service and remove the from from the window
//                Intent intent = new Intent(FloatingViewService.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                stopSelf();

            }
        });

        //Set the view while floating view is expanded.
        //Set the play button.






        //Set the close button
        ImageView closeButton = (ImageView) mFloatingView.findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*collapsedView.setVisibility(View.VISIBLE);
                expandedView.setVisibility(View.GONE);*/
                collapsedView.setVisibility(View.GONE);
                expandedView.setVisibility(View.GONE);
//                Intent intent = new Intent(FloatingViewService.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                stopSelf();
            }
        });

        //Open the application on thi button click
        ImageView openButton = (ImageView) mFloatingView.findViewById(R.id.open_button);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open the application  click.
                Intent intent = new Intent(FloatingViewService.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                //close the service and remove view from the view hierarchy
                stopSelf();
            }
        });

        //Drag and move floating view using user's touch action.
        mFloatingView.findViewById(R.id.root_container).setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        //remember the initial position.
                        initialX = params.x;
                        initialY = params.y;

                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        int Xdiff = (int) (event.getRawX() - initialTouchX);
                        int Ydiff = (int) (event.getRawY() - initialTouchY);

                        //The check for Xdiff <10 && YDiff< 10 because sometime elements moves a little while clicking.
                        //So that is click event.
                        if (Xdiff < 10 && Ydiff < 10) {
                            if (isViewCollapsed()) {
                                //When user clicks on the image view of the collapsed layout,
                                //visibility of the collapsed layout will be changed to "View.GONE"
                                //and expanded view will become visible.
                                collapsedView.setVisibility(View.GONE);

                                /*RelativeLayout.LayoutParams rr = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                                rr.height = RelativeLayout.LayoutParams.MATCH_PARENT;
                                rr.width = RelativeLayout.LayoutParams.MATCH_PARENT;*/
                                //FrameLayout.LayoutParams ff = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                                //ff.width = FrameLayout.LayoutParams.MATCH_PARENT;


                                //expandedView.setLayoutParams(rr);
                                expandedView.setVisibility(View.VISIBLE);
                            }
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);

                        //Update the layout with new X & Y coordinate
                        mWindowManager.updateViewLayout(mFloatingView, params);
                        return true;
                }
                return false;
            }
        });
//
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent(this, NotificationReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 2);
//        calendar.set(Calendar.MINUTE, 7);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.DATE);
        //calendar.add(Calendar.DAY_OF_WEEK, 1);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),broadcast);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,broadcast);
        startService(new Intent(FloatingViewService.this, FloatingViewService.class));

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //expandedView.setBackgroundColor(Color.TRANSPARENT);
                Toast.makeText(FloatingViewService.this, "heress", Toast.LENGTH_SHORT).show();
            }
        },300);
    }

    /**
     * Detect if the floating view is collapsed or expanded.
     *
     * @return true if the floating view is collapsed.
     */
    private boolean isViewCollapsed() {
        return mFloatingView == null || mFloatingView.findViewById(R.id.collapse_view).getVisibility() == View.VISIBLE;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }





    @Override
    public void onDestroy() {
        super.onDestroy();
     if (mFloatingView != null) mWindowManager.removeView(mFloatingView);

    }




}
