package com.acme.dbo.txlog;

import static com.acme.dbo.txlog.ConsolePrinter.printToConsole;
import static com.acme.dbo.txlog.Decorator.decorateMessage;

public class Facade {
    public static int intAccumulator;
    public static String lastUsedType;

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
    }

    public static void log(int message) {
        if (message == 0) {
            flushInteger();
        }
        intAccumulator = intAccumulator + message;
        lastUsedType="int";
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
        };
        printToConsole(message);
        lastUsedType="String";
    }

    public static void log(boolean message) {
        printToConsole(decorateMessage(message));
    }

    public static void log(Object message) {
        printToConsole(decorateMessage(message));
    }
}
