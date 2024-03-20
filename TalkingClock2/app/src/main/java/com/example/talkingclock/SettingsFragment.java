package com.example.talkingclock;

import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    private ListView listView;
    private String[] settingsNames = {"1. Alarm sounds", "2. Icon colors", "3. Font size", "4. Voice"}; // Example settings names
    private TextToSpeechUtil ttsUtil; // TextToSpeechUtil instance

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        // Retrieve TextToSpeechUtil instance from MainActivity
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            ttsUtil = mainActivity.getTtsUtil();
        }

        // Initialize the ListView and adapter
        listView = view.findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, settingsNames);
        listView.setAdapter(adapter);

        // Find the speaking mouth ImageView and set click listener
        ImageView speakingMouthImageView = view.findViewById(R.id.speaking_mouth);
        speakingMouthImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Speak each setting name with a delay using TextToSpeechUtil
                if (ttsUtil != null) {
                    final Handler handler = new Handler();
                    for (int i = 0; i < settingsNames.length; i++) {
                        final int index = i;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ttsUtil.speak(settingsNames[index]);
                            }
                        }, i * 2000); // Delay each speech by 2 seconds (2000 milliseconds)
                    }
                }
            }

        });

        return view;
    }
}
