package com.acme.dbo.txlog.message;

public class CharMessage implements Message {
    private char message;

    public CharMessage(char message) {
        this.message = message;
    }
/*
    @Override
    public Message getMessage() {
        return this;
    }
*/
    public String getDecoratedMessage() {
        return "char: " + message;
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
