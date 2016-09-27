package com.example.ossusum.wrpg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutFragment extends android.support.v4.app.Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private SensorManager mSensorManager;
        private Sensor mStepCounterSensor;
        private Sensor mStepDetectorSensor;
        private TextView mGainExp, mExpNeeded, mCurrentExp, mPlayerName;
        private ProgressBar expBar;
        private int expGained = 0;
        private Player player;


    /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static WorkoutFragment newInstance(int sectionNumber, Player mPlayer) {
            return new WorkoutFragment(sectionNumber, mPlayer);
        }
        public WorkoutFragment(){

        }
        @SuppressLint("ValidFragment")
        public WorkoutFragment(int sectionNumber, Player mPlayer) {
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            //args.putString(GameProcesses.PLAYER_STRING, mPlayer.toString());
            this.setArguments(args);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_workout, container, false);
            player = GameProcesses.Load(rootView.getContext());
            if(player == null){
                player = new Player(getArguments().getString(GameProcesses.PLAYER_STRING),1);
                //player = GameProcesses.Load(rootView.getContext());
            }
            mPlayerName = (TextView) rootView.findViewById(R.id.textView55);
            mPlayerName.setText(player.getName());
            mExpNeeded = (TextView) rootView.findViewById(R.id.expNeededView);
            mCurrentExp = (TextView) rootView.findViewById(R.id.currentExpView);
            mCurrentExp.setText(player.getExp()+"");
            mGainExp = (TextView) rootView.findViewById(R.id.expGainedView);
            mGainExp.setText("");
            mExpNeeded.setText(GameProcesses.getNeededExp(player.getLevel(),player.getExp())+"");
            expBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
            expBar.setMax(GameProcesses.getMaxExp(player.getLevel()));
            Button startButton = (Button) rootView.findViewById(R.id.Startbutton);
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
                    mStepCounterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
                    mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
                    mSensorManager.registerListener(new SensorHandler(), mStepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST);
                    mSensorManager.registerListener(new SensorHandler(), mStepDetectorSensor, SensorManager.SENSOR_DELAY_FASTEST);
                }
            });
            Button endButton = (Button) rootView.findViewById(R.id.Endbutton);
            endButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSensorManager.unregisterListener(new SensorHandler(), mStepCounterSensor);
                    mSensorManager.unregisterListener(new SensorHandler(), mStepDetectorSensor);
                    expGained = 0;
                }
            });

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
        private class SensorHandler implements SensorEventListener{

            @Override
            public void onSensorChanged(SensorEvent event) {
                Sensor sensor = event.sensor;
                float[] values = event.values;
                int value = -1;

                if (values.length > 0) {
                    value = (int) values[0];
                }

                if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
                    Toast.makeText(getActivity().getApplicationContext(),"Step Counter Detected : " + value,Toast.LENGTH_SHORT).show();
                } else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
                    // For test only. Only allowed value is 1.0 i.e. for step taken
                    Toast.makeText(getActivity().getApplicationContext(),"Step Detector Detected : " + value,Toast.LENGTH_SHORT).show();

                    expBar.setProgress(++expGained);
                    mGainExp.setText(expGained +"");
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }

    @Override
    public void onPause() {
        GameProcesses.Save(getActivity(), player);
        //TODO Check this line of code
        //Intent intent = new Intent(this.getActivity(),BackgroundService.class);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getActivity(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        //getActivity().sendBroadcast(intent);
        new GameProcesses().Notification(getActivity());
        super.onPause();
    }

    @Override
    public void onDestroy() {
        GameProcesses.Save(getActivity(),player);
        new GameProcesses().Notification(getActivity());
        super.onDestroy();

    }
}

