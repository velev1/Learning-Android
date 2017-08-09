package com.example.velev.fragmentsresponsiveui.startup.models;

import com.example.velev.fragmentsresponsiveui.startup.enums.DeviceType;

/**
 * Created by velev on 8.8.2017 г..
 */

public class Device {
    private static Device instance;

    private DeviceType deviceType;

    private Device() {

    }

    public static Device getInstance(){
        if(instance == null) {
            instance = new Device();
        }

        return instance;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }
}