package com.example.ossusum.wrpg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;


public class ProfileFragment extends android.support.v4.app.Fragment {
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
    public static ProfileFragment newInstance(int sectionNumber, Player mPlayer) {
        return new ProfileFragment(sectionNumber, mPlayer);
    }

    public ProfileFragment() {

    }
    @SuppressLint("ValidFragment")
    public ProfileFragment(int sectionNumber, Player mPlayer) {
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        //player = new GameProcesses().Load(new MainActivity());
        //args.putString(GameProcesses.PLAYER_STRING, player.toString());
        this.setArguments(args);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        player = new GameProcesses().Load(rootView.getContext());
        //player = new Player(getArguments().getString(GameProcesses.PLAYER_STRING),1);
        TextView PlayerName = (TextView) rootView.findViewById(R.id.PlayerNameTextView);
        PlayerName.setText(player.getName());
        TextView Level = (TextView) rootView.findViewById(R.id.LevelView);
        Level.setText(player.getLevel()+"");
        TextView Strength = (TextView) rootView.findViewById(R.id.StrengthStatView);
        Strength.setText(player.getStrength()+"");
        TextView Endurance = (TextView) rootView.findViewById(R.id.EnduranceStatView);
        Endurance.setText(player.getEndurance()+"");
        TextView Intelligence = (TextView) rootView.findViewById(R.id.IntelligenceStatView);
        Intelligence.setText(player.getIntelligence()+"");
        TextView Speed = (TextView) rootView.findViewById(R.id.SpeedStatView);
        Speed.setText(player.getSpeed()+"");
        TextView Luck = (TextView) rootView.findViewById(R.id.LuckStatView);
        Luck.setText(player.getLuck()+"");
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
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
}


