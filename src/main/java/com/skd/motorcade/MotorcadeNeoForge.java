package com.skd.motorcade;

import com.mojang.serialization.Codec;
import com.skd.motorcade.Motorcade;
import com.skd.motorcade.network.NeoForgeNetworking;
import com.skd.motorcade.util.DefaultRegistrar;
import com.skd.motorcade.util.InitlessConstants;
import com.skd.motorcade.util.RegistryQueue;
import com.skd.motorcade.util.network.MotorcadePacketPayload;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.Set;

@Mod(InitlessConstants.MOTORCADE)
@EventBusSubscriber(modid = InitlessConstants.MOTORCADE, bus = EventBusSubscriber.Bus.MOD)
public class MotorcadeNeoForge {
    public MotorcadeNeoForge() {
        NeoForgePlatform.init();

        Motorcade.init();
    }

    @SubscribeEvent
    public static void registerNetworking(RegisterPayloadHandlersEvent evt) {
        var reg = evt.registrar(NeoForgeNetworking.PROTOCOL_VERSION);
        reg.playBidirectional(MotorcadePacketPayload.TYPE, MotorcadePacketPayload.STREAM_CODEC, NeoForgeNetworking.HANDLER);
    }

    @SubscribeEvent
    public static void registerRegistries(DataPackRegistryEvent.NewRegistry evt) {
        Motorcade.initDynamicRegistries(new Motorcade.DynamicRegistryRegistrar() {
            @Override
            public <T> void accept(ResourceKey<Registry<T>> key, Codec<T> codec, DefaultRegistrar<T> defaults) {
                evt.dataPackRegistry(key, codec, codec);
            }
        });
    }

    @SubscribeEvent
    public static void generateData(GatherDataEvent evt) {
        var regset = new RegistrySetBuilder();

        Motorcade.initDynamicRegistries(new Motorcade.DynamicRegistryRegistrar() {
            @Override
            public <T> void accept(ResourceKey<Registry<T>> key, Codec<T> codec, DefaultRegistrar<T> defaults) {
                regset.add(key, bs -> defaults.bootstrap(bs::register));
            }
        });

        evt.getGenerator().addProvider(true, (DataProvider.Factory<DataProvider>)
                output -> new DatapackBuiltinEntriesProvider(output, evt.getLookupProvider(), regset, Set.of(InitlessConstants.MOTORCADE)));
    }

    @SubscribeEvent
    @SuppressWarnings("deprecation")
    public static void registerAll(RegisterEvent evt) {
        if (evt.getRegistry() == NeoForgeRegistries.ENTITY_DATA_SERIALIZERS) {
            for (var e : NeoForgePlatform.INSTANCE.dataSerializers.entrySet()) {
                evt.register(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS.key(), e.getKey(), e::getValue);
            }
        }

        register(BuiltInRegistries.BLOCK, evt);
        register(BuiltInRegistries.BLOCK_ENTITY_TYPE, evt);
        register(BuiltInRegistries.DATA_COMPONENT_TYPE, evt);
        register(BuiltInRegistries.ITEM, evt);
        register(BuiltInRegistries.ENTITY_TYPE, evt);
        register(BuiltInRegistries.PARTICLE_TYPE, evt);
        register(BuiltInRegistries.SOUND_EVENT, evt);
        register(BuiltInRegistries.MENU, evt);
        register(BuiltInRegistries.RECIPE_TYPE, evt);
        register(BuiltInRegistries.RECIPE_SERIALIZER, evt);
        register(BuiltInRegistries.CREATIVE_MODE_TAB, evt);
    }

    public static <T> void register(Registry<T> registry, RegisterEvent evt) {
        if (registry == evt.getRegistry()) {
            for (var entry : RegistryQueue.getQueue(registry)) {
                evt.register(registry.key(), entry.rl(), entry.entry()::create);
            }
        }
    }
}
