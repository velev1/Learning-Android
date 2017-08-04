package com.example.velev.dexterlab.views.lab;

import com.example.velev.dexterlab.data.models.Weapon;

import java.util.List;

/**
 * Created by velev on 4.8.2017 г..
 */

public interface LabContract {

    interface View {

    }

    interface ViewModel {
        List<Weapon> getAllWeapons();
    }
}
