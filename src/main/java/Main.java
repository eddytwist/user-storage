import dao.UserFileDao;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private static boolean finished = false;
    public static Scanner scanner = new Scanner(System.in);
    public static UserFileDao userFileDao = new UserFileDao();

    public static void main(String[] args) {
        LOG.info("THE PROGRAM IS RUNNING.\n");
        while(!finished) {
            showMainMenu();
        }
    }

    public static void showMainMenu() {
        System.out.println("\nUser Storage Application");
        System.out.println("-------------------------");
        System.out.println("Main menu:");
        System.out.println("1. Create new user");
        System.out.println("2. Update existing user");
        System.out.println("3. Get user information");
        System.out.println("4. Get all users information");
        System.out.println("5. Delete user from storage");
        System.out.println("6. Exit application");
        actionMainMenu();
    }

    public static void actionMainMenu() {
        System.out.print("\nEnter the number (1-6) to continue: ");
        String number = scanner.nextLine();
        switch(number) {
            case "1":
                showUserCreated();
                break;
            case "2":
                showUserStorage();
                System.out.println("\nChose the User you want to change.");
                showUserUpdated(userIdInput());
                break;
            case "3":
                System.out.println("What User info you want to print?");
                showUserInfo(userIdInput());
                break;
            case "4":
                showUserStorage();
                break;
            case "5":
                showUserDeleted(userIdInput());
                break;
            case "6":
                exit();
                break;
            default:
                System.out.println("\nYou've entered wrong number. Please, try again.");
        }
    }

    public static void exit(){
        finished = true;
        LOG.info("THE PROGRAM SUCCESSFULLY FINISHED.\n");
    }

    public static int userIdInput() {
        String stringId;
        int userId;
        while (true) {
            System.out.print("Enter the User id: ");
            stringId = scanner.nextLine();
            try {
                userId = Integer.parseInt(stringId);
                break;
            } catch (NumberFormatException e) {
                System.out.println("\nYou've entered wrong id. Please, try again.");
            }
        }
        return userId;
    }

    public static void showUserStorage() {
        List<User> users = userFileDao.getAllUsers();
        System.out.println("\nUsers storage:");
        for (User user : users) {
            System.out.println(user);
        }
    }

    public static void showUserInfo(int id) {
        User user = userFileDao.getUserById(id);
        System.out.println("\nUser id '" + id + "' information: ");
        System.out.println(user);
    }

    public static void showUserCreated() {
        if (userFileDao.createUser()) {
            System.out.println("User successfully added.");
        } else {
            System.out.println("User creation failed.");
        }
    }

    public static void showUserDeleted(int id) {
        if (userFileDao.deleteUser(id)) {
            System.out.println("User successfully deleted.");
        } else {
            System.out.println("User deletion failed.");
        }
    }

    public static void showUserUpdated(int id) {
        if (userFileDao.updateUser(userFileDao.getUserById(id))) {
            System.out.println("User successfully updated.");
        } else {
            System.out.println("User update failed.");
        }
    }
}
