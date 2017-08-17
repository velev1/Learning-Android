package com.example.velev.loaderdemo.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velev on 17.8.2017 г..
 */

public class FakeData {

    private List<String> data;

    public FakeData() {
        this.data = new ArrayList<>();
        fillData();

    }

    public List<String> getData() {
        return data;
    }

    private void fillData () {
        for (int i = 0; i < 50; i++) {
            this.data.add("some data" + i);
        }
    }
}
