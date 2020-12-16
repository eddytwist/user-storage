package com.innowise_group.entity;

public enum Role {
    BACKEND,
    FRONTEND,
    DESIGNER,
    TESTER,
    MANAGER,
    ANALYST;

    public static void showRoles() {
        System.out.println("\nRoles:");
        for(Role role : Role.values()) {
            System.out.println(role.ordinal() + 1 + ". " + role);
        }
        System.out.print("Choose the role by number (1-6): ");
    }
}
