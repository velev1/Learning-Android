package com.example.velev.dexterlab.views.lab;

import android.content.Context;
import android.view.View;

import com.example.velev.dexterlab.R;
import com.example.velev.dexterlab.data.models.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 3.8.2017 г..
 */

public class LabViewModel implements LabContract.ViewModel{

    private List<Weapon> weapons;
    private View view;

    public LabViewModel(Context context) {
        this.weapons = new ArrayList<>();
        fillFakeData();

        this.view = new View(context);
    }

    @Override
    public List<Weapon> getAllWeapons() {
        return this.weapons;
    }

    private void fillFakeData() {
        int img = R.drawable.weapon;
        for (int i = 0; i < 26; i++) {
            Weapon weapon = new Weapon(img,
                    "Blaster" + (i + 1),
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac metus metus. Vivamus turpis odio, varius in condimentum sit amet, tempus nec turpis. Mauris ipsum diam, eleifend vitae mauris et, iaculis tempus orci. Aenean non neque sed justo euismod accumsan. Sed placerat.",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et lorem dapibus, mollis sapien nec, congue augue. In scelerisque efficitur dui, a luctus ligula suscipit sit.",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
            this.weapons.add(weapon);
        }
    }
}
