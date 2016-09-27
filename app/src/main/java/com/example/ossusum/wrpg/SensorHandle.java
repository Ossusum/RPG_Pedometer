package com.example.ossusum.wrpg;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.Toast;

/**
 * Created by Benny on 9/7/2015.
 */
public class SensorHandle implements SensorEventListener {

    private Context mContext;
    private Player mPlayer;
    private int expGained = 0;
    SensorHandle(Context context, Player mPlayer){
        mContext = context;
        this.mPlayer = mPlayer;
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        float[] values = event.values;
        int value = -1;

        if (values.length > 0) {
            value = (int) values[0];
        }

        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            Toast.makeText(mContext, "Step Counter Detected : " + value, Toast.LENGTH_SHORT).show();
        } else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            // For test only. Only allowed value is 1.0 i.e. for step taken
            Toast.makeText(mContext,"Step Detector Detected : " + value,Toast.LENGTH_SHORT).show();
            ++expGained;
            //expBar.setProgress(++expGained);
            //mGainExp.setText(expGained +"");
        }
        //TODO check if this worked
        mPlayer.addExp(expGained);
        GameProcesses.Save(this.mContext,mPlayer);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public int getExpGained(){
        return expGained;
    }
}