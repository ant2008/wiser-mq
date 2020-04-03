package com.wiser.mq.dto;

public class WiserMqMessage {
    private String messgeTag;

    private String messageBody;

    private String messageId;

    private String messageKey;

    public String getMessgeTag() {
        return messgeTag;
    }

    public void setMessgeTag(String messgeTag) {
        this.messgeTag = messgeTag;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }
}
