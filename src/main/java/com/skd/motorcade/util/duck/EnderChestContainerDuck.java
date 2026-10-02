package com.skd.motorcade.util.duck;

import com.skd.motorcade.automobile.attachment.rear.BaseChestRearAttachment;
import net.minecraft.world.inventory.PlayerEnderChestContainer;

public interface EnderChestContainerDuck {
    void motorcade$setActiveAttachment(BaseChestRearAttachment attachment);

    static EnderChestContainerDuck of(PlayerEnderChestContainer inv) {
        return (EnderChestContainerDuck) inv;
    }
}
