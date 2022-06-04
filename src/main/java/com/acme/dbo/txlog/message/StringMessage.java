package com.acme.dbo.txlog.message;

public class StringMessage implements Message{
    private String message;
    private int counter;

    public StringMessage(String message) {
        this.message = message;
        this.counter = 1;
    }

    public StringMessage(String message, int counter) {
        this.message = message;
        this.counter = counter;
    }
/*
    public String getMessage() {
        return message;
    }
*/
    @Override
    public String getDecoratedMessage() {
        if (counter == 1) {
            return "string: " + message;
        } else
        if (counter != 0) {
            return message + " (x" + counter + ")";
        } else {
            return message;
        }
    }

    @Override
    public Message accumulate(Message currentMessage) {
        return new StringMessage(message, ((StringMessage) currentMessage).counter + 1);
    }

    @Override
    public boolean isSame(Message currentMessage) {
        boolean isSameType = currentMessage instanceof StringMessage;
        if (! isSameType) {
            return false;
        } else if (message.equals(((StringMessage) currentMessage).message)) {
            return true;
        } else {
            return false;
        }
    }

}
