package com.example.rarct.gasprices;

public enum GasType{
        G95(15, "Gasolina 95"),
        G98(3, "Gasolina 98 "),
        GOA(4, "Gasóleo A habitual"),
        NGO(5, "Nuevo gasóleo A"),
        GLP(17, "Gases licuados del petróleo");


        private final double code;
        private final String label;

    GasType(double code, String label) {
            this.code = code;
            this.label = label;
        }

        private double code() {
            return code;
        }

        private String label() {
            return label;
        }
    }

