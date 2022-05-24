package com.acme.dbo.txlog.message;

public class CharMessage {
    public char message;

    public CharMessage(char message) {
        this.message = message;
    }

    public String getDecoratedMessage() {
        return "char: " + message;
    }


}
