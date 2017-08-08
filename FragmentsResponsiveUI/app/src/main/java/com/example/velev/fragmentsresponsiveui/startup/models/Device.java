package com.example.velev.fragmentsresponsiveui.startup.models;

import com.example.velev.fragmentsresponsiveui.startup.enums.DeviceType;

/**
 * Created by velev on 8.8.2017 г..
 */

public class Device {
    private static Device instance;

    private DeviceType deviceType;

    private Device(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public static Device getInstance(DeviceType deviceType){
        if(instance == null) {
            instance = new Device(deviceType);
        }

        return instance;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }
}