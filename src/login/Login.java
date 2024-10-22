package login;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Login {
    private Map<String, String> userData;

    public Login() {
        userData = new HashMap<>();
    }

    // Check if the username is formatted correctly
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Check if the password meets the complexity requirements
    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
               Pattern.compile("[A-Z]").matcher(password).find() &&
               Pattern.compile("[0-9]").matcher(password).find() &&
               Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find();
    }

    // Register a new user
    public String registerUser(String username, String password) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }

        // Store the user data
        userData.put(username, password);
        return "User registered successfully.";
    }

    // Login a user
    public boolean loginUser(String username, String password) {
        return userData.containsKey(username) && userData.get(username).equals(password);
    }

    // Return login status message
    public String returnLoginStatus(boolean loginSuccessful, String username) {
        if (loginSuccessful) {
            return "Welcome " + username + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public static void main(String[] args) {
        Login loginSystem = new Login();

        // Test user registration
        System.out.println(loginSystem.registerUser("kyl_1", "Ch&&sec@ke99!")); // Should succeed
        System.out.println(loginSystem.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!")); // Should fail username
        System.out.println(loginSystem.registerUser("kyl_2", "password")); // Should fail password

        // Test user login
        boolean loginSuccess = loginSystem.loginUser("kyl_1", "Ch&&sec@ke99!");
        System.out.println(loginSystem.returnLoginStatus(loginSuccess, "kyl_1")); // Should welcome user

        loginSuccess = loginSystem.loginUser("kyl_1", "wrongpassword");
        System.out.println(loginSystem.returnLoginStatus(loginSuccess, "kyl_1")); // Should fail
    }
}