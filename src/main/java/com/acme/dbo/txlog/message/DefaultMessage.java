package com.acme.dbo.txlog.message;

public class DefaultMessage implements Message {
    @Override
    public String getDecoratedMessage() {
        return null;
    }

    @Override
    public Message accumulate(Message message) {
        return message;
    }

    @Override
    public boolean isSame(Message message) {
        return false;
    }
}
