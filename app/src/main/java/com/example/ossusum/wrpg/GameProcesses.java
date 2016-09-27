package com.example.ossusum.wrpg;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;;

/**
 * Created by Benny on 8/14/2015.
 */
public class GameProcesses {

    public final static String FILENAME = "PlayerInfo";
    public final static String PLAYER_STRING = "Player";
    public final static int NEW_PLAYER = 0;
    public final static int OLD_PLAYER = 1;
    //public static File SAVE_FILE = new File(FILENAME);

    public GameProcesses(){

    }
    public static int getNeededExp(int currentLevel, int currentExp){
        double base = 2.8;// base
        double answer = 1;
        // holds answer starts at 1 to for multiplication
        int stop = ++currentLevel;
        for(int i = 0; i >= stop; ++i){
            answer = (answer * base);
        }
        return (int)(Math.pow(currentLevel++,2.8117))-currentExp;
        //return (int) answer;
    }
    public boolean checkForFile(Context context){
        try{
            return new Scanner(new File(FILENAME)).hasNext();
        }catch (Exception e){
            return false;
        }
    }
    public static int getMaxExp(int currentLevel){
        return (int)(Math.pow(currentLevel++,2.8117));
    }
    public static Player Load(Context context){
            final Player testPlayer;
            try {
                File someFile = new File(FILENAME);
                FileInputStream fis = context.openFileInput(someFile.getPath());
                BufferedInputStream buf = new BufferedInputStream(fis);
                byte[] contents = new byte[1024];
                int bytesRead = 0 ;
                String strFileContents = "" ;
                while( (bytesRead = buf.read(contents)) != -1){
                    strFileContents += new String(contents, 0, bytesRead);
                }
                buf.close();
                fis.close();
                return new Player(strFileContents, GameProcesses.OLD_PLAYER);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context,"Could not fine file",Toast.LENGTH_LONG).show();

                testPlayer = new Player("Test3",GameProcesses.NEW_PLAYER);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                context);

                // set title
                alertDialog.setTitle("Welcome to WRPG");

                // set dialog message
                final EditText textEntryView = new EditText(context.getApplicationContext());
                final Context mContext = context.getApplicationContext();
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
                textEntryView.setLayoutParams(lp);
                alertDialog
                    .setMessage("What would you like \nyour name to be?")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            testPlayer.setName(textEntryView.getText().toString());
                            GameProcesses.Save(mContext.getApplicationContext(), testPlayer);
                        }
                    })
                .setView(textEntryView)
                .show();
//                return new Player(textEntryView.getText().toString(),0);
            }

        //}
        return testPlayer;
    }
    public static void Save(Context context, Player player) {
        try {
            //TODO File name changed here
            File SomeFile = new File(FILENAME);
            FileOutputStream fos = context.openFileOutput(SomeFile.getPath(), Context.MODE_PRIVATE);
            fos.write(player.toString().getBytes());
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void Notification(Context context){

        final NotificationManager mNotifyManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.mipmap.ic_launcher);
        // When the loop is finished, updates the notification
        mBuilder.setContentText("Download complete")
                .setProgress(0, 0,false);
        mNotifyManager.notify(1, mBuilder.build());
    }
}
// Starts the thread by calling the run() method in its Runnable


//    public static void PromptForName(final Context context,Player mPlayer){
//        //Player[] Player = new Player[1];
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
//                context);
//
//        // set title
//        alertDialog.setTitle("Welcome to WRPG");
//
//        // set dialog message
//        final EditText textEntryView = new EditText(context.getApplicationContext());
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        textEntryView.setLayoutParams(lp);
//        alertDialog
//                .setMessage("What would you like \nyour name to be?")
//                .setCancelable(false)
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                        mPlayer.setName(textEntryView.getText().toString());
//                        GameProcesses.Save(context.getApplicationContext(), mPlayer);
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//
//                    }
//                })
//                .setView(textEntryView)
//                .show();
//                return new Player(textEntryView.getText().toString(),0);
//   }

