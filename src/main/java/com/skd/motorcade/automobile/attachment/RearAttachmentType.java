package com.skd.motorcade.automobile.attachment;

import com.mojang.serialization.Codec;
import com.skd.motorcade.Motorcade;
import com.skd.motorcade.automobile.AutomobileComponent;
import com.skd.motorcade.automobile.DisplayStat;
import com.skd.motorcade.automobile.attachment.rear.BackhoeRearAttachment;
import com.skd.motorcade.automobile.attachment.rear.BannerPostRearAttachment;
import com.skd.motorcade.automobile.attachment.rear.BaseChestRearAttachment;
import com.skd.motorcade.automobile.attachment.rear.BlockRearAttachment;
import com.skd.motorcade.automobile.attachment.rear.EmptyRearAttachment;
import com.skd.motorcade.automobile.attachment.rear.PassengerSeatRearAttachment;
import com.skd.motorcade.automobile.attachment.rear.PaverRearAttachment;
import com.skd.motorcade.automobile.attachment.rear.RearAttachment;
import com.skd.motorcade.entity.AutomobileEntity;
import com.skd.motorcade.util.SimpleMapContentRegistry;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public record RearAttachmentType<T extends RearAttachment>(
        ResourceLocation id, BiFunction<RearAttachmentType<T>, AutomobileEntity, T> constructor, RearAttachmentModel model
) implements AutomobileComponent<RearAttachmentType<?>> {
    public static final ResourceLocation ID = Motorcade.rl("rear_attachment");
    public static final SimpleMapContentRegistry<RearAttachmentType<?>> REGISTRY = new SimpleMapContentRegistry<>();
    public static final Codec<RearAttachmentType<?>> CODEC = REGISTRY.codec();

    public static final RearAttachmentType<EmptyRearAttachment> EMPTY = register(new RearAttachmentType<>(
            Motorcade.rl("empty"), EmptyRearAttachment::new, new RearAttachmentModel(ResourceLocation.parse("empty"), Motorcade.rl("empty"), 0)
    ));

    public static final RearAttachmentType<PassengerSeatRearAttachment> PASSENGER_SEAT = register(new RearAttachmentType<>(
            Motorcade.rl("passenger_seat"), PassengerSeatRearAttachment::new,
            new RearAttachmentModel(Motorcade.rl("textures/entity/automobile/rear_attachment/passenger_seat.png"), Motorcade.rl("rear_attachment/passenger_seat"), 11)
    ));

    public static final RearAttachmentType<BlockRearAttachment> CRAFTING_TABLE = register(block("crafting_table", BlockRearAttachment::craftingTable));
    public static final RearAttachmentType<BlockRearAttachment> LOOM = register(block("loom", BlockRearAttachment::loom));
    public static final RearAttachmentType<BlockRearAttachment> CARTOGRAPHY_TABLE = register(block("cartography_table", BlockRearAttachment::cartographyTable));
    public static final RearAttachmentType<BlockRearAttachment> SMITHING_TABLE = register(block("smithing_table", BlockRearAttachment::smithingTable));
    public static final RearAttachmentType<BlockRearAttachment> GRINDSTONE = register(block("grindstone", Motorcade.rl("rear_attachment/grindstone"), BlockRearAttachment::grindstone));
    public static final RearAttachmentType<BlockRearAttachment> STONECUTTER = register(block("stonecutter", Motorcade.rl("rear_attachment/stonecutter"), BlockRearAttachment::stonecutter));
    public static final RearAttachmentType<BlockRearAttachment> AUTO_MECHANIC_TABLE = register(block("auto_mechanic_table", BlockRearAttachment::autoMechanicTable));

    public static final RearAttachmentType<BlockRearAttachment> CHEST = register(chest("chest", BaseChestRearAttachment::chest));
    public static final RearAttachmentType<BlockRearAttachment> ENDER_CHEST = register(chest("ender_chest", BaseChestRearAttachment::enderChest));
    public static final RearAttachmentType<BlockRearAttachment> SADDLED_BARREL = register(block("saddled_barrel", BaseChestRearAttachment::saddledBarrel));

    public static final RearAttachmentType<BannerPostRearAttachment> BANNER_POST = register(new RearAttachmentType<>(
            Motorcade.rl("banner_post"), BannerPostRearAttachment::new,
            new RearAttachmentModel(Motorcade.rl("textures/entity/automobile/rear_attachment/banner_post.png"), Motorcade.rl("rear_attachment/banner_post"), 10)
    ));

    public static final RearAttachmentType<BackhoeRearAttachment> BACKHOE = register(new RearAttachmentType<>(
            Motorcade.rl("backhoe"), BackhoeRearAttachment::new,
            new RearAttachmentModel(Motorcade.rl("textures/entity/automobile/rear_attachment/backhoe.png"), Motorcade.rl("rear_attachment/plow"), 11)
    ));

    public static final RearAttachmentType<PaverRearAttachment> PAVER = register(new RearAttachmentType<>(
            Motorcade.rl("paver"), PaverRearAttachment::new,
            new RearAttachmentModel(Motorcade.rl("textures/entity/automobile/rear_attachment/paver.png"), Motorcade.rl("rear_attachment/plow"), 11)
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
    public void forEachStat(Consumer<DisplayStat<RearAttachmentType<?>>> action) {
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    private static RearAttachmentType<BlockRearAttachment> chest(String name, BiFunction<RearAttachmentType<BlockRearAttachment>, AutomobileEntity, BlockRearAttachment> constructor) {
        return block(name, Motorcade.rl("rear_attachment/chest"), constructor);
    }

    private static RearAttachmentType<BlockRearAttachment> block(String name, BiFunction<RearAttachmentType<BlockRearAttachment>, AutomobileEntity, BlockRearAttachment> constructor) {
        return block(name, Motorcade.rl("rear_attachment/block"), constructor);
    }

    private static RearAttachmentType<BlockRearAttachment> block(String name, ResourceLocation model, BiFunction<RearAttachmentType<BlockRearAttachment>, AutomobileEntity, BlockRearAttachment> constructor) {
        return new RearAttachmentType<>(
                Motorcade.rl(name), constructor,
                new RearAttachmentModel(Motorcade.rl("textures/entity/automobile/rear_attachment/"+name+".png"), model, 11)
        );
    }

    private static <T extends RearAttachment> RearAttachmentType<T> register(RearAttachmentType<T> entry) {
        REGISTRY.register(entry);
        return entry;
    }

    public record RearAttachmentModel(ResourceLocation texture, ResourceLocation modelId, float pivotDistPx) {}
}
