package com.acme.dbo.txlog;

import com.acme.dbo.txlog.message.*;

public class Facade {
    public static LogService logService = new LogService();

/*
    public static  void log(byte message) {
        logService.log(new ByteMessage(message));
    }
*/
    public static void log(char message) {
        try {
            logService.log(new CharMessage(message));
        } catch (LogOperationException e) {
            e.printStackTrace();
        }
    }

    public static void log(int message) {
        try {
            logService.log(new IntMessage(message));
        } catch (LogOperationException e) {
            e.printStackTrace();
        }
    }

    public static void log(String message) {
        try {
            logService.log(new StringMessage(message));
        } catch (LogOperationException e) {
            e.printStackTrace();
        }
    }

    public static void log(boolean message) {
            try {
                logService.log(new BooleanMessage(message));
            } catch (LogOperationException e) {
                e.printStackTrace();
            }
    }

    public static void clearState() {
        try {
            logService.saveState();
        } catch (LogOperationException e) {
            e.printStackTrace();
        }
    }

 /*   public static void log(Object message) {
        logService.log(new ObjectMessage(message));
    }

    public static void flushInteger () {
        logService.getFlusher().flushInteger();
    }

    public static void flushByte () {
        logService.getFlusher().flushByte();
    }

    public static void flushString () {
        logService.getFlusher().flushString();
    }



    public static void log(int[] arrayMessage) {
        logService.log(new ArrayMessage(arrayMessage));
    }
*/
}
