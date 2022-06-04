package com.acme.dbo.txlog;

import com.acme.dbo.txlog.message.*;
import com.acme.dbo.txlog.saver.ConsolePrinter;
import com.acme.dbo.txlog.saver.Saver;

public class LogService {

    private Saver saver;
    private Message currentMessage;

    public LogService() {
        this.saver = new ConsolePrinter();
        this.currentMessage = new DefaultMessage();
    }

    public void log(Message message) throws LogOperationException{
        try {
            if (currentMessage instanceof DefaultMessage) {
                currentMessage = message;
            } else if (message.isSame(currentMessage)) {
                currentMessage = message.accumulate(currentMessage);
            } else {
                saver.save(currentMessage);
                currentMessage = message;
            }
        } catch (SaveOperationException savingException) {
            throw new LogOperationException("Saving error: " + savingException.getMessage());
        }
    }

    public void saveState() throws LogOperationException{
        try {
            saver.save(currentMessage);
        } catch (SaveOperationException savingException) {
            throw new LogOperationException("Saving error: " + savingException.getMessage());
        }

        currentMessage = new DefaultMessage();
    }

}
