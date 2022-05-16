package com.acme.dbo.txlog;

import static com.acme.dbo.txlog.ConsolePrinter.printToConsole;
import static com.acme.dbo.txlog.Decorator.decorateMessage;

public class Facade {
    public static int intAccumulator;
    public static String lastUsedType;
    public static String lastUsedString;
    public static int stringCounter;
    public static byte byteAccumulator;

    public static boolean checkIfTypeChanged(String currentType) {
        if  ((lastUsedType == null) || (currentType.equals(lastUsedType))) {
            return false;
        } else {
            return true;
        }
    }

    public static void flushStateIfRequired(String currentType) {
        if (checkIfTypeChanged(currentType)) {
            StateFlusher.flushState();
        }
    }

    public static void log(int message) {
        flushStateIfRequired("int");
        if (message > (Integer.MAX_VALUE - intAccumulator)) {
            StateFlusher.flushInteger(Integer.MAX_VALUE);
        }
        intAccumulator = intAccumulator + message;
        lastUsedType = "int";
    }

    public static void log(byte message) {
        flushStateIfRequired("byte");
        if (message > (byte) (Byte.MAX_VALUE - byteAccumulator)) {
            StateFlusher.flushByte(Byte.MAX_VALUE);
        }
        byteAccumulator = (byte) (byteAccumulator + message);
        lastUsedType = "byte";
    }

    public static void log(char message) {
        printToConsole(decorateMessage(message));
    }

    public static void log(String message) {
        flushStateIfRequired("String");
        boolean stringHasSameValueOrNull = (lastUsedString==null)||(message.equals(lastUsedString));
        if (!stringHasSameValueOrNull) {
            StateFlusher.flushString();
        }
        stringCounter ++;
        lastUsedType="String";
        lastUsedString = message;
    }

    public static void log(boolean message) {
        printToConsole(decorateMessage(message));
    }

    public static void log(Object message) {
        printToConsole(decorateMessage(message));
    }
}
