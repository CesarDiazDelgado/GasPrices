package com.example.rarct.gasprices;

public enum GasType{
    G95(15, "Gasoline 95"),
    G98(3, "Gasoline 98 "),
    GOA(4, "Diesel oil A habitual"),
    NGO(5, "New Diesel oil A"),
    GLP(17, "Liquefied petroleum gases");

    private final int code;
    private final String label;

    GasType(int code, String label) {
            this.code = code;
            this.label = label;
        }

    public int code() {
        return code;
    }

    public String label() {
        return label;
    }

}

