package com.acme.dbo.txlog.saver;

import com.acme.dbo.txlog.SaveOperationException;
import com.acme.dbo.txlog.message.Message;

public class ConsolePrinter implements Saver {
    public void save(Message message) throws SaveOperationException {
        if (message == null) {
            throw new SaveOperationException("Cannot save null message.");
        } else {
            System.out.println(message.getDecoratedMessage());
        }
    }
}
