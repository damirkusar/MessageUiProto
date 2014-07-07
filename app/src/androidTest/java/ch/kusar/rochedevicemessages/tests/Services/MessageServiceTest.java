package ch.kusar.rochedevicemessages.tests.Services;

import android.test.InstrumentationTestCase;

import java.util.ArrayList;

import ch.kusar.rochedevicemessages.Models.Message;
import ch.kusar.rochedevicemessages.Services.MessageService;

/**
 * Created by ku5ar on 07.07.14.
 */
public class MessageServiceTest extends InstrumentationTestCase {

    private MessageService messageService;

    protected void setUp() throws Exception {
        super.setUp();

        this.messageService = new MessageService();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public final void test_getMessages_sizeIs_10() {
        ArrayList<Message> list = this.messageService.getMessages();

        assertEquals(10, list.size());
    }
}
