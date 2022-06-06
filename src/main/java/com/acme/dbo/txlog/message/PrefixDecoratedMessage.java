package com.acme.dbo.txlog.message;

public class PrefixDecoratedMessage {
    private String prefix;

    PrefixDecoratedMessage (String prefix) {
        this.prefix = prefix;
    }

    public String getDecoratedMessage(String message) {
        return (prefix + message);
    }
}
