package com.example.ossusum.wrpg;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Benny on 9/7/2015.
 */
public class BackgroundService extends IntentService {
    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder builder;
    private SensorManager mSensorManager;
    private Sensor mStepCounterSensor;
    private Sensor mStepDetectorSensor;
    private Player mPlayer;

    public BackgroundService(Player player) {
        super("Counting your steps");
        mPlayer = player;
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        // Do the work that requires your app to keep the CPU running.
        // ...

        mSensorManager = (SensorManager) getApplicationContext().getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        mSensorManager.registerListener(new SensorHandle(getApplicationContext(),mPlayer),
                mStepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(new SensorHandle(getApplicationContext(), mPlayer),
                mStepDetectorSensor, SensorManager.SENSOR_DELAY_FASTEST);

        // Release the wake lock provided by the WakefulBroadcastReceiver.
        //TODO Find a way to pass exp to player profile when finished
        WakeUpReceiver.completeWakefulIntent(intent);

    }
}
