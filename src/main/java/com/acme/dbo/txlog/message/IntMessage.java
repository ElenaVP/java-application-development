package com.acme.dbo.txlog.message;

import com.acme.dbo.txlog.StateFlusher;

public class IntMessage {
    private int message;
    public static int accumulator;

    public IntMessage(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }

    public void accumulate(StateFlusher flusher) {
        int accumulatorOverflowRest = message - (Integer.MAX_VALUE - accumulator);
        if (accumulatorOverflowRest > 0) {
            accumulator = Integer.MAX_VALUE;
            flusher.flushInteger();
            accumulator = accumulatorOverflowRest;
        } else {
            accumulator = accumulator + message;
        }
    }

    public boolean isSame() {
        return true;
    }

    public static String getDecoratedAccumulatedMessage() {
        return "primitive: " + accumulator;
    }
}
