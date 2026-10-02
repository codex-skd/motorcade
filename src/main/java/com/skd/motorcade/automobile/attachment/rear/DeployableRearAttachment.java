package com.skd.motorcade.automobile.attachment.rear;

import com.skd.motorcade.automobile.attachment.RearAttachmentType;
import com.skd.motorcade.entity.AutomobileEntity;

public abstract class DeployableRearAttachment extends RearAttachment {

    protected DeployableRearAttachment(RearAttachmentType<?> type, AutomobileEntity entity) {
        super(type, entity);
    }

    public abstract void deploy();
}
