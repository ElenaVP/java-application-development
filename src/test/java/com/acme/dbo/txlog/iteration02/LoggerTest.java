package com.acme.dbo.txlog.iteration02;

import com.acme.dbo.txlog.Facade;
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
        Facade.flushInteger();
        //endregion

        //region then
        assertSysoutContains(
            "str 1\r\n" +
            "3\r\n" +
            "str 2\r\n" +
            "0\r\n"
        );
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
        Facade.flushString();
        //endregion

        //region then
        assertSysoutEquals(
                "str 1" + System.lineSeparator() +
                        "str 2 (x2)" + System.lineSeparator() +
                        "0\r\n" +
                        "str 2\r\n" +
                        "str 3 (x3)\r\n"
        );
        //endregion
    }

    /*
    TODO: implement Logger solution to match specification as tests
    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
        //region when
        Facade.log("str 1");
        Facade.log(10);
        Facade.log(Integer.MAX_VALUE);
        Facade.log("str 2");
        Facade.log(0);
        //endregion

        //region then
        assertSysoutEquals(
            "str 1\r\n" +
            "10\r\n" +
            Integer.MAX_VALUE + "\r\n" +
            "str 2\r\n" +
            "0\r\n"
        );
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
        //endregion

        //region then
        assertSysoutEquals(
            "str 1\r\n" +
            "10\r\n" +
            Byte.MAX_VALUE + "\r\n" +
            "str 2\r\n" +
            "0\r\n"
        );
        //endregion
    }


    */
}