package com.example.ossusum.wrpg;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Benny on 8/14/2015.
 */
public class Player {


    private String Name;
    private int Level;
    private int Exp;
    private int UnspentPoints;
    private int[] Stats = new int[5];
    /*  0 = Strength
        1 = Endurance
        2 = Intelligence
        3 = Speed
        4 = Luck
     */

    // might add class to this constructor later
    public Player(String name, int variable){
        if (variable == GameProcesses.NEW_PLAYER){
            Name = name;
            Level = 1;
            // this line will be replace buy getStartingStats();
            for(int i = 0; i < Stats.length; ++i){
                Stats[i] = 10;
            }
            UnspentPoints = 0;
            Exp = 0;
        }
        if(variable == GameProcesses.OLD_PLAYER){
            String[] s = name.split("\\|");
            Name = s[0];
            Level = Integer.parseInt(s[1]);
            UnspentPoints = Integer.parseInt(s[2]);
            Exp = Integer.parseInt(s[3]);
            for(int i = 0 ; i < Stats.length ; ++i){
                Stats[i] = Integer.parseInt(s[i+4]);
            }
        }
    }
    public Player(String name, int level, int exp, int points, int[] stats){
        Name = name;
        Level =level;
        Exp = exp;
        UnspentPoints = points;
        Stats = stats;
    }
    public void setPoints(int newPoints){
        UnspentPoints = newPoints;
    }
    public String toString(){
        return Name + "|" + Level + "|" + Exp + "|" + UnspentPoints + "|" + Stats[0]+ "|" + Stats[1]+ "|" + Stats[2]
                + "|" + Stats[3]+ "|" + Stats[4];
    }
    public void setName(String name) {
        Name = name;
    }
    public String getName() {return Name;}
    public int getStrength() {
        return Stats[0];
    }
    public void setStrength(int str){
        Stats[0] = str;
    }
    public int getEndurance(){
        return Stats[1];
    }
    public void setEndurance(int end){
        Stats[1] = end;
    }
    public int getIntelligence(){
        return Stats[2];
    }
    public void setIntelligence(int intel){
        Stats[2] = intel;
    }
    public int getSpeed(){
        return Stats[3];
    }
    public void setSpeed(int speed){
        Stats[3] = speed;
    }
    public int getLuck(){
        return Stats[4];
    }
    public void setLuck(int luck){
        Stats[4] = luck;
    }
    public int getLevel(){
        return Level;
    }
    public void setLevel(int level){
        Level = level;
    }
    public int getExp() {
        return Exp;
    }
    public void setExp(int exp){Exp = exp;}
    public void Levelup(Context context){
        ++Level;
        UnspentPoints += 2;
        Toast toast = Toast.makeText(context, "You Leveled Up!", Toast.LENGTH_LONG);
        toast.show();
    }
    public void addExp(int exp){
        Exp += exp;
    }

    public int getAllocationPoints() {
        return UnspentPoints;
    }
}
