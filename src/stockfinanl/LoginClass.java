/*This class is used to implements the concept of collection and generics that we have learned in our course
with the help of HashMap class which implements the Map interface
*/
package stockfinanl;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
public class LoginClass {
    private static HashMap<String, String> users = new HashMap<>();
    public void logIn() throws IOException {
        Scanner sc = new Scanner(System.in);
        String adminUserName;
        String adminPassword;
        System.out.println("*****Log In Page*******");

        System.out.println("Enter userName");
        adminUserName = sc.nextLine();
        if (!users.containsKey(adminUserName)) {
            System.out.println("User not found. Would you like to register a new user? (yes/no)");
            String registerChoice = sc.nextLine();
            if (registerChoice.equalsIgnoreCase("yes")) {
                System.out.println("Enter a password for the new user: ");
                adminPassword = sc.nextLine();
                users.put(adminUserName, adminPassword);
                System.out.println("User registered successfully!");
                logIn();
            } else {
                System.out.println("User registration declined. Exiting the program.");
                return;
            }
        } else {
            System.out.println("Enter User Password ");
            adminPassword = sc.nextLine();

            if (adminPassword.equals(users.get(adminUserName))) {
                System.out.println("===========Successfully Logged in==============");
                System.out.println("========Welcome to Stock Information System=====");
                System.out.println("Admin page");
            } else {
                System.out.println("Invalid Password");
                System.out.println("Re-enter Password");
                logIn();
            }
        }
    }
}
