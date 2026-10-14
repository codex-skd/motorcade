package com.skd.motorcade.mixin.jsonem;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MaterialDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LayerDefinition.class)
public interface LayerDefinitionAccess {
    @Accessor("mesh")
    MeshDefinition motorcade$root();

    @Accessor("material")
    MaterialDefinition motorcade$texture();

    @Invoker("<init>")
    static LayerDefinition motorcade$create(MeshDefinition data, MaterialDefinition dimensions) {
        throw new AssertionError();
    }
}
