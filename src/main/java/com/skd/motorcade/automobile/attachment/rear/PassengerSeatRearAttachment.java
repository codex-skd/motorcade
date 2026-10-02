package com.skd.motorcade.automobile.attachment.rear;

import com.skd.motorcade.automobile.attachment.RearAttachmentType;
import com.skd.motorcade.entity.AutomobileEntity;

public class PassengerSeatRearAttachment extends RearAttachment {
    public PassengerSeatRearAttachment(RearAttachmentType<?> type, AutomobileEntity entity) {
        super(type, entity);
    }

    @Override
    public boolean isRideable() {
        return true;
    }

    @Override
    public double getPassengerHeightOffset() {
        return 0.69;
    }
}
