package com.skd.motorcade;

import com.mojang.serialization.Codec;
import com.skd.motorcade.automobile.AutomobileEngine;
import com.skd.motorcade.automobile.AutomobileFrame;
import com.skd.motorcade.automobile.AutomobileWheel;
import com.skd.motorcade.block.MotorcadeBlocks;
import com.skd.motorcade.entity.MotorcadeEntities;
import com.skd.motorcade.item.MotorcadeItems;
import com.skd.motorcade.item.CreativeTabQueue;
import com.skd.motorcade.particle.MotorcadeParticles;
import com.skd.motorcade.platform.Platform;
import com.skd.motorcade.recipe.AutoMechanicTableRecipe;
import com.skd.motorcade.recipe.AutoMechanicTableRecipeSerializer;
import com.skd.motorcade.screen.AutoMechanicTableScreenHandler;
import com.skd.motorcade.screen.SingleSlotScreenHandler;
import com.skd.motorcade.sound.MotorcadeSounds;
import com.skd.motorcade.util.AUtils;
import com.skd.motorcade.util.MotorcadeClientResourceDumper;
import com.skd.motorcade.util.DefaultRegistrar;
import com.skd.motorcade.util.Eventual;
import com.skd.motorcade.util.InitlessConstants;
import com.skd.motorcade.util.RegistryQueue;
import com.skd.motorcade.util.network.CommonPackets;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Motorcade {
    public static final String MOD_ID = InitlessConstants.MOTORCADE;
    public static final Logger LOG = LogManager.getLogger("motorcade");

    public static CreativeTabQueue TAB = new CreativeTabQueue(rl("motorcade"));
    public static CreativeTabQueue PREFAB_TAB = new CreativeTabQueue(rl("motorcade_prefabs"));

    public static final TagKey<Block> SLOPES = TagKey.create(Registries.BLOCK, rl("slopes"));
    public static final TagKey<Block> STEEP_SLOPES = TagKey.create(Registries.BLOCK, rl("steep_slopes"));
    public static final TagKey<Block> NON_STEEP_SLOPES = TagKey.create(Registries.BLOCK, rl("non_steep_slopes"));
    public static final TagKey<Block> STICKY_SLOPES = TagKey.create(Registries.BLOCK, rl("sticky_slopes"));

    public static final Eventual<MenuType<AutoMechanicTableScreenHandler>> AUTO_MECHANIC_SCREEN =
            RegistryQueue.register(BuiltInRegistries.MENU, Motorcade.rl("auto_mechanic_table"), () -> Platform.get().menuType(AutoMechanicTableScreenHandler::new));
    public static final Eventual<MenuType<SingleSlotScreenHandler>> SINGLE_SLOT_SCREEN =
            RegistryQueue.register(BuiltInRegistries.MENU, Motorcade.rl("single_slot"), () -> Platform.get().menuType(SingleSlotScreenHandler::new));

    public static void init() {
        MotorcadeSounds.init();
        MotorcadeBlocks.init();
        MotorcadeItems.init();
        MotorcadeEntities.init();
        MotorcadeParticles.init();
        initOther();

        CommonPackets.init();
    }

    public static void initOther() {
        RegistryQueue.register(BuiltInRegistries.RECIPE_TYPE, AutoMechanicTableRecipe.ID, () -> AutoMechanicTableRecipe.TYPE);
        RegistryQueue.register(BuiltInRegistries.RECIPE_SERIALIZER, AutoMechanicTableRecipe.ID, () -> AutoMechanicTableRecipeSerializer.INSTANCE);
        RegistryQueue.register(BuiltInRegistries.CREATIVE_MODE_TAB, TAB.location, () -> Platform.get().creativeTab(TAB.location, AUtils::createGroupIcon, TAB));
        RegistryQueue.register(BuiltInRegistries.CREATIVE_MODE_TAB, PREFAB_TAB.location, () -> Platform.get().creativeTab(PREFAB_TAB.location, AUtils::createPrefabsIcon, PREFAB_TAB));

        Platform.get().registerDataSerializer(AutomobileFrame.ID, AutomobileFrame.SERIALIZER);
        Platform.get().registerDataSerializer(AutomobileWheel.ID, AutomobileWheel.SERIALIZER);
        Platform.get().registerDataSerializer(AutomobileEngine.ID, AutomobileEngine.SERIALIZER);
    }

    public static void initDynamicRegistries(DynamicRegistryRegistrar handler) {
        handler.accept(AutomobileFrame.REGISTRY, AutomobileFrame.DIRECT_CODEC, AutomobileFrame.BOOTSTRAP);
        handler.accept(AutomobileWheel.REGISTRY, AutomobileWheel.DIRECT_CODEC, AutomobileWheel.BOOTSTRAP);
        handler.accept(AutomobileEngine.REGISTRY, AutomobileEngine.DIRECT_CODEC, AutomobileEngine.BOOTSTRAP);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void dumpDynamicRegistries(HolderLookup.Provider registries) throws IOException {
        MotorcadeClientResourceDumper.dumpDynamicRegistry(registries, AutomobileFrame.REGISTRY, AutomobileFrame.DIRECT_CODEC);
        MotorcadeClientResourceDumper.dumpDynamicRegistry(registries, AutomobileWheel.REGISTRY, AutomobileWheel.DIRECT_CODEC);
        MotorcadeClientResourceDumper.dumpDynamicRegistry(registries, AutomobileEngine.REGISTRY, AutomobileEngine.DIRECT_CODEC);
    }

    public interface DynamicRegistryRegistrar {
        <T> void accept(ResourceKey<Registry<T>> key, Codec<T> codec, DefaultRegistrar<T> defaults);
    }
}
