package com.testapp.helper;

import java.util.Random;

public abstract class BaseClass {

    public static String getRandomString(String value) {
        Random random = new Random();
        StringBuilder randomizedAlias = new StringBuilder(value.length());

        for (int i = 0; i < value.length(); i++) {
            int randomIndex = random.nextInt(value.length());
            randomizedAlias.append(value.charAt(randomIndex));
        }

        return randomizedAlias.toString();
    }

    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
