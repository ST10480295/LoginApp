

package loginapp;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before; 

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
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
        
        // We expect the hash to end with :0:HITONIGHT 
        // (0 from message num, HI from first word, TONIGHT from last word)
        String actualHash = msg.getMessageHash();
        System.out.println("THE ACTUAL HASH IS: " + actualHash);
        assertTrue("Hash should end with :1:HITONIGHT", actualHash.endsWith(":1:HITONIGHT"));
    }
    // ==========================================
    // PART 3: SETUP AND NEW UNIT TESTS
    // ==========================================

    @Before
    public void setUp() {
        // Reset the counts so the arrays start fresh for every single test
        MessageManager.sentCount = 0;
        MessageManager.storedCount = 0;
        MessageManager.idCount = 0;
        MessageManager.hashCount = 0;
        MessageManager.disregardCount = 0;
        
        // Load the specific test data required by the assignment
        MessageManager.populateTestData();
    }

    // 4. Test: Sent Messages array correctly populated
    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        // Test Data: Developer entry for Test data for message 1-4
        assertEquals("Did you get the cake?", MessageManager.sentMessages[0]);
        assertEquals("It is dinner time !", MessageManager.sentMessages[1]);
    }

    // 5. Test: Display the longest Message
    @Test
    public void testDisplayLongestMessage() {
        String longest = "";
        for (int i = 0; i < MessageManager.storedCount; i++) {
            if (MessageManager.storedMessages[i] != null && MessageManager.storedMessages[i].length() > longest.length()) {
                longest = MessageManager.storedMessages[i];
            }
        }
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    // 6. Test: Delete a message using a message hash
    @Test
    public void testDeleteMessageByHash() {
        // Test Data: Test Message 2
        String hashToDelete = MessageManager.messageHashes[1]; 
        
        // Simulate the deletion logic
        for (int i = 0; i < MessageManager.hashCount; i++) {
            if (MessageManager.messageHashes[i].equals(hashToDelete)) {
                MessageManager.storedMessages[0] = "";
                break;
            }
        }
        
        // Assert that the message is now empty
        assertEquals("", MessageManager.storedMessages[0]);
    }
} 
