package com.acme.dbo.txlog.message;

public interface Message {
   // Message getMessage();

    String getDecoratedMessage();

    Message accumulate(Message message);

    boolean isSame(Message message);
}
