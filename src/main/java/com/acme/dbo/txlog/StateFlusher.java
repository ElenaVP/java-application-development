package com.acme.dbo.txlog;

import static com.acme.dbo.txlog.ConsolePrinter.printToConsole;
import static com.acme.dbo.txlog.Decorator.decorateMessage;

public class StateFlusher {


    public static void flushState() {
        if ("int".equals(Facade.lastUsedType)) {
            flushInteger();
        }
        if ("String".equals(Facade.lastUsedType)) {
            flushString();
        }
        if ("byte".equals(Facade.lastUsedType)) {
            flushByte();
        }
    }

    public static void flushInteger() {
        printToConsole(Decorator.decorateMessage(Facade.intAccumulator));
        Facade.intAccumulator = 0;
        Facade.lastUsedType = null;
    }

    public static void flushInteger(Integer numberToFlush) {
        printToConsole(Decorator.decorateMessage(numberToFlush));
        Facade.intAccumulator = Facade.intAccumulator - numberToFlush;
    }

    public static void flushByte() {
        printToConsole(decorateMessage((byte) Facade.byteAccumulator));
        Facade.byteAccumulator = 0;
        Facade.lastUsedType = null;
    }

    public static void flushByte(Byte numberToFlush) {
        printToConsole(decorateMessage((byte) numberToFlush));
        Facade.byteAccumulator = (byte) (Facade.byteAccumulator - numberToFlush);
    }


    public static void flushString() {
        if (Facade.stringCounter == 1) {
            printToConsole(Decorator.decorateMessage(Facade.lastUsedString));
        } else
        if (Facade.stringCounter != 0) {
            printToConsole(decorateMessage(Facade.lastUsedString + " (x" + Facade.stringCounter + ")"));
        }
        Facade.lastUsedString = null;
        Facade.stringCounter = 0;
        Facade.lastUsedType = null;
    }
}
