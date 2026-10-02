package com.skd.motorcade.item;

import com.skd.motorcade.automobile.AutomobileEngine;

public class AutomobileEngineItem extends AutomobileComponentItem.Dynamic<AutomobileEngine> {
    public AutomobileEngineItem(Properties settings) {
        super(settings, "engine", AutomobileEngine.REGISTRY, MotorcadeItems.COMPONENT_ENGINE, AutomobileEngine.EMPTY);
    }
}
