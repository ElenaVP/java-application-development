package com.acme.dbo.txlog;

import com.acme.dbo.txlog.message.*;
import com.acme.dbo.txlog.saver.ConsolePrinter;
import com.acme.dbo.txlog.saver.Saver;

public class LogService {

    private Saver saver;
    private StateFlusher flusher;
    public static String lastUsedType;
    private Message currentMessage;

    public LogService() {
        this.saver = new ConsolePrinter();
        this.flusher = new StateFlusher(saver);
        this.currentMessage = new DefaultMessage();
    }
/*
    public StateFlusher getFlusher() {
        return flusher;
    }*/
/*
    public boolean checkIfTypeChanged(String currentType) {
        if  ((lastUsedType == null) || (currentType.equals(lastUsedType))) {
            return false;
        } else {
            return true;
        }
    }

    public void flushStateIfRequired(String currentType) {
        boolean ifTypeChanged = (lastUsedType == null) || (currentType.equals(lastUsedType));
        if (!ifTypeChanged) {
            flusher.flushState();
        }
    }
*/
    public void log(Message message) {
        if (currentMessage instanceof DefaultMessage) {
            currentMessage = message;
        } else if (message.isSame(currentMessage)) {
            currentMessage = message.accumulate(currentMessage);
        } else {
            saver.save(currentMessage);
            currentMessage = message;
        }
    }

    public void saveState() {
        saver.save(currentMessage);
        currentMessage = new DefaultMessage();
    }
/*
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
        saver.save(message.getDecoratedMessage());

    }

    public void log(BooleanMessage message) {
        saver.save(message.getDecoratedMessage());

    }

    public void log(ObjectMessage message) {
        saver.save(message.getDecoratedMessage());

    }

    public void log(ByteMessage message) {
        saver.save(message.getDecoratedMessage());
    }
    */
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
