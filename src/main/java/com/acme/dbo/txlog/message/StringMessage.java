package com.acme.dbo.txlog.message;

public class StringMessage {
    private String message;
    public static int stringCounter;
    public static String lastUsedString;

    public StringMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void accumulate() {
        lastUsedString = message;
        stringCounter ++;
    }

    public boolean isSameOrNull() {
        return (lastUsedString==null)||(lastUsedString.equals(message));
    }

    public static String getDecoratedAccumulatedMessage() {
        if (stringCounter == 1) {
            return "string: " + lastUsedString;
        } else
        if (stringCounter != 0) {
            return lastUsedString + " (x" + stringCounter + ")";
        } else {
            return lastUsedString;
        }
    }
}
