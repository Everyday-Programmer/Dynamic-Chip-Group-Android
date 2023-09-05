package com.example.chipgroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = findViewById(R.id.tv);
        ChipGroup chipGroup = findViewById(R.id.chipGroup);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("C");
        arrayList.add("C++");
        arrayList.add("C#");
        arrayList.add("SQL");
        arrayList.add("Java");
        arrayList.add("Python");
        arrayList.add("Flutter");
        arrayList.add("Jetpack Compose");

        Random rand = new Random();
        for (String s: arrayList) {
            Chip chip = (Chip) LayoutInflater.from(MainActivity.this).inflate(R.layout.chip_layout, null);
            chip.setText(s);
            int id = rand.nextInt();
            chip.setId(id);
            chipGroup.addView(chip);
        }

        chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                if (checkedIds.isEmpty()) {
                    textView.setText("No languages selected");
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i: checkedIds) {
                        Chip chip = findViewById(i);
                        stringBuilder.append(", ").append(chip.getText());
                    }
                    textView.setText("Selected languages: " + stringBuilder.toString().replaceFirst(",", ""));
                }
            }
        });
    }
}