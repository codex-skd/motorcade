package com.skd.motorcade.item;

import com.skd.motorcade.automobile.AutomobileFrame;

public class AutomobileFrameItem extends AutomobileComponentItem.Dynamic<AutomobileFrame> {
    public AutomobileFrameItem(Properties settings) {
        super(settings, "frame", AutomobileFrame.REGISTRY, MotorcadeItems.COMPONENT_FRAME, AutomobileFrame.EMPTY);
    }
}
