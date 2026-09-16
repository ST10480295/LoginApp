/*
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
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String firstName;
        String lastName;
        String userName;
        String password;
        String cellPhoneNumber;

        // Ask the user to enter their details
        System.out.print("Enter your first name: ");
        firstName = input.nextLine();

        System.out.print("Enter your last name: ");
        lastName = input.nextLine();

        System.out.print("Enter your username: ");
        userName = input.nextLine();

        System.out.print("Enter your password: ");
        password = input.nextLine();

        System.out.print("Enter your cellphone number: ");
        cellPhoneNumber = input.nextLine();

        // Create a LoginApp object
        LoginApp user = new LoginApp(
                firstName,
                lastName,
                userName,
                password,
                cellPhoneNumber
        );

        // Register the user
        String registrationMessage = user.registerUser();

        System.out.println(registrationMessage);

        // Only continue to login if registration was successful
        if (user.checkUserName()
                && user.checkPasswordComplexity()
                && user.checkCellPhoneNumber()) {

            System.out.print("Enter your username to login: ");
            String enteredUserName = input.nextLine();

            System.out.print("Enter your password to login: ");
            String enteredPassword = input.nextLine();

            // Attempt login
            user.loginUser(enteredUserName, enteredPassword);

            // Display login status
            System.out.println(user.returnLoginStatus());
        }

        input.close();
    }
}