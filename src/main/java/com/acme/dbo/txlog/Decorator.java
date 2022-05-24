package com.acme.dbo.txlog;

import com.acme.dbo.txlog.message.CharMessage;

public class Decorator {
    public static final String PRIMITIVE_PREFIX = "primitive";
    public static final String STRING_PREFIX = "string";


    public static String decorateMessage(String message) {
        return (STRING_PREFIX + ": " + message);
    }

}
