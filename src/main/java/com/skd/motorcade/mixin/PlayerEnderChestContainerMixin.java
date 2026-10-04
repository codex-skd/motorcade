package com.skd.motorcade.mixin;

import com.skd.motorcade.automobile.attachment.rear.BaseChestRearAttachment;
import com.skd.motorcade.util.duck.EnderChestContainerDuck;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEnderChestContainer.class)
public class PlayerEnderChestContainerMixin implements EnderChestContainerDuck {
    private @Nullable BaseChestRearAttachment motorcade$activeAttachment = null;

    @Override
    public void motorcade$setActiveAttachment(BaseChestRearAttachment attachment) {
        this.motorcade$activeAttachment = attachment;
    }

    @Inject(method = "stillValid", at = @At("HEAD"), cancellable = true)
    private void motorcade$allowPlayerUseWithAttachment(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (this.motorcade$activeAttachment != null) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "startOpen", at = @At("TAIL"))
    private void motorcade$openActiveAttachment(Player player, CallbackInfo ci) {
        if (this.motorcade$activeAttachment != null) {
            this.motorcade$activeAttachment.open(player);
        }
    }

    @Inject(method = "stopOpen", at = @At("TAIL"))
    private void motorcade$closeActiveAttachment(Player player, CallbackInfo ci) {
        if (this.motorcade$activeAttachment != null) {
            this.motorcade$activeAttachment.close(player);
        }
        this.motorcade$activeAttachment = null;
    }
}
