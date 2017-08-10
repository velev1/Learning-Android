package com.example.velev.fragmentsresponsiveui.startup.utils;

/**
 * Created by velev on 10.8.2017 г..
 */

public class OrientationModeProvider {

    private static OrientationModeProvider instance;
    private boolean isPhoneLandscape;

    private OrientationModeProvider() {

    }

    public static OrientationModeProvider getInstance() {
        if(instance == null) {
            instance = new OrientationModeProvider();
        }

        return instance;
    }

    public boolean getIsPhoneLandscape() {
        return isPhoneLandscape;
    }

    public void setIsPhoneLandscape(boolean phoneLandscape) {
        isPhoneLandscape = phoneLandscape;
    }
}
