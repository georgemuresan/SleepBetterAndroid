package com.example.admin.sleepbetter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class QInitial extends AppCompatActivity {


    private static final String DATABASE_NAME = "user_db";
    private UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_QuesInitial);

        Button button = (Button) findViewById(R.id.submitButton);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                loopForSending();



            }

        });

    }

    private void loopForSending(){
        //     System.out.println("IS NETWORK : " + isConnected());
        if (isConnected()){
            goToMenu();
        } else {
            InternetDialog dial = new InternetDialog();
            dial.show(getFragmentManager(), "dialog");
        }
    }

    public  boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

    public static class InternetDialog extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage("You need to have internet connection to proceed.");
            builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // FIRE ZE MISSILES!
                    dialog.dismiss();


                }
            });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

    private void goToMenu() {

        double mood =0;

        RadioGroup qGroup = this.findViewById(R.id.q1Group);
        int qID = qGroup.getCheckedRadioButtonId();
        View radioButton = qGroup.findViewById(qID);
        final int howLong = qGroup.indexOfChild(radioButton) +1;
        mood += howLong;

        //RadioButton r = (RadioButton) q1Group.getChildAt(idx);
        // final String howLong = r.getText().toString();

        qGroup = this.findViewById(R.id.q2Group);
        qID = qGroup.getCheckedRadioButtonId();
        radioButton = qGroup.findViewById(qID);
        final int awake = qGroup.indexOfChild(radioButton) +1;
        mood += awake;

        qGroup = this.findViewById(R.id.q3Group);
        qID = qGroup.getCheckedRadioButtonId();
        radioButton = qGroup.findViewById(qID);
        final int earlier = qGroup.indexOfChild(radioButton) +1;
        mood += earlier;

        qGroup = this.findViewById(R.id.q4Group);
        qID = qGroup.getCheckedRadioButtonId();
        radioButton = qGroup.findViewById(qID);
        final int nightsAWeek = qGroup.indexOfChild(radioButton) +1;
        mood += nightsAWeek;

        qGroup = this.findViewById(R.id.q5Group);
        qID = qGroup.getCheckedRadioButtonId();
        radioButton = qGroup.findViewById(qID);
        final int quality = qGroup.indexOfChild(radioButton) +1;
        mood += quality;

        qGroup = this.findViewById(R.id.q6Group);
        qID = qGroup.getCheckedRadioButtonId();
        radioButton = qGroup.findViewById(qID);
        final int impactMood = qGroup.indexOfChild(radioButton) +1;
        mood += impactMood;

        qGroup = this.findViewById(R.id.q7Group);
        qID = qGroup.getCheckedRadioButtonId();
        radioButton = qGroup.findViewById(qID);
        final int impactActivities = qGroup.indexOfChild(radioButton) +1;
        mood += impactActivities;

        qGroup = this.findViewById(R.id.q8Group);
        qID = qGroup.getCheckedRadioButtonId();
        radioButton = qGroup.findViewById(qID);
        final int impactGeneral = qGroup.indexOfChild(radioButton) +1;
        mood += impactGeneral;

        qGroup = this.findViewById(R.id.q9Group);
        qID = qGroup.getCheckedRadioButtonId();
        radioButton = qGroup.findViewById(qID);
        final int problem = qGroup.indexOfChild(radioButton) +1;
        mood += problem;

        mood = mood/9;
        Intent intent = new Intent(this, MainMenu.class);

    //    getSharedPreferences("questionnaire", MODE_PRIVATE).getInt("apetite", 0);
   //     getSharedPreferences("questionnaire", MODE_PRIVATE).edit().putInt("apetite", 1).apply();


        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String currentDate = df.format(c);

        getSharedPreferences("date", MODE_PRIVATE).getString("startingDate", "");
        getSharedPreferences("date", MODE_PRIVATE).edit().putString("startingDate", currentDate).apply();


        startActivity(intent);

        final double finalMood = mood;
        new Thread(new Runnable() {
            @Override
            public void run() {

                userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
                userDatabase.daoAccess().deleteUserExperimentTable();
                userDatabase.daoAccess().deleteUserQuesionnaireTable();
                userDatabase.daoAccess().deleteUserDiaryTable();

                UserQuestionnaire user = new UserQuestionnaire();
                String username = getSharedPreferences("name", MODE_PRIVATE).getString("participantID", "nothing");
                user.setUsername(username);
                user.setDate(currentDate);
                user.setHowLong(howLong);
                user.setAwake(awake);
                user.setEarlier(earlier);
                user.setNightsAWeek(nightsAWeek);
                user.setQuality(quality);
                user.setImpactMood(impactMood);
                user.setImpactActivities(impactActivities);
                user.setImpactGeneral(impactGeneral);
                user.setProblem(problem);
                user.setMood(finalMood);

                getSharedPreferences("questionnaire", MODE_PRIVATE).edit().putInt("howLong", howLong).apply();
                getSharedPreferences("questionnaire", MODE_PRIVATE).edit().putInt("awake", awake).apply();
                getSharedPreferences("questionnaire", MODE_PRIVATE).edit().putInt("earlier", earlier).apply();
                getSharedPreferences("questionnaire", MODE_PRIVATE).edit().putInt("quality", quality).apply();
                getSharedPreferences("questionnaire", MODE_PRIVATE).edit().putInt("impactMood", impactMood).apply();
                getSharedPreferences("questionnaire", MODE_PRIVATE).edit().putInt("impactActivities", impactActivities).apply();
                getSharedPreferences("questionnaire", MODE_PRIVATE).edit().putInt("impactGeneral", impactGeneral).apply();
                getSharedPreferences("MOOD", MODE_PRIVATE).edit().putFloat("mood", (float) finalMood).apply();
                userDatabase.daoAccess().insertSingleUserQuestionnaire(user);


                Report rep = new Report(userDatabase, getApplicationContext());
                rep.save(username, true, getSharedPreferences("consent", MODE_PRIVATE).getString("consent", "nothing"));

             //   ArrayList<String> diary_comments = new ArrayList<String>();
            //    diary_comments.add("");
                String diary_comments = "$^$^$^$^$^$^$^$^$^$^$^$^$^$^$^$^";
                getSharedPreferences("diary", MODE_PRIVATE).edit().putString("diary", diary_comments).apply();
                String experiments = "No experiment for the initial day.";
                getSharedPreferences("experiments", MODE_PRIVATE).edit().putString("experiments", experiments).apply();
            }
        }).start();


        String experiment = getSharedPreferences("name", Context.MODE_PRIVATE).getString("experiment", " ");

        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        final String formattedDate2 = df2.format(c);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        HomeCollection.date_collection_arr = new ArrayList<HomeCollection>();
        HomeCollection.date_collection_arr.add(new HomeCollection(formattedDate2, "No experiment started yet", String.valueOf(getSharedPreferences("MOOD", MODE_PRIVATE).getInt("mood", 0)), ""));
        String json = gson.toJson(HomeCollection.date_collection_arr);

        editor.putString("trial", json);
        editor.commit();

    }

/*
    public void onBackPressed() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
        finish();
    }*/
}
