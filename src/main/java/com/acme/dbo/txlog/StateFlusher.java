package com.acme.dbo.txlog;

import com.acme.dbo.txlog.message.ByteMessage;
import com.acme.dbo.txlog.message.IntMessage;
import com.acme.dbo.txlog.message.Message;
import com.acme.dbo.txlog.message.StringMessage;
import com.acme.dbo.txlog.saver.ConsolePrinter;
import com.acme.dbo.txlog.saver.Saver;


public class StateFlusher {
    private Saver saver;

    public StateFlusher(Saver saver) {
        this.saver = saver;
    }
/*
       public void flushState() {
        if ("int".equals(LogService.lastUsedType)) {
            flushInteger();
        }
        if ("String".equals(LogService.lastUsedType)) {
            flushString();
        }
        LogService.lastUsedType = null;
    }

    public void flushInteger() {
        saver.save(IntMessage.getDecoratedAccumulatedMessage());
        IntMessage.accumulator = 0;
    }

    public void flushString() {
        saver.save(StringMessage.getDecoratedAccumulatedMessage());
        StringMessage.lastUsedString = null;
        StringMessage.stringCounter = 0;
    }

    public void flushMessage(Message message) {
        saver.save(message.getDecoratedMessage());
        LogService.lastUsedType = null;
        LogService.currentMessage = null;
    }
    */
}
