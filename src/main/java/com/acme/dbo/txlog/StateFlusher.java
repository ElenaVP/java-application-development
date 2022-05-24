package com.acme.dbo.txlog;

import com.acme.dbo.txlog.message.ByteMessage;
import com.acme.dbo.txlog.message.IntMessage;
import com.acme.dbo.txlog.message.StringMessage;


public class StateFlusher {
    private ConsolePrinter printer;

    public StateFlusher(ConsolePrinter printer) {
        this.printer = printer;
    }

    public void flushState() {
        if ("int".equals(LogService.lastUsedType)) {
            flushInteger();
        }
        if ("String".equals(LogService.lastUsedType)) {
            flushString();
        }
        if ("byte".equals(LogService.lastUsedType)) {
            flushByte();
        }
        LogService.lastUsedType = null;
    }

    public void flushInteger() {
        printer.printToConsole(IntMessage.getDecoratedAccumulatedMessage());
        IntMessage.accumulator = 0;

    }

    public void flushByte() {
        printer.printToConsole(ByteMessage.getDecoratedAccumulatedMessage());
        ByteMessage.accumulator = 0;
        //LogService.lastUsedType = null;
    }

    public void flushString() {
        printer.printToConsole(StringMessage.getDecoratedAccumulatedMessage());
        StringMessage.lastUsedString = null;
        StringMessage.stringCounter = 0;
        //LogService.lastUsedType = null;
    }
}
