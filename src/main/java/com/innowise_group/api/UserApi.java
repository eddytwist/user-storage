package com.innowise_group.api;

import com.innowise_group.dao.exception.UserNotFoundException;
import com.innowise_group.entity.Role;
import com.innowise_group.entity.User;
import com.innowise_group.service.CrudService;
import com.innowise_group.util.validation.EmailValidation;
import com.innowise_group.util.validation.PhoneNumberValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UserApi {
    private static final Logger LOG = LoggerFactory.getLogger(UserApi.class);
    private final CrudService<User> crudService;
    private final Scanner scanner;
    private boolean finished = false;

    public UserApi(CrudService<User> crudService) {
        this.crudService = crudService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        LOG.info("THE PROGRAM IS RUNNING.\n");
        while (!finished) {
            showMainMenu();
        }
    }

    public void exit() {
        finished = true;
        System.out.println("\nGood bye!");
        LOG.info("THE PROGRAM SUCCESSFULLY FINISHED.\n");
    }

    public void showMainMenu() {
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
                showUserStorage();
                System.out.println("\nWhat User info you want to print?");
                showUserInfo(userIdInput());
                break;
            case "4":
                showUserStorage();
                break;
            case "5":
                showUserStorage();
                System.out.println("\nWhat User info you want to delete?");
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
        List<User> users = crudService.getAllUsers();
        System.out.println("\nUsers storage:");
        for (User user : users) {
            System.out.println(user);
        }
        LOG.info("Users printed.");
    }

    public void showUserInfo(int id) {
        User user;
        try {
            user = crudService.getUserById(id);
            if (user != null) {
                System.out.println("\nUser id '" + id + "' information: \n" + user);
                LOG.info("User info printed.");
            }
        } catch (UserNotFoundException | NullPointerException e) {
            System.out.println("\nUser not found. Please, try again.");
            LOG.error("User wasn't found.");
        }
    }

    public void showUserCreated() {
        User user = new User(
                addFirstName(),
                addLastName(),
                addEmail(),
                addRoles(),
                addPhoneNumbers());
        if (crudService.createUser(user)) {
            System.out.println("User successfully added.");
            LOG.info("User created.");
        } else {
            System.out.println("User creation failed.");
            LOG.error("User creation failed.");
        }
    }

    public void showUserDeleted(int id) {
        try {
            crudService.deleteUser(id);
            System.out.println("\nUser successfully deleted.");
            LOG.info("User deleted.");
        } catch (UserNotFoundException | NullPointerException e) {
            System.out.println("\nUser not found. Please, try again.");
            LOG.error("User deletion failed.");
        }
    }

    public void showUserUpdated(int id) {
        User updatedUser;
        try {
            updatedUser = updateUserFields(crudService.getUserById(id));
            if (crudService.updateUser(updatedUser)) {
                System.out.println("User successfully updated.");
                LOG.info("User updated.");
            } else {
                System.out.println("User update failed.");
            }
        } catch (UserNotFoundException | NullPointerException e) {
            System.out.println("\nUser not found. Please, try again.");
            LOG.error("User update failed.");
        }
    }

    public void showRoles() {
        System.out.println("\nRoles:");
        for (Role role : Role.values()) {
            System.out.println(role.ordinal() + 1 + ". " + role);
        }
        System.out.print("Choose the role by number (1-6): ");
    }

    public int userIdInput() {
        String stringId;
        int userId;
        while (true) {
            System.out.print("Enter the User id: ");
            stringId = scanner.nextLine();
            try {
                userId = Integer.parseInt(stringId);
                if (userId < 1) {
                    System.out.println("\nUser doesn't exist! Please, try again.");
                } else {
                    LOG.info("User exists.");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nYou've entered wrong id. Please, try again.");
                LOG.error("Invalid User ID input.");
            }
        }
        return userId;
    }

    public Set<Role> addRoles() {
        Set<Role> roles = new HashSet<>();
        int maxRolesSize = 3;
        while (roles.size() < maxRolesSize) {
            showRoles();
            String roleNumber = scanner.nextLine();
            Role role = chooseRole(roleNumber, roles);
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
                    } else {
                        break;
                    }
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

    public Role chooseRole(String roleNumber, Set<Role> roles){
        int maxRolesSize = 3;
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
                    System.out.println("\nWrong role number. You should add at least ONE role.\nPlease, try again.");
                } else {
                    System.out.println("\nWrong role number. User roles: " + roles + " " + roles.size() + "/" + maxRolesSize + "\nPlease, try again.");
                }
        }
        return role;
    }

    public Set<String> addPhoneNumbers() {
        Set<String> phoneNumbers = new HashSet<>();
        int maxPhoneNumbersSize = 3;
        while (phoneNumbers.size() < maxPhoneNumbersSize) {
            System.out.print("\nEnter a phone number (example:'37500 1234567'): ");
            String phoneNumber = scanner.nextLine();
            if (!PhoneNumberValidation.validatePhoneNumber(phoneNumber)) {
                if (phoneNumbers.size() == 0) {
                    System.out.println("\nInvalid phone number. You should add at least ONE phone number.\nPlease, try again.");
                } else {
                    System.out.println("\nInvalid phone number. User phone numbers: " + phoneNumbers + " " +
                            phoneNumbers.size() + "/" + maxPhoneNumbersSize + "\nPlease, try again.");
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
        System.out.print("Enter user name: ");
        String firstName = scanner.nextLine();
        LOG.info("First name added to User. Details: " + firstName);
        return firstName;
    }

    public String addLastName() {
        System.out.print("Enter user surname: ");
        String lastName = scanner.nextLine();
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
        User userCopy = new User(user);
        boolean finished = false;
        while (!finished) {
            System.out.print("\nChose the field you want to update by number (1-5):" + userCopy.getUserFields());
            System.out.println("\n6. Finish update.");
            String fieldNumber = scanner.nextLine();
            switch (fieldNumber) {
                case "1":
                    userCopy.setFirstName(addFirstName());
                    break;
                case "2":
                    userCopy.setLastName(addLastName());
                    break;
                case "3":
                    userCopy.setEmail(addEmail());
                    break;
                case "4":
                    userCopy.setRoles(addRoles());
                    break;
                case "5":
                    userCopy.setPhoneNumbers(addPhoneNumbers());
                    break;
                case "6":
                    finished = true;
                    break;
                default:
                    System.out.println("\nWrong number. Please, try again.");
            }
        }
        LOG.info("User successfully updated. Details: " + userCopy);
        return userCopy;
    }

}