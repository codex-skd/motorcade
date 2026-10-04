package com.skd.motorcade.automobile.model;

import com.mojang.serialization.Codec;
import com.skd.motorcade.Motorcade;
import com.skd.motorcade.automobile.render.BaseModel;
import com.skd.motorcade.automobile.render.attachment.front.AutopilotFrontAttachmentModel;
import com.skd.motorcade.automobile.render.attachment.front.FrontAttachmentRenderModel;
import com.skd.motorcade.automobile.render.attachment.front.HarvesterFrontAttachmentModel;
import com.skd.motorcade.automobile.render.attachment.rear.BannerPostRearAttachmentModel;
import com.skd.motorcade.automobile.render.attachment.rear.ChestRearAttachmentModel;
import com.skd.motorcade.automobile.render.attachment.rear.GrindstoneRearAttachmentModel;
import com.skd.motorcade.automobile.render.attachment.rear.PlowRearAttachmentModel;
import com.skd.motorcade.automobile.render.attachment.rear.RearAttachmentRenderModel;
import com.skd.motorcade.automobile.render.attachment.rear.StonecutterRearAttachmentModel;
import com.skd.motorcade.automobile.render.obj.ObjModel;
import com.skd.motorcade.util.SimpleMapContentRegistry;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;

public record ModelType(ResourceLocation id,
                        ModelInstanceProvider provider
) implements SimpleMapContentRegistry.Identifiable {
    public static final SimpleMapContentRegistry<ModelType> REGISTRY = new SimpleMapContentRegistry<>();
    public static final Codec<ModelType> CODEC = REGISTRY.codec();

    public static final ModelType BASIC = REGISTRY.register(new ModelType(Motorcade.rl("basic"), BaseModel::new));
    public static final ModelType FRONT_ATTACHMENT = REGISTRY.register(new ModelType(Motorcade.rl("front_attachment"), FrontAttachmentRenderModel::new));
    public static final ModelType AUTOPILOT_FRONT_ATTACHMENT = REGISTRY.register(new ModelType(Motorcade.rl("autopilot_front_attachment"), AutopilotFrontAttachmentModel::new));
    public static final ModelType HARVESTER_FRONT_ATTACHMENT = REGISTRY.register(new ModelType(Motorcade.rl("harvester_front_attachment"), HarvesterFrontAttachmentModel::new));
    public static final ModelType REAR_ATTACHMENT = REGISTRY.register(new ModelType(Motorcade.rl("rear_attachment"), RearAttachmentRenderModel::new));
    public static final ModelType CHEST_REAR_ATTACHMENT = REGISTRY.register(new ModelType(Motorcade.rl("chest_rear_attachment"), ChestRearAttachmentModel::new));
    public static final ModelType GRINDSTONE_REAR_ATTACHMENT = REGISTRY.register(new ModelType(Motorcade.rl("grindstone_rear_attachment"), GrindstoneRearAttachmentModel::new));
    public static final ModelType STONECUTTER_REAR_ATTACHMENT = REGISTRY.register(new ModelType(Motorcade.rl("stonecutter_rear_attachment"), StonecutterRearAttachmentModel::new));
    public static final ModelType PLOW_REAR_ATTACHMENT = REGISTRY.register(new ModelType(Motorcade.rl("plow_rear_attachment"), PlowRearAttachmentModel::new));
    public static final ModelType BANNER_REAR_ATTACHMENT = REGISTRY.register(new ModelType(Motorcade.rl("banner_rear_attachment"), BannerPostRearAttachmentModel::new));
    public static final ModelType OBJ = REGISTRY.register(new ModelType(Motorcade.rl("obj"), ObjModel::new));

    @Override
    public ResourceLocation getId() {
        return id();
    }

    public interface ModelInstanceProvider {
        Model create(EntityRendererProvider.Context ctx,
                     ModelDefinition.RenderMaterial material,
                     ModelLayerLocation modelLayer,
                     Vector3f translation, Vector3f rotation, Vector3f scale);
    }
}
