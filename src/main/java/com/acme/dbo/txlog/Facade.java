package com.acme.dbo.txlog;

import java.util.Objects;

import static com.acme.dbo.txlog.ConsolePrinter.printToConsole;
import static com.acme.dbo.txlog.Decorator.decorateMessage;

public class Facade {
    public static int intAccumulator;
    public static String lastUsedType;
    public static String lastUsedString;
    public static int stringCounter;

    public static boolean checkIfTypeChanged(String currentType) {
        if  ((lastUsedType == null) || (currentType.equals(lastUsedType))) {
            return false;
        } else {
            return true;
        }
    }

    public static void flushInteger () {
        printToConsole(Integer.valueOf(intAccumulator).toString());
        intAccumulator = 0;
        lastUsedType = null;
    }

    public static void flushString () {
        if (stringCounter == 1) {
            printToConsole(lastUsedString);
        } else
        if (stringCounter != 0) {
            printToConsole(lastUsedString + " (x" + stringCounter + ")");
        }
        lastUsedString = null;
        stringCounter = 0;
        lastUsedType = null;
    }

    public static void log(int message) {
        int overflowNormalization = 0;
        if (checkIfTypeChanged("int")) {
            flushString();
        }
        if ((intAccumulator + message) < intAccumulator) {
            printToConsole(String.valueOf(Integer.MAX_VALUE));
            overflowNormalization = Integer.MAX_VALUE;
        }
        intAccumulator = intAccumulator + message - overflowNormalization;
        lastUsedType = "int";
    }

    public static void log(byte message) {
        printToConsole(decorateMessage(message));
    }

    public static void log(char message) {
        printToConsole(decorateMessage(message));
    }

    public static void log(String message) {
        if (checkIfTypeChanged("String")) {
            flushInteger();
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
