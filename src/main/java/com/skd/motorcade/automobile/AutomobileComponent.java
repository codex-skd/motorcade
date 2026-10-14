package com.skd.motorcade.automobile;

import com.skd.motorcade.util.SimpleMapContentRegistry;

public interface AutomobileComponent<T extends AutomobileComponent<T>> extends SimpleMapContentRegistry.Identifiable, StatContainer<T> {
    boolean isEmpty();
}
