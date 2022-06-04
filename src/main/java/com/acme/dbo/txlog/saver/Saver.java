package com.acme.dbo.txlog.saver;

import com.acme.dbo.txlog.message.Message;

public interface Saver {
    public void save(Message message);
}
