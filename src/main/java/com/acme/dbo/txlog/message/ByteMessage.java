package com.acme.dbo.txlog.message;

import com.acme.dbo.txlog.StateFlusher;

public class ByteMessage {
    private byte message;
    public static byte accumulator;

    public ByteMessage(byte message) {
        this.message = message;
    }

    public byte getMessage() {
        return message;
    }

    public void accumulate(StateFlusher flusher) {
        byte accumulatorOverflowRest = (byte) (message - (byte) (Byte.MAX_VALUE - accumulator));
        if (accumulatorOverflowRest > 0) {
            accumulator = Byte.MAX_VALUE;
            flusher.flushByte();
            accumulator = accumulatorOverflowRest;
        } else {
            accumulator = (byte) (accumulator + message);
        }
    }

    public static String getDecoratedAccumulatedMessage() {
        return "primitive: " + accumulator;
    }
}
