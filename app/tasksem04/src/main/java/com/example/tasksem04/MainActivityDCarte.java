package com.example.tasksem04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    private DCarte carteExistenta = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dcarte);

        final EditText etTitlu = findViewById(R.id.ET_titlu);
        final Spinner spGen = findViewById(R.id.SP_gen);
        final RadioButton rbCartonata = findViewById(R.id.RB_cartonata);
        final RadioButton rbNecartonata = findViewById(R.id.RB_necartonata);
        final RatingBar rbRating = findViewById(R.id.RB_rating);
        final CheckBox cbCitita = findViewById(R.id.CB_citita);
        final Switch swImprumutabila = findViewById(R.id.SW_imprumutabila);
        Button btnSalveaza = findViewById(R.id.BTN_salveaza);

        ArrayAdapter<CharSequence> adapterGen = ArrayAdapter.createFromResource(
                this, R.array.genuri, android.R.layout.simple_spinner_item);
        adapterGen.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spGen.setAdapter(adapterGen);

        Intent it = getIntent();
        if (it.hasExtra("carte")) {
            carteExistenta = it.getParcelableExtra("carte");

            etTitlu.setText(carteExistenta.getTitlu());
            spGen.setSelection(adapterGen.getPosition(carteExistenta.getGen()));
            if (carteExistenta.getCoperta().equals("Cartonata")) {
                rbCartonata.setChecked(true);
            } else {
                rbNecartonata.setChecked(true);
            }
            rbRating.setRating(carteExistenta.getRating());
            cbCitita.setChecked(carteExistenta.isCitita());
            swImprumutabila.setChecked(carteExistenta.isImprumutabila());
        }

        btnSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coperta;
                if (rbCartonata.isChecked()) {
                    coperta = "Cartonata";
                } else {
                    coperta = "Necartonata";
                }

                DCarte carte;
                if (carteExistenta != null) {
                    carte = carteExistenta;
                    carte.setTitlu(etTitlu.getText().toString());
                    carte.setGen(spGen.getSelectedItem().toString());
                    carte.setCoperta(coperta);
                    carte.setRating(rbRating.getRating());
                    carte.setCitita(cbCitita.isChecked());
                    carte.setImprumutabila(swImprumutabila.isChecked());
                } else {
                    carte = new DCarte(
                            etTitlu.getText().toString(),
                            spGen.getSelectedItem().toString(),
                            coperta,
                            rbRating.getRating(),
                            cbCitita.isChecked(),
                            swImprumutabila.isChecked());
                }

                Intent rezultat = new Intent();
                rezultat.putExtra("carte", carte);
                setResult(RESULT_OK, rezultat);
                finish();
            }
        });
    }
}