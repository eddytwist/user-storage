package dao;

import entity.Role;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtil;
import util.validation.EmailValidation;
import util.validation.PhoneNumberValidation;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UserFileDao implements UserDao<User> {
    private static final Logger LOG = LoggerFactory.getLogger(UserFileDao.class);
    public static Scanner scanner = new Scanner(System.in);

    @Override
    public boolean createUser() {
        User user = new User();
        List<User> users =  FileUtil.readFile();
        user.setId(users.get(users.size() - 1).getId() + 1);
        user.setFirstName(addFirstName());
        user.setLastName(addLastName());
        user.setEmail(addEmail());
        user.setRoles(addRoles());
        user.setPhoneNumbers(addPhoneNumbers());
        users.add(user);
        LOG.info("User successfully created. Details: \n" + user);
        return FileUtil.updateFile(users);
    }

    @Override
    public User getUserById(int id) {
        List<User> users = FileUtil.readFile();
        User user = users.get(id - 1);
        LOG.info("User successfully found. Details: " + user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return FileUtil.readFile();
    }

    @Override
    public boolean updateUser(User user) {
        List<User> users = FileUtil.readFile();
        User updatedUser = updateUserFields(user);
        users.set(users.indexOf(user), updatedUser);
        return FileUtil.updateFile(users);
    }

    @Override
    public boolean deleteUser(int id) {
        List<User> users = FileUtil.readFile();
        User user = getUserById(id);
        users.remove(user);
        LOG.info("User successfully deleted. Details: " + users);
        return FileUtil.updateFile(users);
    }

    public Set<Role> addRoles(){
        Set<Role> roles = new HashSet<>();
        int maxRolesSize = 3;
        while(roles.size() < maxRolesSize) {
            showRoles();
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
                    if(roles.size() == 0) {
                        System.out.println("\nWrong role number. You should add at least ONE role." +
                                "\nPlease, try again.");
                    } else {
                        System.out.println("\nWrong role number. User roles: " + roles + " " +
                                roles.size() + "/" + roles +
                                "\nPlease, try again.");
                    }
            }
            if(role != null) {
                if(!roles.add(role)) {
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

    public void showRoles() {
        System.out.println("\nRoles:");
        for(Role role : Role.values()) {
            System.out.println(role.ordinal() + 1 + ". " + role);
        }
        System.out.print("Choose the role by number (1-6): ");
    }

    public Set<String> addPhoneNumbers() {
        Set<String> phoneNumbers = new HashSet<>();
        int maxPhoneNumbersSize = 3;
        while(phoneNumbers.size() < maxPhoneNumbersSize) {
            System.out.print("\nEnter a phone number (example:'37500 1234567'): ");
            String phoneNumber = scanner.nextLine();
            if(!PhoneNumberValidation.validatePhoneNumber(phoneNumber)){
                if(phoneNumbers.size() == 0) {
                    System.out.println("\nInvalid phone number. You should add at least ONE phone number." +
                            "\nPlease, try again.");
                } else {
                    System.out.println("\nInvalid phone number. User phone numbers: " + phoneNumbers + " " +
                            phoneNumbers.size() + "/" + maxPhoneNumbersSize +
                            "\nPlease, try again.");
                }
            } else {
                if(!phoneNumbers.add(phoneNumber)) {
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
            if (!EmailValidation.validateEmail(email)){
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
