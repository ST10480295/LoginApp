/* // Main class for the Login Application
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loginapp;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class LoginApp {

    // Declare user information
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String cellPhoneNumber;

    private boolean loginSuccessful;

    public LoginApp(String firstName, String lastName, String userName,
            String password, String cellPhoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;

        this.loginSuccessful = false;
    }

    // Method to check if the username is correctly formatted
    public boolean checkUserName() {

        return userName.contains("_") && userName.length() <= 5;
    }

    // Method to check if the password is correctly formatted
    public boolean checkPasswordComplexity() {

        boolean hasUppercase = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        if (password.length() < 8) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {

            char character = password.charAt(i);

            if (Character.isUpperCase(character)) {
                hasUppercase = true;
            }

            if (Character.isDigit(character)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(character)) {
                hasSpecialCharacter = true;
            }
        }

        return hasUppercase && hasNumber && hasSpecialCharacter;
    }

    // Method to check if the cellphone number is correctly formatted
    public boolean checkCellPhoneNumber() {

        return cellPhoneNumber.matches("\\+27\\d{9}");
    }

    // Method to register the user
    public String registerUser() {

        if (!checkUserName()) {

            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";

        } else if (!checkPasswordComplexity()) {

            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";

        } else if (!checkCellPhoneNumber()) {

            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";

        } else {

            return "Username successfully captured.\n"
                    + "Password successfully captured.\n"
                    + "Cell number successfully captured.";
        }
    }

    // Method to log in the user
    public boolean loginUser(String enteredUserName, String enteredPassword) {

        if (enteredUserName.equals(userName)
                && enteredPassword.equals(password)) {

            loginSuccessful = true;

        } else {

            loginSuccessful = false;
        }

        return loginSuccessful;
    }

    // Method to return the login status
    public String returnLoginStatus() {

        if (loginSuccessful) {

            return "Welcome " + firstName + " " + lastName
                    + " it is great to see you again.";

        } else {

            return "Username or password incorrect, please try again.";
        }
    }

    // Main method
    // Main method
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // PART 1: REGISTRATION AND LOGIN 
        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();
        System.out.print("Enter your username: ");
        String userName = input.nextLine();
        System.out.print("Enter your password: ");
        String password = input.nextLine();
        System.out.print("Enter your cellphone number: ");
        String cellPhoneNumber = input.nextLine();

        LoginApp user = new LoginApp(firstName, lastName, userName, password, cellPhoneNumber);
        String registrationMessage = user.registerUser();
        System.out.println(registrationMessage);

        // Only continue if registration was successful
        if (user.checkUserName() && user.checkPasswordComplexity() && user.checkCellPhoneNumber()) {
            System.out.print("Enter your username to login: ");
            String enteredUserName = input.nextLine();
            System.out.print("Enter your password to login: ");
            String enteredPassword = input.nextLine();

            user.loginUser(enteredUserName, enteredPassword);
            System.out.println(user.returnLoginStatus());

            //PART 2: MESSAGING (Only runs if login is successful) 
            if (user.loginSuccessful) {
                System.out.println("\nWelcome to QuickChat.");

                // 5. Users define how many messages they wish to enter
                System.out.print("How many messages do you wish to enter? ");
                int numMessages = input.nextInt();
                input.nextLine(); // Consume the leftover newline

                // 4. The application should run until the user selects 'quit'
                boolean running = true;
                while (running) {
                    System.out.println("\n--- MENU ---");
                    System.out.println("Option 1) Send Messages");
                    System.out.println("Option 2) Show recently sent messages");
                    System.out.println("Option 3) Quit");
                    System.out.print("Choose an option: ");
                    int choice = input.nextInt();
                    input.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            // Send Messages
                            for (int i = 0; i < numMessages; i++) {
                                System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");
                                System.out.print("Enter recipient cell number (+27...): ");
                                String recipient = input.nextLine();

                                System.out.print("Enter message (max 250 chars): ");
                                String msgText = input.nextLine();

                                // 6. Validate message length
                                if (msgText.length() > 250) {
                                    System.out.println("Please enter a message of less than 250 characters.");
                                    i--; // Decrement so it doesn't count as a sent message
                                    continue; 
                                }

                                // Create the Message object
                                Message msg = new Message(recipient, msgText, i + 1); 
                                
                                // Validate recipient
                                System.out.println(msg.checkRecipientCell());
                                
                                // 6. Send, Disregard, or Store
                                System.out.println("Choose an option: 1) Send Message  2) Disregard Message  3) Store Message to send later");
                                int action = input.nextInt();
                                input.nextLine(); 

                                if (action == 1) {
                                    System.out.println("Message successfully sent");
                                    System.out.println("\n--- Message Details ---");
                                    System.out.println("Message ID: " + msg.getMessageID());
                                    System.out.println("Message Hash: " + msg.getMessageHash());
                                    System.out.println("Recipient: " + msg.getRecipient());
                                    System.out.println("Message: " + msg.getMessageText());
                                } else if (action == 2) {
                                    System.out.println("Press 0 to delete the message");
                                } else if (action == 3) {
                                    System.out.println("Message successfully stored");
                                }
                            }
                            System.out.println("\nTotal messages sent: " + numMessages);
                            break;

                        case 2:
                            System.out.println("Coming Soon.");
                            break;

                        case 3:
                            System.out.println("Exiting QuickChat. Goodbye!");
                            running = false;
                            break;

                        default:
                            System.out.println("Invalid option. Please choose 1, 2, or 3.");
                    }
                }
            }
        }
        input.close();
    }

        
}
