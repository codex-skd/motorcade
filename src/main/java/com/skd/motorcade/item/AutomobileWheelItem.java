package com.skd.motorcade.item;

import com.skd.motorcade.automobile.AutomobileWheel;

public class AutomobileWheelItem extends AutomobileComponentItem.Dynamic<AutomobileWheel> {
    public AutomobileWheelItem(Properties settings) {
        super(settings, "wheel", AutomobileWheel.REGISTRY, MotorcadeItems.COMPONENT_WHEEL, AutomobileWheel.EMPTY);
    }
}
