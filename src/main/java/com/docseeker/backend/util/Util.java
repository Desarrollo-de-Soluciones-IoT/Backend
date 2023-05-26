package com.docseeker.backend.util;

public class Util {

    private Util() {
        throw new IllegalStateException("Utility class");
    }

    public static float calculateBMI(float height, float weight) {
        return (float) (weight / Math.pow(height / 100.0, 2));
    }
}
