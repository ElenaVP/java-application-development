package com.acme.dbo.txlog.iteration02;

import com.acme.dbo.txlog.Facade;
import com.acme.dbo.txlog.LogService;
import com.acme.dbo.txlog.StateFlusher;
import com.acme.dbo.txlog.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion




    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when
        Facade.log("str 1");
        Facade.log(1);
        Facade.log(2);
        Facade.log("str 2");
        Facade.log(0);
        Facade.clearState();
        //endregion

        //region then
        assertSysoutContains("str 1\r\n");
        assertSysoutContains("3\r\n");
        assertSysoutContains("str 2\r\n");
        assertSysoutContains("0\r\n");
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
        //region when
        Facade.log("str 1");
        Facade.log("str 2");
        Facade.log("str 2");
        Facade.log(0);
        Facade.log("str 2");
        Facade.log("str 3");
        Facade.log("str 3");
        Facade.log("str 3");
        Facade.clearState();
        //endregion

        //region then
        assertSysoutContains("str 1");
        assertSysoutContains("str 2 (x2)");
        assertSysoutContains("0\r\n");
        assertSysoutContains("str 2\r\n");
        assertSysoutContains("str 3 (x3)\r\n");
        //endregion
    }
/*
    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
        //region when
        Facade.log("str 1");
        Facade.log(10);
        Facade.log(Integer.MAX_VALUE);
        Facade.log("str 2");
        Facade.log(0);
        Facade.flushInteger();
        //endregion

        //region then
        assertSysoutContains("str 1\r\n");
        assertSysoutContains(Integer.MAX_VALUE + "\r\n");
        assertSysoutContains("10\r\n");
        assertSysoutContains("str 2\r\n");
        assertSysoutContains("0\r\n");
        //endregion
    }


    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
        //region when
        Facade.log("str 1");
        Facade.log((byte)10);
        Facade.log((byte)Byte.MAX_VALUE);
        Facade.log("str 2");
        Facade.log(0);
        Facade.flushInteger();
        //endregion

        //region then
        assertSysoutContains("str 1\r\n");
        assertSysoutContains(Byte.MAX_VALUE + "\r\n");
        assertSysoutContains("10\r\n");
        assertSysoutContains("str 2\r\n");
        assertSysoutContains("0\r\n");
        //endregion
    }

*/
}