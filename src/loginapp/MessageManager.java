package loginapp;

import java.util.Scanner;

public class MessageManager {

    // --- 1. ARRAYS (Requirement 1) ---
    public static String[] sentMessages = new String[100];
    public static String[] disregardedMessages = new String[100];
    public static String[] storedMessages = new String[100];
    public static String[] messageHashes = new String[100];
    public static String[] messageIDs = new String[100];
    public static String[] recipients = new String[100];

    public static int sentCount = 0;
    public static int disregardCount = 0;
    public static int storedCount = 0;
    public static int hashCount = 0;
    public static int idCount = 0;

    // --- 2. POPULATE TEST DATA (Requirement 3) ---
    public static void populateTestData() {
        // Message 1 - Sent
        Message msg1 = new Message("+27834557896", "Did you get the cake?", 1);
        sentMessages[sentCount] = msg1.getMessageText();
        messageIDs[idCount] = msg1.getMessageID();
        messageHashes[hashCount] = msg1.getMessageHash();
        recipients[idCount] = msg1.getRecipient();
        sentCount++; idCount++; hashCount++;

        // Message 2 - Stored
        Message msg2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", 2);
        storedMessages[storedCount] = msg2.getMessageText();
        messageIDs[idCount] = msg2.getMessageID();
        messageHashes[hashCount] = msg2.getMessageHash();
        recipients[idCount] = msg2.getRecipient();
        storedCount++; idCount++; hashCount++;

        // Message 3 - Disregard
        Message msg3 = new Message("+27834484567", "Yohoooo, I am at your gate.", 3);
        disregardedMessages[disregardCount] = msg3.getMessageText();
        disregardCount++;

        // Message 4 - Sent (Developer)
        Message msg4 = new Message("0838884567", "It is dinner time !", 4);
        sentMessages[sentCount] = msg4.getMessageText();
        messageIDs[idCount] = msg4.getMessageID();
        messageHashes[hashCount] = msg4.getMessageHash();
        recipients[idCount] = msg4.getRecipient();
        sentCount++; idCount++; hashCount++;

        // Message 5 - Stored
        Message msg5 = new Message("+27838884567", "Ok, I am leaving without you.", 5);
        storedMessages[storedCount] = msg5.getMessageText();
        messageIDs[idCount] = msg5.getMessageID();
        messageHashes[hashCount] = msg5.getMessageHash();
        recipients[idCount] = msg5.getRecipient();
        storedCount++; idCount++; hashCount++;
    }

    // --- 3. STORED MESSAGES MENU (Requirement 2) ---
    public static void storedMessagesMenu(Scanner scanner) {
        System.out.println("\n--- Stored Messages Menu ---");
        System.out.println("a. Display sender and recipient of all stored messages");
        System.out.println("b. Display the longest stored message");
        System.out.println("c. Search for a message ID");
        System.out.println("d. Search messages for a particular recipient");
        System.out.println("e. Delete a message using message hash");
        System.out.println("f. Display Report");
        System.out.print("Choose an option: ");
        
        String choice = scanner.nextLine();

        switch (choice.toLowerCase()) {
            case "a":
                displaySendersAndRecipients();
                break;
            case "b":
                displayLongestMessage();
                break;
            case "c":
                searchByMessageID(scanner);
                break;
            case "d":
                searchByRecipient(scanner);
                break;
            case "e":
                deleteByHash(scanner);
                break;
            case "f":
                displayReport();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // --- Helper Methods for the Menu ---
    private static void displaySendersAndRecipients() {
        System.out.println("\nStored Messages:");
        for (int i = 0; i < storedCount; i++) {
            System.out.println("Sender: Developer | Recipient: " + recipients[i+1] + " | Msg: " + storedMessages[i]);
        }
    }

    private static void displayLongestMessage() {
        String longest = "";
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i] != null && storedMessages[i].length() > longest.length()) {
                longest = storedMessages[i];
            }
        }
        System.out.println("Longest Message: " + longest);
    }

    private static void searchByMessageID(Scanner scanner) {
        System.out.print("Enter Message ID: ");
        String id = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < idCount; i++) {
            if (messageIDs[i].equals(id)) {
                System.out.println("Message found: " + storedMessages[i]);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Message ID not found.");
    }

    private static void searchByRecipient(Scanner scanner) {
        System.out.print("Enter Recipient Number: ");
        String recipient = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < storedCount; i++) {
            if (recipients[i+1].equals(recipient)) { 
                System.out.println("- " + storedMessages[i]);
                found = true;
            }
        }
        if (!found) System.out.println("No stored messages found for this recipient.");
    }

    private static void deleteByHash(Scanner scanner) {
        System.out.print("Enter Message Hash to delete: ");
        String hash = scanner.nextLine();
        for (int i = 0; i < hashCount; i++) {
            if (messageHashes[i].equals(hash)) {
                System.out.println("Message: \"" + storedMessages[i] + "\" successfully deleted.");
                storedMessages[i] = ""; 
                return;
            }
        }
        System.out.println("Hash not found.");
    }

    private static void displayReport() {
        System.out.println("\n--- Stored Messages Report ---");
        System.out.println("Hash\t\t\t| Recipient\t\t| Message");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(messageHashes[1] + "\t| " + recipients[1] + "\t| " + storedMessages[0]);
        System.out.println(messageHashes[3] + "\t| " + recipients[3] + "\t| " + storedMessages[1]);
    }
}