import org.example.authlab.Validator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    // Email Tests
    @Test
    void EmailTest1() {
        // Invalid test
        String invalidEmail = "";
        String invalidEmailResponse = Validator.validateEmail(invalidEmail);
        System.out.println("Testing invalid email: \"" + invalidEmail + "\" | Response: " + invalidEmailResponse);

        // Valid test
        String validEmail = "JOHN@example.com";
        String validEmailResponse = Validator.validateEmail(validEmail);
        System.out.println("Testing valid email: \"" + validEmail + "\" | Response: " + validEmailResponse);

        // Assertions
        assertNotNull(invalidEmailResponse, "Expecting a validation error for blank email.");
        assertNull(validEmailResponse, "Expecting no validation error for a valid email.");
    }

    @Test
    void EmailTest2() {
        // Invalid test
        String invalidEmail = "myemail";
        String invalidEmailResponse = Validator.validateEmail(invalidEmail);
        System.out.println("Testing invalid email: \"" + invalidEmail + "\" | Response: " + invalidEmailResponse);

        // Valid test
        String validEmail = "e@m.com";
        String validEmailResponse = Validator.validateEmail(validEmail);
        System.out.println("Testing valid email: \"" + validEmail + "\" | Response: " + validEmailResponse);

        // Assertions
        assertNotNull(invalidEmailResponse, "Expecting a validation error for email without '@'.");
        assertNull(validEmailResponse, "Expecting no validation error for a valid email.");
    }

    @Test
    void EmailTest3() {
        // Invalid test
        String invalidEmail = "@mail";
        String invalidEmailResponse = Validator.validateEmail(invalidEmail);
        System.out.println("Testing invalid email: \"" + invalidEmail + "\" | Response: " + invalidEmailResponse);

        // Valid test
        String validEmail = "JOHN@mm.com";
        String validEmailResponse = Validator.validateEmail(validEmail);
        System.out.println("Testing valid email: \"" + validEmail + "\" | Response: " + validEmailResponse);

        // Assertions
        assertNotNull(invalidEmailResponse, "Expecting a validation error for missing local part.");
        assertNull(validEmailResponse, "Expecting no validation error for a valid email.");
    }

    @Test
    void EmailTest4() {
        // Invalid test
        String invalidEmail = "john@mail.coooom";
        String invalidEmailResponse = Validator.validateEmail(invalidEmail);
        System.out.println("Testing invalid email: \"" + invalidEmail + "\" | Response: " + invalidEmailResponse);

        // Valid test
        String validEmail = "JJ@ee.gg";
        String validEmailResponse = Validator.validateEmail(validEmail);
        System.out.println("Testing valid email: \"" + validEmail + "\" | Response: " + validEmailResponse);

        // Assertions
        assertNotNull(invalidEmailResponse, "Expecting a validation error for an invalid domain name.");
        assertNull(validEmailResponse, "Expecting no validation error for a valid email.");
    }

    @Test
    void EmailTest5() {
        // Invalid test
        String invalidEmail = "mycat@@pets.com";
        String invalidEmailResponse = Validator.validateEmail(invalidEmail);
        System.out.println("Testing invalid email: \"" + invalidEmail + "\" | Response: " + invalidEmailResponse);

        // Valid test
        String validEmail = "4@g.ee";
        String validEmailResponse = Validator.validateEmail(validEmail);
        System.out.println("Testing valid email: \"" + validEmail + "\" | Response: " + validEmailResponse);

        // Assertions
        assertNotNull(invalidEmailResponse, "Expecting a validation error for email with multiple '@'.");
        assertNull(validEmailResponse, "Expecting no validation error for a valid email.");
    }

    // Password Tests
    @Test
    void PasswordTest1() {
        // Invalid test
        String invalidPassword = "";
        String invalidPasswordResponse = Validator.validatePassword(invalidPassword);
        System.out.println("Testing invalid password: \"" + invalidPassword + "\" | Response: " + invalidPasswordResponse);

        // Valid test
        String validPassword = "Password!2";
        String validPasswordResponse = Validator.validatePassword(validPassword);
        System.out.println("Testing valid password: \"" + validPassword + "\" | Response: " + validPasswordResponse);

        // Assertions
        assertNotNull(invalidPasswordResponse, "Expecting a validation error for blank password.");
        assertNull(validPasswordResponse, "Expecting no validation error for a valid password.");
    }

    @Test
    void PasswordTest2() {
        // Invalid test
        String invalidPassword = "e";
        String invalidPasswordResponse = Validator.validatePassword(invalidPassword);
        System.out.println("Testing invalid password: \"" + invalidPassword + "\" | Response: " + invalidPasswordResponse);

        // Valid test
        String validPassword = "23@Eeeffffffff";
        String validPasswordResponse = Validator.validatePassword(validPassword);
        System.out.println("Testing valid password: \"" + validPassword + "\" | Response: " + validPasswordResponse);

        // Assertions
        assertNotNull(invalidPasswordResponse, "Expecting a validation error for a short password.");
        assertNull(validPasswordResponse, "Expecting no validation error for a valid password.");
    }

    @Test
    void PasswordTest3() {
        // Invalid test
        String invalidPassword = "password";
        String invalidPasswordResponse = Validator.validatePassword(invalidPassword);
        System.out.println("Testing invalid password: \"" + invalidPassword + "\" | Response: " + invalidPasswordResponse);

        // Valid test
        String validPassword = "Kee3Ed2w2esf#";
        String validPasswordResponse = Validator.validatePassword(validPassword);
        System.out.println("Testing valid password: \"" + validPassword + "\" | Response: " + validPasswordResponse);

        // Assertions
        assertNotNull(invalidPasswordResponse, "Expecting a validation error for an insufficiently complex password.");
        assertNull(validPasswordResponse, "Expecting no validation error for a valid password.");
    }

    @Test
    void PasswordTest4() {
        // Invalid test
        String invalidPassword = "Pass1!";
        String invalidPasswordResponse = Validator.validatePassword(invalidPassword);
        System.out.println("Testing invalid password: \"" + invalidPassword + "\" | Response: " + invalidPasswordResponse);

        // Valid test
        String validPassword = " 2222feffe%E";
        String validPasswordResponse = Validator.validatePassword(validPassword);
        System.out.println("Testing valid password: \"" + validPassword + "\" | Response: " + validPasswordResponse);

        // Assertions
        assertNotNull(invalidPasswordResponse, "Expecting a validation error for a weak password.");
        assertNull(validPasswordResponse, "Expecting no validation error for a valid password.");
    }

    @Test
    void PasswordTest5() {
        // Invalid test
        String invalidPassword = "Password!";
        String invalidPasswordResponse = Validator.validatePassword(invalidPassword);
        System.out.println("Testing invalid password: \"" + invalidPassword + "\" | Response: " + invalidPasswordResponse);

        // Valid test
        String validPassword = "SDSe3f33!";
        String validPasswordResponse = Validator.validatePassword(validPassword);
        System.out.println("Testing valid password: \"" + validPassword + "\" | Response: " + validPasswordResponse);

        // Assertions
        assertNotNull(invalidPasswordResponse, "Expecting a validation error for an insufficiently diverse password.");
        assertNull(validPasswordResponse, "Expecting no validation error for a valid password.");
    }

    String email = "john@gmail.com";

    @Test
    void PasswordConfirmation1() {

        String Password1 = "Password2$";
        String Password2 = "Different2$";

        String invalidConfirmation = Validator.validateCredentials(email, Password1, Password2);
        System.out.println("Testing unsuccessful confirmation: \"" + Password1 + " and " + Password2 + "\" | Response: " + invalidConfirmation);

        String validConfirmation = Validator.validateCredentials(email, Password1, Password1);
        System.out.println("Testing sucessful confirmation: \"" + Password1 + " and " + Password1 + "\" | Response: " + validConfirmation);


        // Assertions
        assertNotNull(invalidConfirmation, "Expecting a validation error for confirmation password not matching.");
        assertNull(validConfirmation, "Expecting no validation error for matching password and confirmation.");
    }

    @Test
    void PasswordConfirmation2() {

        String Password1 = "123456789@!qQ";
        String Password2 = "ABCDEFGHI@!qQ";

        String invalidConfirmation = Validator.validateCredentials(email, Password1, Password2);
        System.out.println("Testing unsuccessful confirmation: \"" + Password1 + " and " + Password2 + "\" | Response: " + invalidConfirmation);

        String validConfirmation = Validator.validateCredentials(email, Password1, Password1);
        System.out.println("Testing sucessful confirmation: \"" + Password1 + " and " + Password1 + "\" | Response: " + validConfirmation);


        // Assertions
        assertNotNull(invalidConfirmation, "Expecting a validation error for confirmation password not matching.");
        assertNull(validConfirmation, "Expecting no validation error for matching password and confirmation.");
    }
}
