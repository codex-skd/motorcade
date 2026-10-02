package com.skd.motorcade.automobile.attachment;

import com.mojang.serialization.Codec;
import com.skd.motorcade.Motorcade;
import com.skd.motorcade.automobile.AutomobileComponent;
import com.skd.motorcade.automobile.DisplayStat;
import com.skd.motorcade.automobile.attachment.front.AutopilotFrontAttachment;
import com.skd.motorcade.automobile.attachment.front.CropHarvesterFrontAttachment;
import com.skd.motorcade.automobile.attachment.front.EmptyFrontAttachment;
import com.skd.motorcade.automobile.attachment.front.FrontAttachment;
import com.skd.motorcade.automobile.attachment.front.GrassCutterFrontAttachment;
import com.skd.motorcade.automobile.attachment.front.MobControllerFrontAttachment;
import com.skd.motorcade.entity.AutomobileEntity;
import com.skd.motorcade.util.SimpleMapContentRegistry;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public record FrontAttachmentType<T extends FrontAttachment>(
        ResourceLocation id, BiFunction<FrontAttachmentType<T>, AutomobileEntity, T> constructor, FrontAttachmentModel model
) implements AutomobileComponent<FrontAttachmentType<?>> {
    public static final ResourceLocation ID = Motorcade.rl("front_attachment");
    public static final SimpleMapContentRegistry<FrontAttachmentType<?>> REGISTRY = new SimpleMapContentRegistry<>();
    public static final Codec<FrontAttachmentType<?>> CODEC = REGISTRY.codec();

    public static final FrontAttachmentType<EmptyFrontAttachment> EMPTY = register(new FrontAttachmentType<>(
            Motorcade.rl("empty"), EmptyFrontAttachment::new, new FrontAttachmentModel(ResourceLocation.parse("empty"), Motorcade.rl("empty"), 1)
    ));

    public static final FrontAttachmentType<MobControllerFrontAttachment> MOB_CONTROLLER = register(new FrontAttachmentType<>(
            Motorcade.rl("mob_controller"), MobControllerFrontAttachment::new,
            new FrontAttachmentModel(Motorcade.rl("textures/entity/automobile/front_attachment/mob_controller.png"), Motorcade.rl("front_attachment/mob_controller"), 1.7f)
    ));

    public static final FrontAttachmentType<AutopilotFrontAttachment> AUTOPILOT = register(new FrontAttachmentType<>(
            Motorcade.rl("autopilot"), AutopilotFrontAttachment::new,
            new FrontAttachmentModel(Motorcade.rl("textures/entity/automobile/front_attachment/autopilot.png"), Motorcade.rl("front_attachment/autopilot"), 1.7f)
    ));

    public static final FrontAttachmentType<CropHarvesterFrontAttachment> CROP_HARVESTER = register(new FrontAttachmentType<>(
            Motorcade.rl("crop_harvester"), CropHarvesterFrontAttachment::new,
            new FrontAttachmentModel(Motorcade.rl("textures/entity/automobile/front_attachment/crop_harvester.png"), Motorcade.rl("front_attachment/harvester"), 0.83f)
    ));

    public static final FrontAttachmentType<GrassCutterFrontAttachment> GRASS_CUTTER = register(new FrontAttachmentType<>(
            Motorcade.rl("grass_cutter"), GrassCutterFrontAttachment::new,
            new FrontAttachmentModel(Motorcade.rl("textures/entity/automobile/front_attachment/grass_cutter.png"), Motorcade.rl("front_attachment/harvester"), 0.83f)
    ));

    @Override
    public boolean isEmpty() {
        return this == EMPTY;
    }

    @Override
    public ResourceLocation containerId() {
        return ID;
    }

    @Override
    public void forEachStat(Consumer<DisplayStat<FrontAttachmentType<?>>> action) {
    }

    @Override
    public ResourceLocation getId() {
        return this.id();
    }

    private static <T extends FrontAttachment> FrontAttachmentType<T> register(FrontAttachmentType<T> entry) {
        REGISTRY.register(entry);
        return entry;
    }

    public record FrontAttachmentModel(ResourceLocation texture, ResourceLocation modelId, float scale) {}
}
