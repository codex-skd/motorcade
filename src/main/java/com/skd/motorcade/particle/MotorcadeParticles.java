package com.skd.motorcade.particle;

import com.skd.motorcade.Motorcade;
import com.skd.motorcade.platform.Platform;
import com.skd.motorcade.util.Eventual;
import com.skd.motorcade.util.RegistryQueue;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class MotorcadeParticles {
    public static final Eventual<SimpleParticleType> DRIFT_SMOKE = RegistryQueue.register(BuiltInRegistries.PARTICLE_TYPE, Motorcade.rl("drift_smoke"), () -> Platform.get().simpleParticleType(true));

    public static void init() {
    }
}
