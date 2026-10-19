/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginapp;

import java.util.Random;

public class Message {
    // Variables required by the brief
    private String messageID;
    private int numMessagesSent;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Constructor
    public Message(String recipient, String messageText, int numMessagesSent) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.numMessagesSent = numMessagesSent;
        this.messageID = generateID();
        this.messageHash = createMessageHash();
    }

    // 1. Boolean: checkMessageID() - Ensures ID is not more than 10 characters
    public boolean checkMessageID() {
        return this.messageID.length() <= 10;
    }

    // 2. String: checkRecipientCell() - Ensures recipient is valid
    public String checkRecipientCell() {
        // Hint says to reuse Part 1 logic: +27 followed by 9 digits
        if (this.recipient.matches("\\+27\\d{9}")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // 3. String: createMessageHash() - Generates the hash
    public String createMessageHash() {
        // Get first 2 digits of ID
        String firstTwoID = messageID.substring(0, 2);
        
        // Get first and last words of the message
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        // Construct the hash: 00:0:HITHANKS
        String hash = firstTwoID + ":" + numMessagesSent + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // Helper method to generate a random 10-digit ID
    private String generateID() {
        Random rand = new Random();
        long id = (long) (rand.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(id);
    }

    // Getters for testing and printing
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
}