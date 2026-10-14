package com.skd.motorcade.mixin.jsonem;

import net.minecraft.client.model.geom.builders.CubeDeformation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CubeDeformation.class)
public interface CubeDeformationAccess {
    @Accessor("growX")
    float motorcade$radiusX();

    @Accessor("growY")
    float motorcade$radiusY();

    @Accessor("growZ")
    float motorcade$radiusZ();
}
