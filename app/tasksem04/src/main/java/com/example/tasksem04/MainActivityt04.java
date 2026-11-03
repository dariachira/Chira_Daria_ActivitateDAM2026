package com.example.tasksem04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityt04 extends AppCompatActivity {

    private TextView tvRezultat;
    private ArrayList<DCarte> carti = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activityt04);

        tvRezultat = findViewById(R.id.TV_rezultat);

        Button btn = findViewById(R.id.BTN_adauga);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), MainActivityDCarte.class);
                startActivityForResult(it, 201);
            }
        });

        Button btnLista = findViewById(R.id.BTN_lista);
        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ActivNoua.class);
                it.putParcelableArrayListExtra("lista", carti);
                startActivityForResult(it, 202);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 201) {
                DCarte carte = data.getParcelableExtra("carte");
                carti.add(carte);
                tvRezultat.setText("Carti adaugate: " + carti.size()
                        + "\n\n" + carte.toString());
                Toast.makeText(getApplicationContext(), carte.toString(),
                        Toast.LENGTH_LONG).show();
            }
            if (requestCode == 202) {
                ArrayList<DCarte> actualizata = data.getParcelableArrayListExtra("lista");
                if (actualizata != null) {
                    carti = actualizata;
                    tvRezultat.setText("Carti in lista: " + carti.size());
                }
            }
        }
    }
}