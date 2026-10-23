package com.example.tasksem04;


import java.io.Serializable;

    public class DCarte implements Serializable {
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

        @Override
        public String toString() {
            return "Titlu: " + titlu + "\nGen: " + gen + "\nCoperta: " + coperta
                    + "\nRating: " + rating + "\nCitita: " + citita
                    + "\nImprumutabila: " + imprumutabila;
        }
    }


