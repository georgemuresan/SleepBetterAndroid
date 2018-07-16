package com.example.admin.sleepbetter;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Questionnaire extends Fragment {

    private SeekBarWithIntervals timesPerNightBar = null;
    private SeekBarWithIntervals nightTerrorsBar = null;
    private SeekBarWithIntervals fallAsleepBar = null;
    private SeekBarWithIntervals wakeUpBar = null;
    private SeekBarWithIntervals freshBar = null;
    private SeekBarWithIntervals happyBar = null;
    private SeekBarWithIntervals sadBar = null;
    private SeekBarWithIntervals sleepyBar = null;
    private SeekBarWithIntervals tiredBar = null;
    private SeekBarWithIntervals stressedBar = null;
    private SeekBarWithIntervals apetiteBar = null;
    private SeekBarWithIntervals concentrateBar = null;
    private SeekBarWithIntervals coordinateBar = null;
    private SeekBarWithIntervals irritableBar = null;

    View questionnaireView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        questionnaireView = inflater.inflate(R.layout.questionnaire, container, false);

        List<String> seekbarIntervals = getIntervals("upToFour");
        getSeekbarWithIntervals("times").setIntervals(seekbarIntervals);

        List<String> listOne = getIntervals("upToFive");
        getSeekbarWithIntervals("nightTerrors").setIntervals(listOne);

        getSeekbarWithIntervals("fallAsleep").setIntervals(listOne);

        getSeekbarWithIntervals("wakeUp").setIntervals(listOne);

        getSeekbarWithIntervals("fresh").setIntervals(listOne);

        getSeekbarWithIntervals("happy").setIntervals(listOne);

        getSeekbarWithIntervals("sad").setIntervals(listOne);

        getSeekbarWithIntervals("sleepy").setIntervals(listOne);

        getSeekbarWithIntervals("tired").setIntervals(listOne);

        getSeekbarWithIntervals("stressed").setIntervals(listOne);

        getSeekbarWithIntervals("irritable").setIntervals(listOne);

        getSeekbarWithIntervals("concentrate").setIntervals(listOne);

        getSeekbarWithIntervals("coordinate").setIntervals(listOne);

        getSeekbarWithIntervals("apetite").setIntervals(listOne);

        return questionnaireView;
    }

    private List<String> getIntervals(String command) {

        if (command.equals("upToFour")) {
            return new ArrayList<String>() {{
                add("0");
                add("1");
                add("2");
                add("3");
                add("4/4+");
            }};
        } else  if (command.equals("upToFive")) {
            return new ArrayList<String>() {{
                add("1");
                add("2");
                add("3");
                add("4");
                add("5");
            }};
        }
        return null;
    }

    private SeekBarWithIntervals getSeekbarWithIntervals(String name) {

        if (name.equals("times")) {
            if (timesPerNightBar == null) {
                timesPerNightBar = (SeekBarWithIntervals) questionnaireView.findViewById(R.id.timesPerNightBar);
            }

            return timesPerNightBar;
        } else  if (name.equals("nightTerrors")) {
            if (nightTerrorsBar == null) {
                nightTerrorsBar = (SeekBarWithIntervals) questionnaireView.findViewById(R.id.nightTerrorsBar);
            }

            return nightTerrorsBar;
        } else  if (name.equals("fallAsleep")) {
            if (fallAsleepBar == null) {
                fallAsleepBar = (SeekBarWithIntervals) questionnaireView.findViewById(R.id.fallAsleepBar);
            }

            return fallAsleepBar;
        } else  if (name.equals("wakeUp")) {
            if (wakeUpBar == null) {
                wakeUpBar = (SeekBarWithIntervals) questionnaireView.findViewById(R.id.easyWakeUpBar);
            }

            return wakeUpBar;
        } else  if (name.equals("fresh")) {
            if (freshBar == null) {
                freshBar = (SeekBarWithIntervals) questionnaireView.findViewById(R.id.freshBar);
            }

            return freshBar;
        } else  if (name.equals("happy")) {
            if (happyBar == null) {
                happyBar = (SeekBarWithIntervals) questionnaireView.findViewById(R.id.happyBar);
            }

            return happyBar;
        } else  if (name.equals("sad")) {
            if (sadBar == null) {
                sadBar = (SeekBarWithIntervals) questionnaireView.findViewById(R.id.sadBar);
            }

            return sadBar;
        } else  if (name.equals("sleepy")) {
            if (sleepyBar == null) {
                sleepyBar = (SeekBarWithIntervals) questionnaireView.findViewById(R.id.sleepyBar);
            }

            return sleepyBar;
        } else  if (name.equals("tired")) {
            if (tiredBar == null) {
                tiredBar = (SeekBarWithIntervals) questionnaireView.findViewById(R.id.tiredBar);
            }

            return tiredBar;
        } else  if (name.equals("stressed")) {
            if (stressedBar == null) {
                stressedBar = (SeekBarWithIntervals) questionnaireView.findViewById(R.id.stressedBar);
            }

            return stressedBar;
        } else  if (name.equals("irritable")) {
            if (irritableBar == null) {
                irritableBar = (SeekBarWithIntervals) questionnaireView.findViewById(R.id.irritableBar);
            }

            return irritableBar;
        } else  if (name.equals("concentrate")) {
            if (concentrateBar == null) {
                concentrateBar = (SeekBarWithIntervals) questionnaireView.findViewById(R.id.concentrateBar);
            }

            return concentrateBar;
        } else  if (name.equals("coordinate")) {
            if (coordinateBar == null) {
                coordinateBar = (SeekBarWithIntervals) questionnaireView.findViewById(R.id.coordinateBar);
            }

            return coordinateBar;
        } else  if (name.equals("apetite")) {
            if (apetiteBar == null) {
                apetiteBar = (SeekBarWithIntervals) questionnaireView.findViewById(R.id.apetiteBar);
            }

            return apetiteBar;
        }
        return null;
    }
}