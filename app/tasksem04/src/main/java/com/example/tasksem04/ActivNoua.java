package com.example.tasksem04;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivNoua extends AppCompatActivity {

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activ_noua);

        ListView lvCarti = findViewById(R.id.LV_carti);

        final ArrayList<DCarte> carti =
                (ArrayList<DCarte>) getIntent().getSerializableExtra("lista");

        final ArrayAdapter<DCarte> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, carti);
        lvCarti.setAdapter(adapter);

        // Cerinta 3: click scurt -> Toast cu obiectul
        lvCarti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                DCarte carte = carti.get(position);
                Toast.makeText(ActivNoua.this, carte.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });

        // Cerinta 4: click lung -> stergem obiectul din lista si din ListView
        lvCarti.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                carti.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(ActivNoua.this, "Carte stearsa",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}