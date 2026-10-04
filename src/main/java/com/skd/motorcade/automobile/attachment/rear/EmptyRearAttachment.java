package com.skd.motorcade.automobile.attachment.rear;

import com.skd.motorcade.automobile.attachment.RearAttachmentType;
import com.skd.motorcade.entity.AutomobileEntity;

public class EmptyRearAttachment extends RearAttachment {
    public EmptyRearAttachment(RearAttachmentType<?> type, AutomobileEntity entity) {
        super(type, entity);
    }

    @Override
    public void tick() {
    }
}
