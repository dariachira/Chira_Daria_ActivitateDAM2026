package com.example.tasksem04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CarteAdapter extends BaseAdapter {

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