package api;

import entity.Role;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.impl.UserServiceImpl;
import util.validation.EmailValidation;
import util.validation.PhoneNumberValidation;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UserApi {
    private static final Logger LOG = LoggerFactory.getLogger(UserApi.class);
    public static Scanner scanner = new Scanner(System.in);
    public static UserServiceImpl userService = new UserServiceImpl();
    private static boolean finished = false;

    public void start() {
        LOG.info("THE PROGRAM IS RUNNING.\n");
        while (!finished) {
            showMainMenu();
        }
    }

    public void exit() {
        finished = true;
        LOG.info("THE PROGRAM SUCCESSFULLY FINISHED.\n");
    }

    public void showMainMenu() {
        System.out.println("\nUser Storage Application");
        System.out.println("-------------------------");
        System.out.println("Application menu:");
        System.out.println("1. Create new user");
        System.out.println("2. Update existing user");
        System.out.println("3. Get user information");
        System.out.println("4. Get all users information");
        System.out.println("5. Delete user from storage");
        System.out.println("6. Exit application");
        actionMainMenu();
    }

    public void actionMainMenu() {
        System.out.print("\nEnter the number (1-6) to continue: ");
        String number = scanner.nextLine();
        switch (number) {
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

    public void showUserStorage() {
        List<User> users = userService.getAllUsers();
        System.out.println("\nUsers storage:");
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void showUserInfo(int id) {
        User user = userService.getUserById(id);
        System.out.println("\nUser id '" + id + "' information: ");
        System.out.println(user);
    }

    public void showUserCreated() {
        User user = new User(
                addFirstName(),
                addLastName(),
                addEmail(),
                addRoles(),
                addPhoneNumbers());
        if (userService.createUser(user)) {
            System.out.println("User successfully added.");
        } else {
            System.out.println("User creation failed.");
            LOG.error("User creation failed.");
        }
    }

    public void showUserDeleted(int id) {
        if (userService.deleteUser(id)) {
            System.out.println("User successfully deleted.");
        } else {
            System.out.println("User deletion failed.");
            LOG.error("User deletion failed.");
        }
    }

    public void showUserUpdated(int id) {
        User updatedUser = updateUserFields(userService.getUserById(id));
        if (userService.updateUser(updatedUser)) {
            System.out.println("User successfully updated.");
        } else {
            System.out.println("User update failed.");
            LOG.error("User update failed.");
        }
    }

    public int userIdInput() {
        String stringId;
        int userId;
        while (true) {
            System.out.print("Enter the User id: ");
            stringId = scanner.nextLine();
            try {
                userId = Integer.parseInt(stringId);
                if (userId > userService.getLastUserId() || userId < 1) {
                    System.out.println("\nUser doesn't exist! Please, try again.");
                } else {
                    LOG.info("User exists.");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nYou've entered wrong id. Please, try again.");
            }
        }
        return userId;
    }

    public Set<Role> addRoles() {
        Set<Role> roles = new HashSet<>();
        int maxRolesSize = 3;
        while (roles.size() < maxRolesSize) {
            Role.showRoles();
            String roleNumber = scanner.nextLine();
            Role role = null;
            switch (roleNumber) {
                case "1":
                    role = Role.BACKEND;
                    break;
                case "2":
                    role = Role.FRONTEND;
                    break;
                case "3":
                    role = Role.DESIGNER;
                    break;
                case "4":
                    role = Role.TESTER;
                    break;
                case "5":
                    role = Role.MANAGER;
                    break;
                case "6":
                    role = Role.ANALYST;
                    break;
                default:
                    if (roles.size() == 0) {
                        System.out.println("\nWrong role number. You should add at least ONE role." +
                                "\nPlease, try again.");
                    } else {
                        System.out.println("\nWrong role number. User roles: " + roles + " " +
                                roles.size() + "/" + maxRolesSize +
                                "\nPlease, try again.");
                    }
            }
            if (role != null) {
                if (!roles.add(role)) {
                    System.out.println("Role '" + role + "' already exist.");
                } else {
                    roles.add(role);
                    System.out.println("The role '" + role + "' successfully added.");
                    LOG.info("Role added. Details: " + role);
                }
                if (roles.size() != maxRolesSize) {
                    System.out.println("\nDo you want to add another role? (enter 'YES')" +
                            "\nUser roles: " + roles + " " + roles.size() + "/" + maxRolesSize);
                    String answer = scanner.nextLine().toUpperCase();
                    if (answer.equals("YES")) {
                        System.out.println("You can chose another role.");
                    } else break;
                } else {
                    System.out.println("\nYou've added the maximum quantity of roles." +
                            "\nUser roles: " + roles + " " + roles.size() + "/" + maxRolesSize);
                    break;
                }
            }
        }
        LOG.info("Roles added to User. Details: " + roles);
        return roles;
    }

    public Set<String> addPhoneNumbers() {
        Set<String> phoneNumbers = new HashSet<>();
        int maxPhoneNumbersSize = 3;
        while (phoneNumbers.size() < maxPhoneNumbersSize) {
            System.out.print("\nEnter a phone number (example:'37500 1234567'): ");
            String phoneNumber = scanner.nextLine();
            if (!PhoneNumberValidation.validatePhoneNumber(phoneNumber)) {
                if (phoneNumbers.size() == 0) {
                    System.out.println("\nInvalid phone number. You should add at least ONE phone number." +
                            "\nPlease, try again.");
                } else {
                    System.out.println("\nInvalid phone number. User phone numbers: " + phoneNumbers + " " +
                            phoneNumbers.size() + "/" + maxPhoneNumbersSize +
                            "\nPlease, try again.");
                }
            } else {
                if (!phoneNumbers.add(phoneNumber)) {
                    System.out.println("Phone number '" + phoneNumber + "' already exist.");
                } else {
                    phoneNumbers.add(phoneNumber);
                    System.out.println("Phone number '" + phoneNumber + "' successfully added.");
                    LOG.info("Phone number added. Details: " + phoneNumbers);
                }
                if (phoneNumbers.size() != maxPhoneNumbersSize) {
                    System.out.println("\nDo you want to add another phone number? (enter 'YES')" +
                            "\nUser phone numbers: " + phoneNumbers + " " + phoneNumbers.size() + "/" + maxPhoneNumbersSize);
                    String answer = scanner.nextLine().toUpperCase();
                    if (!answer.equals("YES")) {
                        break;
                    }
                } else {
                    System.out.println("\nYou've added the maximum quantity of phone numbers." +
                            "\nUser phone numbers: " + phoneNumbers + " " + phoneNumbers.size() + "/" + maxPhoneNumbersSize);
                    break;
                }
            }
        }
        LOG.info("Phone numbers added to User. Details: " + phoneNumbers);
        return phoneNumbers;
    }

    public String addFirstName() {
        String firstName;
        System.out.print("Enter user name: ");
        firstName = scanner.nextLine();
        LOG.info("First name added to User. Details: " + firstName);
        return firstName;
    }

    public String addLastName() {
        String lastName;
        System.out.print("Enter user surname: ");
        lastName = scanner.nextLine();
        LOG.info("Last name added to User. Details: " + lastName);
        return lastName;
    }

    public String addEmail() {
        String email;
        while (true) {
            System.out.print("Enter user email (example:'any@email.com'): ");
            email = scanner.nextLine();
            if (!EmailValidation.validateEmail(email)) {
                System.out.println("\nInvalid email address. Please, try again.");
            } else {
                break;
            }
        }
        LOG.info("Email added to User. Details: " + email);
        return email;
    }

    public User updateUserFields(User user) {
        boolean finished = false;
        while (!finished) {
            System.out.print("\nChose the field you want to update by number (1-5):" + user.getUserFields());
            System.out.println("\n6. Finish update.");
            String fieldNumber = scanner.nextLine();
            switch (fieldNumber) {
                case "1":
                    user.setFirstName(addFirstName());
                    break;
                case "2":
                    user.setLastName(addLastName());
                    break;
                case "3":
                    user.setEmail(addEmail());
                    break;
                case "4":
                    user.setRoles(addRoles());
                    break;
                case "5":
                    user.setPhoneNumbers(addPhoneNumbers());
                    break;
                case "6":
                    finished = true;
                    break;
                default:
                    System.out.println("\nWrong number. Please, try again.");
            }
        }
        LOG.info("User successfully updated. Details: " + user);
        return user;
    }
}