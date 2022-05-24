package com.acme.dbo.txlog.message;

public class BooleanMessage {
    private boolean message;
    public BooleanMessage(boolean message) {
        this.message = message;
    }

    public boolean getMessage() {
        return message;
    }

    public String getDecoratedMessage() {
        return "primitive: " + message;
    }
}
