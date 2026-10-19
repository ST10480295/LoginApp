

package loginapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    // 1. Test: Message should not be more than 250 characters
    @Test
    public void testMessageLength() {
        // Success Case (Less than 250 chars)
        String shortMsg = "Hi Mike, can you join us for dinner tonight?";
        assertTrue("Message should be ready to send", shortMsg.length() <= 250);

        // Failure Case (More than 250 chars - creating a long string)
        StringBuilder longMsgBuilder = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longMsgBuilder.append("a");
        }
        String longMsg = longMsgBuilder.toString();
        assertFalse("Message should exceed 250 characters", longMsg.length() <= 250);
    }

    // 2. Test: Recipient number is correctly formatted
    @Test
    public void testRecipientCell() {
        // Success case
        Message msgValid = new Message("+27718693002", "Hello", 1);
        assertEquals("Cell phone number successfully captured.", msgValid.checkRecipientCell());

        // Failure case
        Message msgInvalid = new Message("08575975889", "Hello", 1);
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msgInvalid.checkRecipientCell());
    }

    // 3. Test: Message Hash is correct
    @Test
    public void testMessageHash() {
        // Using test data from the brief: Recipient +27718693002, Message: "Hi Mike, can you join us for dinner tonight?"
        // The message number is 0 (first message)
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        
        // We expect the hash to end with :0:HITONIGHT 
        // (0 from message num, HI from first word, TONIGHT from last word)
        String actualHash = msg.getMessageHash();
        assertTrue("Hash should end with :0:HITONIGHT", actualHash.endsWith(":0:HITONIGHT"));
    }
} 
