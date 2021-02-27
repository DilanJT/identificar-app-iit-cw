package com.example.identificar.models;

import com.example.identificar.R;

public class Cars {

//    This is a singleton class which uses only uses a one instance of this throughout the program.

    private final String[] make;
    private final int[] id;

    static Cars cars = new Cars();

    private Cars() {
        make = new String[]{"Acura", "AlphaRomeo", "Audi", "Bentley", "BMW", "Buick",
                "Cadilac", "Chevrolet", "Dodge", "Fiat", "Genesis", "GMC", "Hundai", "Jaguar", "Jeep",
                "LandRover", "Lexus", "Mercedes", "Mercury", "Mini", "Mitsubishi", "Nissan", "Pontiac",
                "Porche", "RollsRoyce", "Subaru", "Suzuki", "Tesla", "Toyota", "Volvo"};

        id =  new int[]{R.drawable.acura, R.drawable.alpha_romeo, R.drawable.audi, R.drawable.bentley,
                R.drawable.bmw, R.drawable.buick, R.drawable.cadillac, R.drawable.chevrolet, R.drawable.dodge,
                R.drawable.fiat, R.drawable.genesis, R.drawable.gmc, R.drawable.hundai, R.drawable.jaguar,
                R.drawable.jeep, R.drawable.land_rover, R.drawable.lexus, R.drawable.mercedes, R.drawable.mercury,
                R.drawable.mini, R.drawable.mitsubishi, R.drawable.nissan, R.drawable.pontiac, R.drawable.porche,
                R.drawable.rolls_royce, R.drawable.subaru, R.drawable.suzuki, R.drawable.tesla, R.drawable.toyota,
                R.drawable.volvo};
    }

    public static Cars getInstance(){
        return cars;
    }

    public String[] getMakes() {
        return make;
    }

    public int[] getCarIds() {
        return id;
    }

    
}
