package com.acme.dbo.txlog.message;

public class ByteMessage {
    private byte message;

    public ByteMessage(byte message) {
        this.message = message;
    }

    public byte getMessage() {
        return message;
    }

    public  String getDecoratedMessage() {
        return "primitive: " + message;
    }
}
