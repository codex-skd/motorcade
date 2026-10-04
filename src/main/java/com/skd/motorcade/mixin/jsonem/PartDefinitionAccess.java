package com.skd.motorcade.mixin.jsonem;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;
import java.util.Map;

@Mixin(PartDefinition.class)
public interface PartDefinitionAccess {
    @Accessor("cubes")
    List<CubeDefinition> motorcade$cuboids();

    @Accessor("partPose")
    PartPose motorcade$transform();

    @Accessor("children")
    Map<String, PartDefinition> motorcade$children();

    @Invoker("<init>")
    static PartDefinition motorcade$create(List<CubeDefinition> cuboids, PartPose rotation) {
        throw new AssertionError();
    }
}
