package com.skd.motorcade.mixin.jsonem;

import net.minecraft.client.model.geom.builders.MaterialDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MaterialDefinition.class)
public interface MaterialDefinitionAccess {
    @Accessor("xTexSize")
    int motorcade$width();

    @Accessor("yTexSize")
    int motorcade$height();
}
