package com.acme.dbo.txlog.message;

import com.acme.dbo.txlog.StateFlusher;

public class IntMessage implements Message{
    private int message;
    public static int accumulator;

    public IntMessage(int message) {
        this.message = message;
    }
/*
    @Override
    public Message getMessage() {
        return null;
    }
*/

    @Override
    public String getDecoratedMessage() {
        return "primitive: " + message;
    }

    public Message accumulate(Message currentMessage) {
        return new IntMessage(message + ((IntMessage)currentMessage).message);
    }

    public boolean isSame(Message currentMessage) {
        if (currentMessage instanceof IntMessage) {
            return true;
        } else {
            return false;
        }
    }
/*
    public static String getDecoratedAccumulatedMessage() {
        return "primitive: " + accumulator;
    }

 */
}
