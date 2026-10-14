package com.skd.motorcade.util.network;

import com.skd.motorcade.Motorcade;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record MotorcadePacketPayload(ResourceLocation id, byte[] bytes) implements CustomPacketPayload {
    public static final Type<MotorcadePacketPayload> TYPE = new Type<>(Motorcade.rl("message_packet"));
    public static final StreamCodec<FriendlyByteBuf, MotorcadePacketPayload> STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC, MotorcadePacketPayload::id,
            ByteBufCodecs.BYTE_ARRAY, MotorcadePacketPayload::bytes,
            MotorcadePacketPayload::new
    );

    public FriendlyByteBuf buf() {
        return new FriendlyByteBuf(Unpooled.wrappedBuffer(bytes));
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
