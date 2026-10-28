package com.example.tasksem04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityt04 extends AppCompatActivity {

    private TextView tvRezultat;
    private ArrayList<DCarte> carti = new ArrayList<>();

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    DCarte carte = (DCarte) result.getData().getSerializableExtra("carte");
                    carti.add(carte);
                    tvRezultat.setText("Carti adaugate: " + carti.size()
                            + "\n\n" + carte.toString());
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

        Button btnLista = findViewById(R.id.BTN_lista);
        btnLista.setOnClickListener(v -> {
            Intent it = new Intent(this, ActivNoua.class);
            it.putExtra("lista", carti);
            startActivity(it);
        });
    }
}