package com.skd.motorcade.block.render;

import com.skd.motorcade.block.model.SlopeUnbakedModel;
import com.skd.motorcade.util.InitlessConstants;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class SlopeModelsProvider extends BlockModelProvider {
    public SlopeModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, InitlessConstants.MOTORCADE, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (var id : SlopeUnbakedModel.DEFAULT_MODELS.keySet()) {
            this.getBuilder(id.toString()).customLoader((model, files) ->
                    new NeoForgeSlopeGeometryLoader.Builder<>(model, files, id));
        }
    }
}
