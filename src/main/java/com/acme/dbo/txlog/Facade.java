package com.acme.dbo.txlog;

import java.util.Objects;

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

    public static void flushState() {
        if ("int".equals(lastUsedType)) {
            flushInteger();
        }
        if ("String".equals(lastUsedType)) {
            flushString();
        }
        if ("byte".equals(lastUsedType)) {
            flushByte();
        }
    }

    public static void flushInteger() {
        printToConsole(decorateMessage(intAccumulator));
        intAccumulator = 0;
        lastUsedType = null;
    }

    public static void flushByte() {
        printToConsole(decorateMessage((byte)byteAccumulator));
        byteAccumulator = 0;
        lastUsedType = null;
    }

    public static void flushString() {
        if (stringCounter == 1) {
            printToConsole(decorateMessage(lastUsedString));
        } else
        if (stringCounter != 0) {
            printToConsole(decorateMessage(lastUsedString + " (x" + stringCounter + ")"));
        }
        lastUsedString = null;
        stringCounter = 0;
        lastUsedType = null;
    }

    public static void log(int message) {
        int overflowNormalization = 0;
        if (checkIfTypeChanged("int")) {
            flushState();
        }
        if (message > (Integer.MAX_VALUE - intAccumulator)) {
            printToConsole(decorateMessage(Integer.MAX_VALUE));
            overflowNormalization = Integer.MAX_VALUE;
        }
        intAccumulator = intAccumulator + message - overflowNormalization;
        lastUsedType = "int";
    }

    public static void log(byte message) {
        byte overflowNormalization = 0;
        if (checkIfTypeChanged("byte")) {
            flushState();
        }
        if (message > (byte) (Byte.MAX_VALUE - byteAccumulator)) {
            printToConsole(decorateMessage((byte)Byte.MAX_VALUE));
            overflowNormalization = (byte) Byte.MAX_VALUE;
        }
        byteAccumulator = (byte) (byteAccumulator + message - overflowNormalization);
        lastUsedType = "byte";
    }

    public static void log(char message) {
        printToConsole(decorateMessage(message));
    }

    public static void log(String message) {
        if (checkIfTypeChanged("String")) {
            flushState();
        }
        boolean stringHasSameValueOrNull = (lastUsedString==null)||(message.equals(lastUsedString));
        if (!stringHasSameValueOrNull) {
            flushString();
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
