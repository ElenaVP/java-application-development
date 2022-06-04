package com.acme.dbo.txlog.message;

public class BooleanMessage implements Message {
    private boolean message;

    public BooleanMessage(boolean message) {
        this.message = message;
    }
/*
    @Override
    public Message getMessage() {
        return this;
    }
*/
    @Override
    public String getDecoratedMessage() {
        return "primitive: " + message;
    }

    @Override
    public Message accumulate(Message message) {
        return this;
    }

    @Override
    public boolean isSame(Message message) {
        return false;
    }
}
