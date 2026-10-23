package com.example.tasksem04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityt04 extends AppCompatActivity {

    private TextView tvRezultat;

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    DCarte carte = (DCarte) result.getData().getSerializableExtra("carte");
                    tvRezultat.setText(carte.toString());
                    Toast.makeText(this, carte.toString(), Toast.LENGTH_LONG).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activityt04);

        tvRezultat = findViewById(R.id.TV_rezultat);
        Button btn = findViewById(R.id.BTN_adauga);
        btn.setOnClickListener(v ->
                launcher.launch(new Intent(this, MainActivityDCarte.class)));
    }
}