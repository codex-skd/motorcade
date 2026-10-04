package com.skd.motorcade.mixin.jsonem;

import net.minecraft.client.model.geom.builders.CubeDefinition;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.UVPair;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Set;

@Mixin(CubeDefinition.class)
public interface CubeDefinitionAccess {
    @Accessor("comment")
    String motorcade$name();

    @Accessor("origin")
    Vector3f motorcade$offset();

    @Accessor("dimensions")
    Vector3f motorcade$dimensions();

    @Accessor("grow")
    CubeDeformation motorcade$dilation();

    @Accessor("mirror")
    boolean motorcade$mirror();

    @Accessor("texCoord")
    UVPair motorcade$uv();

    @Accessor("texScale")
    UVPair motorcade$uvScale();

    @Invoker("<init>")
    static CubeDefinition motorcade$create(@Nullable String name, float textureX, float textureY, float offsetX, float offsetY, float offsetZ, float sizeX, float sizeY, float sizeZ, CubeDeformation extra, boolean mirror, float textureScaleX, float textureScaleY, Set<Direction> p_273201_) {
        throw new AssertionError();
    }
}
