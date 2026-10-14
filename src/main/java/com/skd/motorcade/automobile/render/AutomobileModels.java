package com.skd.motorcade.automobile.render;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.skd.motorcade.Motorcade;
import com.skd.motorcade.automobile.model.ModelDefinition;
import com.skd.motorcade.automobile.model.ModelType;
import com.skd.motorcade.automobile.render.attachment.front.AutopilotFrontAttachmentModel;
import com.skd.motorcade.automobile.render.attachment.front.HarvesterFrontAttachmentModel;
import com.skd.motorcade.automobile.render.attachment.rear.BannerPostRearAttachmentModel;
import com.skd.motorcade.automobile.render.attachment.rear.ChestRearAttachmentModel;
import com.skd.motorcade.automobile.render.attachment.rear.GrindstoneRearAttachmentModel;
import com.skd.motorcade.automobile.render.attachment.rear.PlowRearAttachmentModel;
import com.skd.motorcade.automobile.render.attachment.rear.StonecutterRearAttachmentModel;
import com.skd.motorcade.util.MotorcadeClientResourceDumper;
import com.skd.motorcade.util.EntityRenderHelper;
import net.minecraft.ResourceLocationException;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AutomobileModels implements ResourceManagerReloadListener {
    public static final ResourceLocation RELOADER_ID = Motorcade.rl("automobile_models");
    private static final ResourceLocation EMPTY = Motorcade.rl("empty");
    public static final Gson GSON = new Gson();

    private static Model skidEffect = new EmptyModel();
    private static Model exhaustFumes = new EmptyModel();

    private static final Map<ResourceLocation, ModelDefinition> modelDefinitions = new HashMap<>();
    private static EntityRendererProvider.Context modelProvider = null;
    private static final Map<ResourceLocation, Model> models = new HashMap<>();

    public static final ModelDefinition FRAME_STANDARD = ModelDefinition.ofYaw(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT,
            new ModelLayerLocation(Motorcade.rl("automobile/frame/standard"), "main"),
            -90
    );
    public static final ModelDefinition FRAME_TRACTOR = ModelDefinition.of(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT,
            new ModelLayerLocation(Motorcade.rl("automobile/frame/tractor"), "main")
    );
    public static final ModelDefinition FRAME_SHOPPING_CART = ModelDefinition.of(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT_NO_CULL,
            new ModelLayerLocation(Motorcade.rl("automobile/frame/shopping_cart"), "main")
    );
    public static final ModelDefinition FRAME_C_ARR = ModelDefinition.ofYaw(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT_NO_CULL,
            new ModelLayerLocation(Motorcade.rl("automobile/frame/c_arr"), "main"),
            -90
    );
    public static final ModelDefinition FRAME_PINEAPPLE = ModelDefinition.ofScale(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT,
            new ModelLayerLocation(Motorcade.rl("automobile/frame/pineapple"), "main"),
            2
    );
    public static final ModelDefinition FRAME_MOTORCAR = ModelDefinition.of(
            ModelType.BASIC, ModelDefinition.RenderMaterial.TRANSLUCENT,
            new ModelLayerLocation(Motorcade.rl("automobile/frame/motorcar"), "main")
    );
    public static final ModelDefinition FRAME_RICKSHAW = ModelDefinition.of(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT_NO_CULL,
            new ModelLayerLocation(Motorcade.rl("automobile/frame/rickshaw"), "main")
    );

    public static final ModelDefinition WHEEL_STANDARD = ModelDefinition.ofYaw(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT,
            new ModelLayerLocation(Motorcade.rl("automobile/wheel/standard"), "main"),
            -90
    );
    public static final ModelDefinition WHEEL_OFF_ROAD = ModelDefinition.ofYaw(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT,
            new ModelLayerLocation(Motorcade.rl("automobile/wheel/off_road"), "main"),
            -90
    );
    public static final ModelDefinition WHEEL_STEEL = ModelDefinition.of(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT,
            new ModelLayerLocation(Motorcade.rl("automobile/wheel/steel"), "main")
    );
    public static final ModelDefinition WHEEL_TRACTOR = ModelDefinition.of(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT,
            new ModelLayerLocation(Motorcade.rl("automobile/wheel/tractor"), "main")
    );
    public static final ModelDefinition WHEEL_CARRIAGE = ModelDefinition.of(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT_NO_CULL,
            new ModelLayerLocation(Motorcade.rl("automobile/wheel/carriage"), "main")
    );

    public static final ModelDefinition ENGINE_STONE = ModelDefinition.of(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT,
            new ModelLayerLocation(Motorcade.rl("automobile/engine/stone"), "main")
    );
    public static final ModelDefinition ENGINE_IRON = ModelDefinition.of(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT,
            new ModelLayerLocation(Motorcade.rl("automobile/engine/iron"), "main")
    );
    public static final ModelDefinition ENGINE_COPPER = ModelDefinition.ofYaw(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT,
            new ModelLayerLocation(Motorcade.rl("automobile/engine/copper"), "main"),
            180
    );
    public static final ModelDefinition ENGINE_GOLD = ModelDefinition.of(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT,
            new ModelLayerLocation(Motorcade.rl("automobile/engine/gold"), "main")
    );
    public static final ModelDefinition ENGINE_DIAMOND = ModelDefinition.of(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT,
            new ModelLayerLocation(Motorcade.rl("automobile/engine/diamond"), "main")
    );
    public static final ModelDefinition ENGINE_CREATIVE = ModelDefinition.ofYaw(
            ModelType.BASIC, ModelDefinition.RenderMaterial.CUTOUT,
            new ModelLayerLocation(Motorcade.rl("automobile/engine/creative"), "main"),
            180
    );

    public static final ModelDefinition REAR_ATT_PASSENGER_SEAT = ModelDefinition.of(
            ModelType.REAR_ATTACHMENT, ModelDefinition.RenderMaterial.CUTOUT_NO_CULL,
            new ModelLayerLocation(Motorcade.rl("automobile/rear_attachment/passenger_seat"), "main")
    );
    public static final ModelDefinition REAR_ATT_BLOCK = ModelDefinition.of(
            ModelType.REAR_ATTACHMENT, ModelDefinition.RenderMaterial.CUTOUT_NO_CULL,
            new ModelLayerLocation(Motorcade.rl("automobile/rear_attachment/block"), "main")
    );
    public static final ModelDefinition REAR_ATT_GRINDSTONE = ModelDefinition.of(
            ModelType.GRINDSTONE_REAR_ATTACHMENT, ModelDefinition.RenderMaterial.CUTOUT_NO_CULL,
            GrindstoneRearAttachmentModel.MODEL_LAYER
    );
    public static final ModelDefinition REAR_ATT_STONECUTTER = ModelDefinition.of(
            ModelType.STONECUTTER_REAR_ATTACHMENT, ModelDefinition.RenderMaterial.CUTOUT_NO_CULL,
            StonecutterRearAttachmentModel.MODEL_LAYER
    );
    public static final ModelDefinition REAR_ATT_CHEST = ModelDefinition.of(
            ModelType.CHEST_REAR_ATTACHMENT, ModelDefinition.RenderMaterial.CUTOUT_NO_CULL,
            ChestRearAttachmentModel.MODEL_LAYER
    );
    public static final ModelDefinition REAR_ATT_BANNER_POST = ModelDefinition.of(
            ModelType.BANNER_REAR_ATTACHMENT, ModelDefinition.RenderMaterial.CUTOUT_NO_CULL,
            BannerPostRearAttachmentModel.MODEL_LAYER
    );
    public static final ModelDefinition REAR_ATT_PLOW = ModelDefinition.of(
            ModelType.PLOW_REAR_ATTACHMENT, ModelDefinition.RenderMaterial.CUTOUT_NO_CULL,
            PlowRearAttachmentModel.MODEL_LAYER
    );

    public static final ModelDefinition FRONT_ATT_MOB_CONTROLLER = ModelDefinition.of(
            ModelType.FRONT_ATTACHMENT, ModelDefinition.RenderMaterial.CUTOUT,
            new ModelLayerLocation(Motorcade.rl("automobile/front_attachment/mob_controller"), "main")
    );
    public static final ModelDefinition FRONT_ATT_AUTOPILOT = ModelDefinition.of(
            ModelType.AUTOPILOT_FRONT_ATTACHMENT, ModelDefinition.RenderMaterial.CUTOUT,
            AutopilotFrontAttachmentModel.MODEL_LAYER
    );
    public static final ModelDefinition FRONT_ATT_HARVESTER = ModelDefinition.of(
            ModelType.HARVESTER_FRONT_ATTACHMENT, ModelDefinition.RenderMaterial.CUTOUT,
            HarvesterFrontAttachmentModel.MODEL_LAYER
    );

    public static Model getSkidEffectModel() {
        return skidEffect;
    }

    public static Model getExhaustFumesModel() {
        return exhaustFumes;
    }
    
    public static void register(ResourceLocation location, ModelDefinition model) {
        modelDefinitions.put(location, model);
    }

    public static void init() {
        EntityRenderHelper.registerContextListener(ctx -> {
            models.clear();
            modelProvider = ctx;

            skidEffect = new SkidEffectModel(ctx);
            exhaustFumes = new ExhaustFumesModel(ctx);
        });

        registerDefaults();
    }

    public static void registerDefaults() {
        register(Motorcade.rl("frame/standard"), FRAME_STANDARD);
        register(Motorcade.rl("frame/tractor"), FRAME_TRACTOR);
        register(Motorcade.rl("frame/shopping_cart"), FRAME_SHOPPING_CART);
        register(Motorcade.rl("frame/c_arr"), FRAME_C_ARR);
        register(Motorcade.rl("frame/pineapple"), FRAME_PINEAPPLE);
        register(Motorcade.rl("frame/motorcar"), FRAME_MOTORCAR);
        register(Motorcade.rl("frame/rickshaw"), FRAME_RICKSHAW);

        register(Motorcade.rl("wheel/standard"), WHEEL_STANDARD);
        register(Motorcade.rl("wheel/off_road"), WHEEL_OFF_ROAD);
        register(Motorcade.rl("wheel/steel"), WHEEL_STEEL);
        register(Motorcade.rl("wheel/tractor"), WHEEL_TRACTOR);
        register(Motorcade.rl("wheel/carriage"), WHEEL_CARRIAGE);

        register(Motorcade.rl("engine/stone"), ENGINE_STONE);
        register(Motorcade.rl("engine/iron"), ENGINE_IRON);
        register(Motorcade.rl("engine/copper"), ENGINE_COPPER);
        register(Motorcade.rl("engine/gold"), ENGINE_GOLD);
        register(Motorcade.rl("engine/diamond"), ENGINE_DIAMOND);
        register(Motorcade.rl("engine/creative"), ENGINE_CREATIVE);

        register(Motorcade.rl("rear_attachment/passenger_seat"), REAR_ATT_PASSENGER_SEAT);
        register(Motorcade.rl("rear_attachment/block"), REAR_ATT_BLOCK);
        register(Motorcade.rl("rear_attachment/grindstone"), REAR_ATT_GRINDSTONE);
        register(Motorcade.rl("rear_attachment/stonecutter"), REAR_ATT_STONECUTTER);
        register(Motorcade.rl("rear_attachment/chest"), REAR_ATT_CHEST);
        register(Motorcade.rl("rear_attachment/banner_post"), REAR_ATT_BANNER_POST);
        register(Motorcade.rl("rear_attachment/plow"), REAR_ATT_PLOW);

        register(Motorcade.rl("front_attachment/mob_controller"), FRONT_ATT_MOB_CONTROLLER);
        register(Motorcade.rl("front_attachment/autopilot"), FRONT_ATT_AUTOPILOT);
        register(Motorcade.rl("front_attachment/harvester"), FRONT_ATT_HARVESTER);
    }

    public static Model getModelOrNull(ResourceLocation location) {
        if (modelProvider == null) {
            return null;
        }

        var def =  modelDefinitions.get(location);
        if (def == null) {
            return null;
        }

        return models.computeIfAbsent(location, l -> def.createModel(modelProvider));
    }

    public static Model getModel(ResourceLocation location) {
        var result = getModelOrNull(location);
        if (result == null) {
            return getEmpty();
        }
        return result;
    }

    public static Model getEmpty() {
        return getModelOrNull(EMPTY);
    }

    public static Optional<ModelDefinition> readJson(InputStream data) {
        JsonElement json = GSON.fromJson(GSON.newJsonReader(new InputStreamReader(data)), JsonObject.class);

        return ModelDefinition.CODEC.decode(JsonOps.INSTANCE, json).result().map(Pair::getFirst);
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        modelDefinitions.clear();
        AutomobileModels.registerDefaults();

        FileToIdConverter.json("automobile_models").listMatchingResources(resourceManager).forEach((rl, res) -> {
            var ns = rl.getNamespace();
            var pt = rl.getPath().replaceAll("automobile_models/", "").replaceAll(".json", "");

            try (var in = res.open()) {
                var data = AutomobileModels.readJson(in);
                data.ifPresent(model -> AutomobileModels.register(ResourceLocation.fromNamespaceAndPath(ns, pt), model));
            } catch (IOException | ResourceLocationException e) {
                Motorcade.LOG.error(e);
            }
        });
    }

    public static void dump() throws IOException {
        var dumpRoot = "assets";
        var subFolder = "automobile_models";
        var codec = ModelDefinition.CODEC;

        for (var e : modelDefinitions.entrySet()) {
            MotorcadeClientResourceDumper.dumpJsonResource(dumpRoot, subFolder, e.getKey(), e.getValue(), codec);
        }
    }
}
