package com.example.tasksem04;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivNoua extends AppCompatActivity {

    private ArrayList<DCarte> carti = null;
    private int idModificat = 0;
    private CarteAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activ_noua);

        ListView lv = findViewById(R.id.LV_carti);

        Intent it = getIntent();
        carti = it.getParcelableArrayListExtra("lista");
        if (carti == null) {
            carti = new ArrayList<>();
        }

        adapter = new CarteAdapter(carti, getApplicationContext(),
                android.R.layout.simple_list_item_2);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentModifica = new Intent(getApplicationContext(),
                        MainActivityDCarte.class);
                intentModifica.putExtra("carte", carti.get(i));
                idModificat = i;
                startActivityForResult(intentModifica, 209);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,
                                           int i, long l) {
                carti.remove(i);
                adapter.notifyDataSetChanged();
                trimiteListaInapoi();
                Toast.makeText(getApplicationContext(), "Carte stearsa",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 209) {
                carti.set(idModificat, (DCarte) data.getParcelableExtra("carte"));
                adapter.notifyDataSetChanged();
                trimiteListaInapoi();
            }
        }
    }

    private void trimiteListaInapoi() {
        Intent rezultat = new Intent();
        rezultat.putParcelableArrayListExtra("lista", carti);
        setResult(RESULT_OK, rezultat);
    }

    // Adapterul personalizat, ca clasa interioara
    private class CarteAdapter extends BaseAdapter {

        private List<DCarte> carti = null;
        private Context ctx;
        private int resursaLayout;

        public CarteAdapter(List<DCarte> carti, Context ctx, int resursaLayout) {
            this.carti = carti;
            this.ctx = ctx;
            this.resursaLayout = resursaLayout;
        }

        @Override
        public int getCount() {
            return carti.size();
        }

        @Override
        public Object getItem(int i) {
            return carti.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View v = inflater.inflate(resursaLayout, viewGroup, false);

            TextView titluTV = v.findViewById(android.R.id.text1);
            TextView detaliiTV = v.findViewById(android.R.id.text2);

            DCarte carte = (DCarte) getItem(i);

            titluTV.setText(carte.getTitlu());

            String stare;
            if (carte.isCitita()) {
                stare = "Citita";
            } else {
                stare = "Necitita";
            }
            if (carte.isImprumutabila()) {
                stare = stare + ", se poate imprumuta";
            } else {
                stare = stare + ", nu se imprumuta";
            }
            detaliiTV.setText(carte.getGen() + " | " + carte.getCoperta()
                    + " | Rating: " + carte.getRating() + " | " + stare);

            return v;
        }
    }
}