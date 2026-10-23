package com.example.tasksem04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityDCarte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dcarte);

        EditText etTitlu = findViewById(R.id.ET_titlu);
        Spinner spGen = findViewById(R.id.SP_gen);
        RadioButton rbCartonata = findViewById(R.id.RB_cartonata);
        RatingBar rbRating = findViewById(R.id.RB_rating);
        CheckBox cbCitita = findViewById(R.id.CB_citita);
        Switch swImprumutabila = findViewById(R.id.SW_imprumutabila);
        Button btnSalveaza = findViewById(R.id.BTN_salveaza);

        String[] genuri = {"Roman", "SF", "Poezie", "Stiintific"};
        spGen.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, genuri));

        btnSalveaza.setOnClickListener(v -> {
            DCarte carte = new DCarte(
                    etTitlu.getText().toString(),
                    spGen.getSelectedItem().toString(),
                    rbCartonata.isChecked() ? "Cartonata" : "Necartonata",
                    rbRating.getRating(),
                    cbCitita.isChecked(),
                    swImprumutabila.isChecked());

            Intent rezultat = new Intent();
            rezultat.putExtra("carte", carte);
            setResult(RESULT_OK, rezultat);
            finish();
        });
    }
}