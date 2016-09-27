package com.example.ossusum.wrpg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * Created by Benny on 8/11/2015.
 */
public class StatFragment extends android.support.v4.app.Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private Player player;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static StatFragment newInstance(int sectionNumber, Player mPlayer) {
        return new StatFragment(sectionNumber, mPlayer);
    }
    public StatFragment(){

    }

    @SuppressLint("ValidFragment")
    public StatFragment(int sectionNumber,Player mPlayer) {
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//        args.putString(GameProcesses.PLAYER_STRING, mPlayer.toString());
        this.setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stat, container, false);
        GameProcesses gp = new GameProcesses();
        player = gp.Load(rootView.getContext()) ;
        TextView AllocationPoints = (TextView) rootView.findViewById(R.id.PointsView);
        AllocationPoints.setText(player.getAllocationPoints() + "");
        TextView PlayerName = (TextView) rootView.findViewById(R.id.PlayerNameTextView2);
        PlayerName.setText(player.getName());
        TextView Level = (TextView) rootView.findViewById(R.id.LevelView2);
        Level.setText(player.getLevel()+"");
        TextView Strength = (TextView) rootView.findViewById(R.id.StrengthStatView2);
        Strength.setText(player.getStrength()+"");
        TextView Endurance = (TextView) rootView.findViewById(R.id.EnduranceStatView2);
        Endurance.setText(player.getEndurance()+"");
        TextView Intelligence = (TextView) rootView.findViewById(R.id.IntelligenceStatView2);
        Intelligence.setText(player.getIntelligence()+"");
        TextView Speed = (TextView) rootView.findViewById(R.id.SpeedStatView2);
        Speed.setText(player.getSpeed()+"");
        TextView Luck = (TextView) rootView.findViewById(R.id.LuckStatView2);
        Luck.setText(player.getLuck() + "");

        final int StartingAllocationPoints = player.getAllocationPoints();
        ImageButton leftStrength = (ImageButton) rootView.findViewById(R.id.leftArrowStrength);
        leftStrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //left Strength
                if(player.getAllocationPoints() != StartingAllocationPoints){
                    // do normal stuff
                }
                else{
                    //do nothing
                }
            }
        });
        ImageButton leftIntelligence = (ImageButton) rootView.findViewById(R.id.leftArrowIntelligence);
        leftIntelligence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //left Intelligence
                if(player.getAllocationPoints() != StartingAllocationPoints){
                    // do normal stuff
                }
                else{
                    //do nothing
                }
            }
        });
        ImageButton leftSpeed = (ImageButton) rootView.findViewById(R.id.leftArrowSpeed);
        leftSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.getAllocationPoints() != StartingAllocationPoints){
                    // do normal stuff
                }
                else{
                    //do nothing
                }
            }
        });
        ImageButton leftLuck = (ImageButton) rootView.findViewById(R.id.leftArrowLuck);
        leftLuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.getAllocationPoints() != StartingAllocationPoints){
                    // do normal stuff
                }
                else{
                    //do nothing
                }
            }
        });
        ImageButton leftEndurance = (ImageButton) rootView.findViewById(R.id.leftArrowEndurance);
        leftEndurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.getAllocationPoints() != StartingAllocationPoints){
                    // do normal stuff
                }
                else{
                    //do nothing
                }
            }
        });

        ImageButton rightStrength = (ImageButton) rootView.findViewById(R.id.rightArrowStrength);
        rightStrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.getAllocationPoints() != 0 && player.getAllocationPoints() > -1){
                    // do normal stuff
                }
                else{
                    //do nothing
                }
            }
        });
        ImageButton rightSpeed = (ImageButton) rootView.findViewById(R.id.rightArrowSpeed);
        rightSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.getAllocationPoints() != 0 && player.getAllocationPoints() > -1){
                    // do normal stuff
                }
                else{
                    //do nothing
                }
            }
        });
        ImageButton rightLuck = (ImageButton) rootView.findViewById(R.id.rightArrowLuck);
        rightLuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.getAllocationPoints() != 0 && player.getAllocationPoints() > -1){
                    // do normal stuff
                }
                else{
                    //do nothing
                }
            }
        });
        ImageButton rightIntelligence = (ImageButton) rootView.findViewById(R.id.rightArrowIntelligence);
        rightIntelligence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.getAllocationPoints() != 0 && player.getAllocationPoints() > -1){
                    // do normal stuff
                }
                else{
                    //do nothing
                }
            }
        });
        ImageButton rightEndurance = (ImageButton) rootView.findViewById(R.id.rightArrowEndurance);
        rightEndurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.getAllocationPoints() != 0 && player.getAllocationPoints() > -1){
                    // do normal stuff
                }
                else{
                    //do nothing
                }
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        GameProcesses.Save(getActivity().getApplicationContext(),player);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        GameProcesses.Save(getActivity().getApplicationContext(),player);
        super.onDestroy();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

}
