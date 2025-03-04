package com.acme.dbo.txlog.iteration01;

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
    public void shouldLogInteger() throws IOException {
        //region when
        Facade.log(1);
        Facade.clearState();
        Facade.log(0);
        Facade.clearState();
        Facade.log(-1);
        Facade.clearState();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutEquals("primitive: 1\r\nprimitive: 0\r\nprimitive: -1\r\n");
        //endregion
    }

/*
    @Test
    public void shouldLogByte() throws IOException {
        //region when
        Facade.log((byte)1);
        Facade.flushByte();
        Facade.log((byte)0);
        Facade.flushByte();
        Facade.log((byte)-1);
        Facade.flushByte();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("1");
        assertSysoutContains("0");
        assertSysoutContains("-1");
        //endregion
    }



    @Test
    public void shouldLogChar() throws IOException {
        //region when
        Facade.log('a');
        Facade.log('b');
        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }

*/

    @Test
    public void shouldLogString() throws IOException {
        //region when
        Facade.log("test string 1");
        Facade.log("other str");
        Facade.clearState();
        //endregion

        //region then
        assertSysoutContains("string: ");
        assertSysoutContains("test string 1");
        assertSysoutContains("other str");
        //endregion
    }


/*
    @Test
    public void shouldLogBoolean() throws IOException {
        //region when
        Facade.log(true);
        Facade.log(false);
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("true");
        assertSysoutContains("false");
        //endregion
    }

    @Test
    public void shouldLogReference() throws IOException {
        //region when
        Facade.log(new Object());
        //endregion

        //region then
        assertSysoutContains("reference: ");
        assertSysoutContains("@");
        //endregion
    }

    TODO: implement Logger solution to match specification as tests
    */
}