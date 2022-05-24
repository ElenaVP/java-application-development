package com.acme.dbo.txlog;

import com.acme.dbo.txlog.message.*;

public class LogService {

    private ConsolePrinter printer;
    private StateFlusher flusher;
    public static String lastUsedType;

    public LogService() {
        this.printer = new ConsolePrinter();
        this.flusher = new StateFlusher(printer);
    }

    public StateFlusher getFlusher() {
        return flusher;
    }

    public boolean checkIfTypeChanged(String currentType) {
        if  ((lastUsedType == null) || (currentType.equals(lastUsedType))) {
            return false;
        } else {
            return true;
        }
    }

    public void flushStateIfRequired(String currentType) {
        if (checkIfTypeChanged(currentType)) {
            flusher.flushState();
        }
    }

    public void log(IntMessage message) {
        flushStateIfRequired("int");
        message.accumulate(flusher);
        lastUsedType = "int";
    }


    public void log(StringMessage message) {
        flushStateIfRequired("String");
        if (!message.isSameOrNull()) {
            flusher.flushString();
        }
        message.accumulate();
        lastUsedType="String";
    }

    public void log(CharMessage message) {
        printer.printToConsole(message.getDecoratedMessage());

    }

    public void log(BooleanMessage message) {
        printer.printToConsole(message.getDecoratedMessage());

    }

    public void log(ObjectMessage message) {
        printer.printToConsole(message.getDecoratedMessage());

    }

    public void log(ByteMessage message) {
        flushStateIfRequired("byte");
        message.accumulate(flusher);
        lastUsedType = "byte";
    }

    /*
    public  void log(int[] arrayMessage) {
        StringBuilder message = new StringBuilder("{");
        for (int current: arrayMessage) {
            message.append(current);
            message.append(", ");
        }
        message.delete(message.length()-2, message.length());
        message.append("}");
        printToConsole(decorateMessage("primitives array: ", message.toString()));
    }
*/
}
