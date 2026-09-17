// Unit tests for the Login Application
package loginapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginAppTest {

    // Username is correctly formatted
    @Test
    public void testCheckUserNameCorrect() {

        LoginApp user = new LoginApp(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(true, user.checkUserName());
    }

    // Username is incorrectly formatted
    @Test
    public void testCheckUserNameIncorrect() {

        LoginApp user = new LoginApp(
                "Kyle",
                "Smith",
                "kyle!!!!!!!",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(false, user.checkUserName());
    }

    // Password meets complexity requirements
    @Test
    public void testCheckPasswordComplexityCorrect() {

        LoginApp user = new LoginApp(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(true, user.checkPasswordComplexity());
    }

    // Password does not meet complexity requirements
    @Test
    public void testCheckPasswordComplexityIncorrect() {

        LoginApp user = new LoginApp(
                "Kyle",
                "Smith",
                "kyl_1",
                "password",
                "+27838968976"
        );

        assertEquals(false, user.checkPasswordComplexity());
    }

    // Cell phone number is correctly formatted
    @Test
    public void testCheckCellPhoneNumberCorrect() {

        LoginApp user = new LoginApp(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(true, user.checkCellPhoneNumber());
    }

    // Cell phone number is incorrectly formatted
    @Test
    public void testCheckCellPhoneNumberIncorrect() {

        LoginApp user = new LoginApp(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "08966553"
        );

        assertEquals(false, user.checkCellPhoneNumber());
    }

    // Login successful
    @Test
    public void testLoginSuccessful() {

        LoginApp user = new LoginApp(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(true, user.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    // Login failed
    @Test
    public void testLoginFailed() {

        LoginApp user = new LoginApp(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(false, user.loginUser("wrong_user", "wrong_password"));
    }

    // Username correctly formatted using assertTrue
    @Test
    public void testUsernameCorrectlyFormatted() {

        LoginApp user = new LoginApp(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(user.checkUserName());
    }

    // Username incorrectly formatted using assertFalse
    @Test
    public void testUsernameIncorrectlyFormatted() {

        LoginApp user = new LoginApp(
                "Kyle",
                "Smith",
                "kyle!!!!!!!",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertFalse(user.checkUserName());
    }

    // Password meets complexity requirements using assertTrue
    @Test
    public void testPasswordMeetsComplexity() {

        LoginApp user = new LoginApp(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(user.checkPasswordComplexity());
    }

    // Password does not meet complexity requirements using assertFalse
    @Test
    public void testPasswordDoesNotMeetComplexity() {

        LoginApp user = new LoginApp(
                "Kyle",
                "Smith",
                "kyl_1",
                "password",
                "+27838968976"
        );

        assertFalse(user.checkPasswordComplexity());
    }

    // Cell phone number correctly formatted using assertTrue
    @Test
    public void testCellPhoneCorrectlyFormatted() {

        LoginApp user = new LoginApp(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(user.checkCellPhoneNumber());
    }

    // Cell phone number incorrectly formatted using assertFalse
    @Test
    public void testCellPhoneIncorrectlyFormatted() {

        LoginApp user = new LoginApp(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "08966553"
        );

        assertFalse(user.checkCellPhoneNumber());
    }
} 