package com.example.talkingclock;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.DigitalClock;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class home_screen_fragment extends Fragment implements TextToSpeech.OnInitListener {

    private TextToSpeechUtil ttsUtil; // Updated to use TextToSpeechUtil

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_screen, container, false);

        // Retrieve TextToSpeechUtil instance from MainActivity
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            ttsUtil = mainActivity.getTtsUtil();
        }

        // Find AnalogClock and DigitalClock views by their ids
        AnalogClock analogClock = view.findViewById(R.id.AnalogClock);
        DigitalClock digitalClock = view.findViewById(R.id.DigitalClock);

        // Set OnClickListener for AnalogClock
        analogClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClockClick();
            }
        });

        // Set OnClickListener for DigitalClock
        digitalClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClockClick();
            }
        });

        return view;
    }

    @Override
    public void onInit(int status) {
        // No need to initialize TextToSpeech here as it's managed by TextToSpeechUtil
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // No need to shutdown TextToSpeech here as it's managed by TextToSpeechUtil
    }

    private void onClockClick() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        // Use TextToSpeechUtil for speaking
        if (ttsUtil != null) {
            ttsUtil.speak("The time is " + currentTime);
        }
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }
}
