package com.acme.dbo.txlog.message;

public class StringMessage extends PrefixDecoratedMessage implements Message{
    private String message;
    private int counter;

    public StringMessage(String message) {
        super("string: ");
        this.message = message;
        this.counter = 1;
    }

    public StringMessage(String message, int counter) {
        super("string: ");
        this.message = message;
        this.counter = counter;
    }

    @Override
    public String getDecoratedMessage() {
        if (counter == 1) {
            return super.getDecoratedMessage(message);
        } else
        if (counter != 0) {
            return super.getDecoratedMessage(message + " (x" + counter + ")");
        } else {
            return super.getDecoratedMessage(message);
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
