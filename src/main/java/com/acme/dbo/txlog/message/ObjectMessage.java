package com.acme.dbo.txlog.message;

public class ObjectMessage {
    private Object message;
    public ObjectMessage(Object message) {
        this.message = message;
    }

    public Object getMessage() {
        return message;
    }

    public String getDecoratedMessage() {
        return "reference: " + message;
    }
}
