package com.skd.motorcade.automobile.attachment.front;

import com.skd.motorcade.automobile.attachment.FrontAttachmentType;
import com.skd.motorcade.entity.AutomobileEntity;

public class EmptyFrontAttachment extends FrontAttachment {
    public EmptyFrontAttachment(FrontAttachmentType<?> type, AutomobileEntity automobile) {
        super(type, automobile);
    }
}
