package com.example.talkingclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.talkingclock.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private TextToSpeechUtil ttsUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize TextToSpeechUtil
        ttsUtil = new TextToSpeechUtil(this);

        replaceFragment(new home_screen_fragment());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navHomePage) {
                replaceFragment(new home_screen_fragment());
            } else if (item.getItemId() == R.id.navSetting) {
                replaceFragment(new SettingsFragment());
            } else if (item.getItemId() == R.id.navAlarmList) {
                replaceFragment(new Alarm_list_fragment());
            }
            return true;
        });

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Add_Alarm.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Shutdown TextToSpeechUtil to release resources
        ttsUtil.shutdown();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public TextToSpeechUtil getTtsUtil() {
        return ttsUtil;
    }
}
