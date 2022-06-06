package com.acme.dbo.txlog.message;

public class IntMessage extends PrefixDecoratedMessage implements Message{
    private int message;
    public static int accumulator;

    public IntMessage(int message) {
        super("primitive: ");
        this.message = message;
    }

    @Override
    public String getDecoratedMessage() {
        return super.getDecoratedMessage(String.valueOf(message));
    }

    public Message accumulate(Message currentMessage) {
        return new IntMessage(message + ((IntMessage)currentMessage).message);
    }

    public boolean isSame(Message currentMessage) {
        if (currentMessage instanceof IntMessage) {
            return true;
        } else {
            return false;
        }
    }

}
