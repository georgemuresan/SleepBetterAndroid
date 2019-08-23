package com.uos.admin.sleepbetter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FirstPage extends AppCompatActivity {

    private EditText nameBox = null;
    private EditText participantID = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_welcome);

        nameBox = (EditText) findViewById(R.id.yourName);
        System.out.println("INPUT IS :" + nameBox.getText().toString());
        participantID = (EditText) findViewById(R.id.participantName);
/*
        getApplicationContext().getSharedPreferences("MOOD", MODE_PRIVATE).edit().clear().commit();
        getApplicationContext().getSharedPreferences("name", MODE_PRIVATE).edit().clear().commit();
        getApplicationContext().getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE).edit().clear().commit();
        getApplicationContext().getSharedPreferences("questionnaire", MODE_PRIVATE).edit().clear().commit();
        getApplicationContext().getSharedPreferences("consent", MODE_PRIVATE).edit().clear().commit();
        getApplicationContext().getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().clear().commit();
        getApplicationContext().getSharedPreferences("bmhappy", MODE_PRIVATE).edit().clear().commit();
        getApplicationContext().getSharedPreferences("bmok", MODE_PRIVATE).edit().clear().commit();
        getApplicationContext().getSharedPreferences("bmnotok", MODE_PRIVATE).edit().clear().commit();
        getApplicationContext().getSharedPreferences("bmbad", MODE_PRIVATE).edit().clear().commit();
*/
        getSharedPreferences("name", MODE_PRIVATE).getString("username", "nothing");
        getSharedPreferences("name", MODE_PRIVATE).getString("participantID", "nothing");

        final CheckBox consent = (CheckBox) findViewById(R.id.consentCheckbox);


        String completed = getSharedPreferences("consent", MODE_PRIVATE).getString("consent", "nothing");

        if (completed.equals("Yes") || completed.equals("No")){
            consent.setChecked(true);
        } else {
            consent.setChecked(false);
        }
        consent.setClickable(false);
        TextView cform = (TextView) findViewById(R.id.consentForm);
        cform.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), FirstPageConsent.class);

                String name = nameBox.getText().toString();
                String participant = participantID.getText().toString();

                if (!name.equals("")){
                    getSharedPreferences("name", MODE_PRIVATE).edit().putString("username", name).apply();
                }
                if (!participant.equals("")){
                    getSharedPreferences("name", MODE_PRIVATE).edit().putString("participantID", participant).apply();
                }

                startActivity(intent);


            }
        });


        String name = getSharedPreferences("name", MODE_PRIVATE).getString("username", "nothing");
        String participant = getSharedPreferences("name", MODE_PRIVATE).getString("participantID", "nothing");

        if (!name.equals("nothing")){
            nameBox.setText(name);
        }
        if (!participant.equals("nothing")){
            participantID.setText(participant);
        }

        Button button = (Button) findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (consent.isChecked() ) {
                    goToSecondActivity();
                } else {
                Toast.makeText(getApplicationContext(), "You need read the consent page.", Toast.LENGTH_SHORT).show();
                }
            }

        });





        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (!isFirstRun && consent.isChecked() && !nameBox.getText().toString().equals("") && !participantID.getText().toString().equals("")) {
            //show start activity


            String participantID = getSharedPreferences("name", MODE_PRIVATE).getString("participantID", "nothing");

            if (participantID.contains("B") || participantID.contains("b")){
                startActivity(new Intent(this, B_MainMenu.class));
            } else {
                startActivity(new Intent(this, MainMenu.class));
            }


        }



    }




    private void goToSecondActivity() {



        if (nameBox.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please input your name", Toast.LENGTH_SHORT).show();

        } else if (participantID.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please input your participant ID number", Toast.LENGTH_SHORT).show();

        } else {

            if (participantID.getText().toString().contains("B") || participantID.getText().toString().contains("b") || participantID.getText().toString().contains("A") || participantID.getText().toString().contains("a")) {
                Intent intent = new Intent(this, Demographics.class);

                String name = nameBox.getText().toString();
                String participant = participantID.getText().toString();

                getSharedPreferences("name", MODE_PRIVATE).edit().putString("username", name).apply();
                getSharedPreferences("name", MODE_PRIVATE).edit().putString("participantID", participant).apply();
                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply();

                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Please input the CORRECT participant ID number (e.g. A1/B2 etc)", Toast.LENGTH_LONG).show();

            }



        }

    }

}