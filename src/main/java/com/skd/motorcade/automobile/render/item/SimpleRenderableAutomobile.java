package com.skd.motorcade.automobile.render.item;

import com.skd.motorcade.automobile.AutomobileEngine;
import com.skd.motorcade.automobile.AutomobileFrame;
import com.skd.motorcade.automobile.AutomobileWheel;
import com.skd.motorcade.automobile.attachment.front.FrontAttachment;
import com.skd.motorcade.automobile.attachment.rear.RearAttachment;
import com.skd.motorcade.automobile.render.RenderableAutomobile;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class SimpleRenderableAutomobile implements RenderableAutomobile {
    public final AutomobileFrame frame;
    public final AutomobileEngine engine;
    public final AutomobileWheel wheel;

    public SimpleRenderableAutomobile(AutomobileFrame frame, AutomobileEngine engine, AutomobileWheel wheel) {
        this.frame = frame;
        this.engine = engine;
        this.wheel = wheel;
    }

    @Override
    public AutomobileFrame getFrame() {
        return frame;
    }

    @Override
    public AutomobileEngine getEngine() {
        return engine;
    }

    @Override
    public AutomobileWheel getWheels() {
        return wheel;
    }

    @Override
    public @Nullable RearAttachment getRearAttachment() {
        return null;
    }

    @Override
    public @Nullable FrontAttachment getFrontAttachment() {
        return null;
    }

    @Override
    public float getAutomobileYaw(float tickDelta) {
        return 0;
    }

    @Override
    public float getRearAttachmentYaw(float tickDelta) {
        return 0;
    }

    @Override
    public float getWheelAngle(float tickDelta) {
        return 0;
    }

    @Override
    public float getSteering(float tickDelta) {
        return 0;
    }

    @Override
    public float getSuspensionBounce(float tickDelta) {
        return 0;
    }

    @Override
    public boolean engineRunning() {
        return false;
    }

    @Override
    public int getBoostTimer() {
        return 0;
    }

    @Override
    public int getTurboCharge() {
        return 0;
    }

    @Override
    public long getTime() {
        return 0;
    }

    @Override
    public boolean automobileOnGround() {
        return true;
    }

    @Override
    public boolean debris() {
        return false;
    }

    @Override
    public Vector3f debrisColor() {
        return new Vector3f();
    }
}
