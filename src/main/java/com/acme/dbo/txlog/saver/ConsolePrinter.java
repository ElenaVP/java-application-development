package com.acme.dbo.txlog.saver;

import com.acme.dbo.txlog.message.Message;

public class ConsolePrinter implements Saver {
    public void save(Message message) {
        System.out.println(message.getDecoratedMessage());
    }
}
