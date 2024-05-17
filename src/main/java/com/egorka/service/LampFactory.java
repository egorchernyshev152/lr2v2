package com.egorka.service;

import com.egorka.model.Lamp;

public interface LampFactory {
    Lamp createLamp(int x, int y, String color);
}

