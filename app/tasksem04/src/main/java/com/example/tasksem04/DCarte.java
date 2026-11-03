package com.example.tasksem04;

import android.os.Parcel;
import android.os.Parcelable;

public class DCarte implements Parcelable {
    private String titlu;
    private String gen;
    private String coperta;
    private float rating;
    private boolean citita;
    private boolean imprumutabila;

    public DCarte(String titlu, String gen, String coperta, float rating,
                  boolean citita, boolean imprumutabila) {
        this.titlu = titlu;
        this.gen = gen;
        this.coperta = coperta;
        this.rating = rating;
        this.citita = citita;
        this.imprumutabila = imprumutabila;
    }

    protected DCarte(Parcel in) {
        titlu = in.readString();
        gen = in.readString();
        coperta = in.readString();
        rating = in.readFloat();
        citita = in.readByte() != 0;
        imprumutabila = in.readByte() != 0;
    }

    public static final Creator<DCarte> CREATOR = new Creator<DCarte>() {
        @Override
        public DCarte createFromParcel(Parcel in) {
            return new DCarte(in);
        }

        @Override
        public DCarte[] newArray(int size) {
            return new DCarte[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titlu);
        dest.writeString(gen);
        dest.writeString(coperta);
        dest.writeFloat(rating);
        dest.writeByte((byte) (citita ? 1 : 0));
        dest.writeByte((byte) (imprumutabila ? 1 : 0));
    }

    public String getTitlu() { return titlu; }
    public void setTitlu(String titlu) { this.titlu = titlu; }

    public String getGen() { return gen; }
    public void setGen(String gen) { this.gen = gen; }

    public String getCoperta() { return coperta; }
    public void setCoperta(String coperta) { this.coperta = coperta; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    public boolean isCitita() { return citita; }
    public void setCitita(boolean citita) { this.citita = citita; }

    public boolean isImprumutabila() { return imprumutabila; }
    public void setImprumutabila(boolean imprumutabila) { this.imprumutabila = imprumutabila; }

    @Override
    public String toString() {
        return "Titlu: " + titlu + "\nGen: " + gen + "\nCoperta: " + coperta
                + "\nRating: " + rating + "\nCitita: " + citita
                + "\nImprumutabila: " + imprumutabila;
    }
}