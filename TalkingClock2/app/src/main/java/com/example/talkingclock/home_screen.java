package com.example.talkingclock;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.speech.tts.TextToSpeech;
import android.widget.AnalogClock;
import android.widget.DigitalClock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class home_screen extends Fragment implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_screen, container, false);

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(getActivity(), this);

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
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language is not supported");
            }
        } else {
            Log.e("TTS", "Initialization failed");
        }
    }

    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    private void speak(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    private void onClockClick() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        speak("The time is " + currentTime);
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }
}
